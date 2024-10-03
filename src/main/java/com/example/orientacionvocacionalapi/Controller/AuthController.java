package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.AsesorDTO;
import com.example.orientacionvocacionalapi.dto.EstudianteDTO;
import com.example.orientacionvocacionalapi.dto.UserDTO;
import com.example.orientacionvocacionalapi.dto.UserUpdateDTO;
import com.example.orientacionvocacionalapi.model.entity.User;
import com.example.orientacionvocacionalapi.service.impl.AsesorService;
import com.example.orientacionvocacionalapi.service.impl.EstudianteService;
import com.example.orientacionvocacionalapi.service.impl.JwtUtilService;
import com.example.orientacionvocacionalapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private AsesorService asesorService;

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Validated @RequestBody UserDTO usuarioDTO) {
        try {
            usuarioService.registrarUsuario(usuarioDTO);
            return ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al registrar el usuario.\"}");
        }
    }

    @PostMapping("/registerstudent")
    public ResponseEntity<?> registrarEstudiante(@Validated @RequestBody EstudianteDTO estudianteDTO) {
        try {
            estudianteService.registrarEstudiante(estudianteDTO);
            return ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir el stack trace para más detalles
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al registrar el usuario: " + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/registerasesor")
    public ResponseEntity<?> registrarAsesor(@Validated @RequestBody AsesorDTO asesorDTO) {
        try {
            asesorService.registrarAsesor(asesorDTO);
            return ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir el stack trace para más detalles
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al registrar el usuario:" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        User usuario = usuarioService.login(email, password);


        if (usuario != null) {

            String token = jwtUtilService.generateToken(usuario);

            response.put("message", "Login exitoso");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Credenciales incorrectas");
            return ResponseEntity.status(401).body(response);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUserInfo(@RequestHeader("Authorization") String token,
                                               @RequestBody UserUpdateDTO userUpdateDto) {

        String jwt = token.substring(7);
        String email = jwtUtilService.extractUsername(jwt);


        User usuario = usuarioService.findByEmail(email);
        if (usuario != null) {

            usuario.setFirstName(userUpdateDto.getFirstName());
            usuario.setLastName(userUpdateDto.getLastName());
            usuario.setEmail(userUpdateDto.getEmail());


            User updatedUser = usuarioService.updateUser(usuario);

            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            usuarioService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = usuarioService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/obtenertodos")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usuarioService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/encrypt-password")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestParam String newPassword) {
        try {
            usuarioService.updateAndEncryptPassword(id, newPassword);
            return ResponseEntity.ok("Contraseña actualizada y encriptada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la contraseña: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            usuarioService.generateResetPasswordToken(email);
            // Devuelve una respuesta con encabezado de tipo JSON
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(Map.of("message", "Se ha enviado un correo con instrucciones para restablecer su contraseña."));
        } catch (Exception e) {
            // Devuelve una respuesta con encabezado de tipo JSON en caso de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(Map.of("error", "Error al generar el token de recuperación: " + e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            usuarioService.resetPassword(token, newPassword);
            // Devolver un JSON con un mensaje de éxito
            return ResponseEntity.ok(Map.of("message", "Contraseña actualizada exitosamente."));
        } catch (Exception e) {
            e.printStackTrace(); // Log del error para depuración
            // Devolver un JSON con el mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al restablecer la contraseña: " + e.getMessage()));
        }
    }
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token) {

        String jwt = token.substring(7);


        String email = jwtUtilService.extractUsername(jwt);


        User usuario = usuarioService.findByEmail(email);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
