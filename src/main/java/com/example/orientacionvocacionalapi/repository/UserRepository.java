package com.example.orientacionvocacionalapi.repository;
import com.example.orientacionvocacionalapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}