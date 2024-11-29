package com.example.orientacionvocacionalapi.repository;

import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.model.entity.Solicitation;
import com.example.orientacionvocacionalapi.model.entity.Student;
import com.example.orientacionvocacionalapi.model.enums.SolicitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolicitationRepository extends JpaRepository<Solicitation, Integer> {

    List<Solicitation> findByAdviserId(Integer adviserId);

    List<Solicitation> findByAdviserIdAndStatus(Long adviserId, SolicitationStatus status);
    List<Solicitation> findByStudentIdAndStatus(Long studentId, SolicitationStatus status);

    Optional<Solicitation> findByStudentIdAndAdviserId(Long studentId, Long adviserId);
    Optional<Solicitation> findByStudentAndAdviser(Student student, Adviser adviser);
}
