package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.AdviserDTO;
import com.example.orientacionvocacionalapi.dto.StudentDTO;
import com.example.orientacionvocacionalapi.dto.UserDTO;
import com.example.orientacionvocacionalapi.dto.UserUpdateDTO;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.model.entity.Student;
import com.example.orientacionvocacionalapi.model.entity.User;
import com.example.orientacionvocacionalapi.repository.AdviserRepository;
import com.example.orientacionvocacionalapi.repository.StudentRepository;
import com.example.orientacionvocacionalapi.service.impl.AdviserService;
import com.example.orientacionvocacionalapi.service.impl.StudentService;
import com.example.orientacionvocacionalapi.service.impl.JwtUtilService;
import com.example.orientacionvocacionalapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdviserService adviserService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdviserRepository adviserRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al registrar el usuario: " + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/registerStudent")
    public void registerStudent(@Validated @RequestBody StudentDTO studentDTO) {
        studentService.registerStudent(studentDTO);
        ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
    }

    @PostMapping("/registerAdviser")
    public void registerAdvisor(@Validated @RequestBody AdviserDTO adviserDTO) {
        adviserService.registerAdvisor(adviserDTO);
        ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();


            if (!student.isVerified()) {
                response.put("message", "Por favor, verifica tu correo electrónico antes de iniciar sesión");
                return ResponseEntity.badRequest().body(response);
            }


            if (!passwordEncoder.matches(password, student.getPassword())) {
                response.put("message", "Contraseña incorrecta");
                return ResponseEntity.badRequest().body(response);
            }


            String token = jwtUtilService.generateToken(student);
            response.put("message", "Inicio de sesión exitoso");
            response.put("token", token);
            return ResponseEntity.ok(response);
        }


        Optional<Adviser> optionalAdvisor = adviserRepository.findByEmail(email);
        if (optionalAdvisor.isPresent()) {
            Adviser advisor = optionalAdvisor.get();


            if (!passwordEncoder.matches(password, advisor.getPassword())) {
                response.put("message", "Contraseña incorrecta");
                return ResponseEntity.badRequest().body(response);
            }


            String token = jwtUtilService.generateToken(advisor);
            response.put("message", "Inicio de sesión exitoso");
            response.put("token", token);
            return ResponseEntity.ok(response);
        }


        response.put("message", "Correo electrónico no registrado");
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUserInfo(@RequestHeader("Authorization") String token,
                                               @RequestBody UserUpdateDTO userUpdateDto) {

        String jwt = token.substring(7);
        String email = jwtUtilService.extractUsername(jwt);


        User usuario = userService.findByEmail(email);
        if (usuario != null) {

            usuario.setFirstName(userUpdateDto.getFirstName());
            usuario.setLastName(userUpdateDto.getLastName());
            usuario.setEmail(userUpdateDto.getEmail());


            User updatedUser = userService.updateUser(usuario);

            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/encrypt-password")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id, @RequestParam String newPassword) {
        try {
            userService.updateAndEncryptPassword(id, newPassword);
            return ResponseEntity.ok("Contraseña actualizada y encriptada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la contraseña: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            userService.generateResetPasswordToken(email);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(Map.of("message", "Se ha enviado un correo con instrucciones para restablecer su contraseña."));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(Map.of("error", "Error al generar el token de recuperación: " + e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            userService.resetPassword(token, newPassword);

            return ResponseEntity.ok(Map.of("message", "Contraseña actualizada exitosamente."));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al restablecer la contraseña: " + e.getMessage()));
        }
    }
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token) {

        String jwt = token.substring(7);


        String email = jwtUtilService.extractUsername(jwt);


        User usuario = userService.findByEmail(email);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
