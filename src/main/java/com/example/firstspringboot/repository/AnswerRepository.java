package com.example.firstspringboot.repository;

import com.example.firstspringboot.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
