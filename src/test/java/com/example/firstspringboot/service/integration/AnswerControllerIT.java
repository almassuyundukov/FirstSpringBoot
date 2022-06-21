package com.example.firstspringboot.service.integration;

import com.example.firstspringboot.controller.AnswerController;
import com.example.firstspringboot.entity.Answer;
import com.example.firstspringboot.entity.Question;
import com.example.firstspringboot.repository.AnswerRepository;
import com.example.firstspringboot.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswerControllerIT {

    @LocalServerPort
    private int port1;
    private final String HOST = "http://localhost:";
    private final String PATH = "/api/answers";

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    protected AnswerController answerController;
    private Answer answer;

    @BeforeEach
    void beforeEach(){
        Question question = new Question()
                .setName("Question Test")
                .setNumOfCorrect(1);
        answer = answerRepository.save(new Answer()
                .setQuestion(question)
                .setCorrect(true)
                .setName("Test name"));
    }

    @Test
    void answerControllerIsNotNull(){
        assertThat(answerController).isNotNull();
    }

    @Test
    void responseStatusOK(){
        HttpStatus statusCode = testRestTemplate
                .getForEntity(HOST + port1 + PATH + "/", String.class)
                .getStatusCode();
        assertThat(statusCode).isEqualTo(HttpStatus.OK);
    }

    @Test
    void responseStatusBADREQUEST(){
        HttpStatus statusCode = testRestTemplate
                .getForEntity(HOST + port1 + PATH + "/3", String.class)
                .getStatusCode();
        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void responseAnswer(){
        Answer forObject = testRestTemplate
                .getForObject(HOST + port1 + PATH + "/" + answer.getId(), Answer.class);
        answer.setQuestion(null);
        assertThat(forObject).isEqualTo(answer);
    }

    @AfterEach
    void afterEach(){
        questionRepository.deleteAll();
    }
}
