package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Answer;
import com.example.firstspringboot.entity.Question;
import com.example.firstspringboot.exception.AnswerQuantityMismatchException;
import com.example.firstspringboot.exception.QuestionExistException;
import com.example.firstspringboot.exception.QuestionNotFoundException;
import com.example.firstspringboot.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                        new QuestionNotFoundException("Question with id " + id + " not found"));
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) throw new QuestionNotFoundException("Question not found in database");
        return questions;
    }

    @Override
    public void save(Question question) {
        checkCountCorrectAnswer(question);
        checkQuestionExistByName(question.getName());
        List<Answer> answers = question.getAnswers();
        answers.forEach(a -> a.setQuestion(question));
        question.setAnswers(answers);
        questionRepository.save(question);
    }

    @Override
    public void update(Question newQuestion, Integer id) {
        checkCountCorrectAnswer(newQuestion);
        checkQuestionExistByName(newQuestion.getName());
        Question oldQuestion = findById(id);
        oldQuestion.setAnswers(newQuestion.getAnswers())
                .setLevel(newQuestion.getLevel())
                .setName(newQuestion.getName())
                .setProfile(newQuestion.getProfile())
                .setNumOfCorrect(newQuestion.getNumOfCorrect());
        questionRepository.save(oldQuestion);
    }

    @Override
    public void delete(Integer id) {
        Question question = findById(id);
        questionRepository.delete(question);
    }

    public void checkCountCorrectAnswer(Question question){
        long countAnswerCorrect = question.getAnswers()
                .stream()
                .filter(Answer::getCorrect)
                .count();
        if (question.getNumOfCorrect() != countAnswerCorrect) {
            throw new AnswerQuantityMismatchException("Quantity add true answers, is not correct");
        }
    }

    public void checkQuestionExistByName(String questionName){
        boolean isPresent = questionRepository.findQuestionByName(questionName).isPresent();
        if (isPresent) throw new QuestionExistException("Question with name " + questionName + "exist");

    }
}
