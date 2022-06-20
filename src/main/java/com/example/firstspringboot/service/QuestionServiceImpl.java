package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Question;
import com.example.firstspringboot.exception.QuestionNotFoundException;
import com.example.firstspringboot.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() ->
                        new QuestionNotFoundException("Question with id" + id + "not found"));
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) throw new QuestionNotFoundException("Question not found in database");
        return questions;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void update(Question question, Integer id) {
        Question questionById = findById(id);
        questionById.setAnswers(question.getAnswers())
                .setLevel(question.getLevel())
                .setName(question.getName())
                .setProfile(question.getProfile())
                .setNumOfCorrect(question.getNumOfCorrect());
        questionRepository.save(questionById);
    }

    @Override
    public void delete(Integer id) {
        Question question = findById(id);
        questionRepository.delete(question);
    }
}
