package com.example.firstspringboot.repository;

import com.example.firstspringboot.entity.Level;
import com.example.firstspringboot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findQuestionByNameAndLevel(String name, Level level);
    Optional<Question> findQuestionByName(String name);
}
