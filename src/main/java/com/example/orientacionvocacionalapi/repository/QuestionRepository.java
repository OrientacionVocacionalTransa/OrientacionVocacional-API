package com.example.orientacionvocacionalapi.repository;

import com.example.orientacionvocacionalapi.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
