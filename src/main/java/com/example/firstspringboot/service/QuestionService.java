package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Profile;
import com.example.firstspringboot.entity.Question;

import java.util.List;

public interface QuestionService {
    Question findById(Integer id);
    List<Question> findAll();
    void save(Question question);
    void update(Question question, Integer id);
    void delete(Integer id);
}
