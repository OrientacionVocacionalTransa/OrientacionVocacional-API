package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.dto.UserDTO;
import com.example.orientacionvocacionalapi.model.entity.Student;
import com.example.orientacionvocacionalapi.model.entity.User;
import com.example.orientacionvocacionalapi.model.enums.Plan;
import com.example.orientacionvocacionalapi.repository.StudentRepository;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JwtUtilService jwtUtilService;

    public String updateStudentPlanToPremium(Integer userId) {
        Optional<Student> studentOptional = studentRepository.findById(Long.valueOf(userId));
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student.getPlan() != Plan.PREMIUM) {
                student.setPlan(Plan.PREMIUM);
                studentRepository.save(student);

                // Después de actualizar el plan, generamos un nuevo token
                return jwtUtilService.generateToken(student);  // Genera un token con la nueva información
            }
        } else {
            throw new IllegalArgumentException("No se encontró un estudiante con el ID: " + userId);
        }
        return null; // Retornamos null si no hubo cambio en el plan
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateProfileImage(Integer userId, String newFilePath) {
        User user = findById(userId);
        user.setImg_profile(newFilePath);
        userRepository.save(user);
    }

    public void registerUser(UserDTO usuarioDTO) {

        User user = new User();
        user.setFirstName(usuarioDTO.getFirstName());
        user.setLastName(usuarioDTO.getLastName());
        user.setEmail(usuarioDTO.getEmail());
        user.setPassword(usuarioDTO.getPassword());


        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));


        usuarioRepository.save(user);
    }

    public User login(String email, String password) {
        User usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        }
        return null;
    }

    public void deleteUser(Integer id) throws Exception {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));

        usuarioRepository.delete(user);
    }

    public User getUserById(Integer id) throws Exception {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));
    }

    public List<User> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public void updateAndEncryptPassword(Integer id, String newPassword) throws Exception {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));


        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);


        usuarioRepository.save(user);
    }

    public User findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void generateResetPasswordToken(String email) throws Exception {
        User user = usuarioRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("No se encontró un usuario con ese correo.");
        }

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        usuarioRepository.save(user);


        sendResetPasswordEmail(user.getEmail(), token);
    }


    public void resetPassword(String token, String newPassword) throws Exception {
        User user = usuarioRepository.findByResetPasswordToken(token);
        if (user == null) {
            throw new Exception("Token inválido.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        usuarioRepository.save(user);
    }


    private void sendResetPasswordEmail(String email, String token) throws Exception {
        String resetLink = "https://orientacion-vocacional.vercel.app/reset-password?token=" + token;
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject("Restablecer tu contraseña en OrientacionVocacional");
        helper.setText("<p>Hola,</p>" +
                "<p>Esperamos que estés teniendo un buen día. Hemos recibido una solicitud para restablecer la contraseña de tu cuenta en Orientacion Vocacional, que está diseñada para ayudarte en tu camino de orientación vocacional.</p>" +
                "<p>Si deseas restablecer tu contraseña, haz clic en el enlace de abajo:</p>" +
                "<a href=\"" + resetLink + "\">Restablecer contraseña</a>" +
                "<p>Si no solicitaste este cambio, puedes ignorar este correo.</p>" +
                "<p>¡Gracias por formar parte de nuestra comunidad!</p>" +
                "<p>Atentamente,<br>El equipo de desarrollo de Orientacion Vocacional</p>", true);

        mailSender.send(message);
    }

    public User updateUser(User user) {
        return usuarioRepository.save(user);
    }

}
