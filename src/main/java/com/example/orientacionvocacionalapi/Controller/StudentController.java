package com.example.orientacionvocacionalapi.Controller;
import com.example.orientacionvocacionalapi.dto.StudentDTO;
import com.example.orientacionvocacionalapi.model.entity.Student;
import com.example.orientacionvocacionalapi.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/listStudents")
    public ResponseEntity<List<StudentDTO>> listStudents() {
        List<StudentDTO> students = studentService.listAllStudents();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfileStudent() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String email = authentication.getName();

        Optional<Student> student = studentService.getStudentProfileByEmail(email);


        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"Perfil del Estudiante no encontrado.\"}");
        }
    }
}