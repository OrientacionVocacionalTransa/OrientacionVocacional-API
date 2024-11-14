package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.Mapper.AdvisoryMapper;
import com.example.orientacionvocacionalapi.dto.AdvisoryDTO;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.model.entity.Advisory;
import com.example.orientacionvocacionalapi.model.entity.Student;
import com.example.orientacionvocacionalapi.repository.AdviserRepository;
import com.example.orientacionvocacionalapi.repository.AdvisoryRepository;
import com.example.orientacionvocacionalapi.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AdvisoryService {
    @Autowired
    private AdvisoryRepository advisoryRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdviserRepository adviserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdvisoryMapper advisoryMapper;

    @Transactional
    public Advisory createAdvisory(AdvisoryDTO advisoryDTO) {
        Integer randomId= generateUniqueRandomId();
        Student student = studentRepository.findById(Long.valueOf(advisoryDTO.getStudentId()))
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Adviser adviser = adviserRepository.findById(Long.valueOf(advisoryDTO.getAdviserId()))
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado"));


        Advisory advisory = new Advisory();
        advisory.setId(randomId);
        advisory.setLink(advisoryDTO.getLink());
        advisory.setName(advisoryDTO.getName());
        advisory.setDate(advisoryDTO.getDate());
        advisory.setTime(advisoryDTO.getTime());
        advisory.setStudent(student);
        advisory.setAdviser(adviser);


        advisoryRepository.save(advisory);


        String subject = "Nueva Asesoría Programada";
        String body = emailService.generateHtmlBodycreateAdvisory(student, advisory, adviser);

        emailService.sendHtmlEmail(student.getEmail(), subject, body);  // Enviar el correo

        return advisory;
    }

    private Integer generateUniqueRandomId() {
        Random random = new Random();
        Integer randomId;

        do {
            randomId = 1000000 + random.nextInt(9000000);
        } while (advisoryRepository.existsById(randomId));

        return randomId;
    }
    @Transactional
    public Advisory updateAdvisory(Integer id, AdvisoryDTO advisoryDTO) {
        Advisory advisory = advisoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advisory not found"));


        String previousLink = advisory.getLink();
        String previousName = advisory.getName();
        LocalDate previousDate = advisory.getDate();
        LocalTime previousTime = advisory.getTime();


        advisory.setLink(advisoryDTO.getLink());
        advisory.setName(advisoryDTO.getName());
        advisory.setDate(advisoryDTO.getDate());
        advisory.setTime(advisoryDTO.getTime());


        Advisory updatedAdvisory = advisoryRepository.save(advisory);


        emailService.sendReprogrammingEmail(advisory.getStudent().getEmail(), previousLink, previousName, previousDate, previousTime, advisory);

        return updatedAdvisory;
    }


    @Transactional
    public void deleteAdvisory(Integer id) {
        Advisory advisory = advisoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advisory not found"));


        String studentEmail = advisory.getStudent().getEmail();
        String advisoryName = advisory.getName();
        String advisoryLink = advisory.getLink();
        LocalDate advisoryDate = advisory.getDate();
        LocalTime advisoryTime = advisory.getTime();


        advisoryRepository.deleteById(id);


        emailService.sendCancellationEmail(studentEmail, advisoryName, advisoryLink, advisoryDate, advisoryTime);
    }

    public List<AdvisoryDTO> getAdvisoriesByUserId(Integer userId) {
        List<Advisory> advisories = advisoryRepository.findByStudentIdOrAdviserId(userId, userId);
        return advisories.stream()
                .map(advisoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
