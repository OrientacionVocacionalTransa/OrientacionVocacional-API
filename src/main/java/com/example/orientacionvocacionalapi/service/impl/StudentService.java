package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.Mapper.StudentMapper;
import com.example.orientacionvocacionalapi.dto.StudentDTO;
import com.example.orientacionvocacionalapi.exception.BadRequestException;
import com.example.orientacionvocacionalapi.exception.ResourceNotFoundException;
import com.example.orientacionvocacionalapi.model.entity.Student;
import com.example.orientacionvocacionalapi.model.enums.ERole;
import com.example.orientacionvocacionalapi.repository.StudentRepository;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private EmailService emailService;


    public List<StudentDTO> listAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toDTO)
                .toList();
    }


    public Optional<Student> getStudentProfileByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public StudentDTO findById(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El estudiante con ID " + id+ "no fue encontrado"));
        return studentMapper.toDTO(student);
    }

    public StudentDTO registerStudent(StudentDTO studentDTO) {
        Integer randomId = generateUniqueRandomId();
        studentRepository.findByFirstNameAndLastName(studentDTO.getFirstName(), studentDTO.getLastName())
                .ifPresent(existingStudent -> {
                    throw new BadRequestException("El estudiante ya existe con el mismo nombre y apellido");
                });
        String verificationCode = generateVerificationCode();

        Student estudiante = studentMapper.toEntity(studentDTO);
        ERole eRole = ERole.STUDENT;

        estudiante.setId(randomId);
        estudiante.setImg_profile("profile.png");
        estudiante.setFirstName(studentDTO.getFirstName());
        estudiante.setLastName(studentDTO.getLastName());
        estudiante.setEmail(studentDTO.getEmail());
        estudiante.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        estudiante.setRole(eRole);

        estudiante.setVerificationCode(verificationCode); // Guardar el código de verificación
        estudiante.setVerified(false); // La cuenta está pendiente de verificación

        estudiante = usuarioRepository.save(estudiante);
        // Enviar el código de verificación por correo electrónico

        emailService.sendVerificationEmail(estudiante.getEmail(), verificationCode);

        return studentMapper.toDTO(estudiante);
    }

    public String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // Genera un código de 6 dígitos
    }

    private Integer generateUniqueRandomId() {
        Random random = new Random();
        Integer randomId;

        do {
            randomId = 1000000 + random.nextInt(9000000);
        } while (studentRepository.existsById(Long.valueOf(randomId)));

        return randomId;
    }
}
