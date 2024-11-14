package com.example.orientacionvocacionalapi.Controller;
import com.example.orientacionvocacionalapi.Mapper.QuestionMapper;
import com.example.orientacionvocacionalapi.dto.QuestionDTO;
import com.example.orientacionvocacionalapi.model.entity.VocationalTest;
import com.example.orientacionvocacionalapi.model.entity.Question;
import com.example.orientacionvocacionalapi.repository.QuestionRepository;
import com.example.orientacionvocacionalapi.service.impl.JwtUtilService;
import com.example.orientacionvocacionalapi.service.impl.VocationalTestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vocational-test")
@CrossOrigin(origins = "http://localhost:4200")
public class VocationalController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private JwtUtilService jwtUtilService;
    @Autowired
    private VocationalTestService vocationalTestService;
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/submit-register")
    public ResponseEntity<Map<String, Object>> submitVocationalTest(
            @RequestBody VocationalTest test,
            HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        Integer userId = jwtUtilService.extractUserId(token);

        Map<String, Object> result = vocationalTestService.calculateTestResultRegister(test, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/questions")
    public List<QuestionDTO> getQuestions(){
        List<Question> questions = questionRepository.findAll();

        return questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

}

