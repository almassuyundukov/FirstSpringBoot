package com.example.firstspringboot.controller;

import com.example.firstspringboot.entity.Answer;
import com.example.firstspringboot.entity.Question;
import com.example.firstspringboot.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionRepository questionRepository;
    private Question question;

    @BeforeEach
    void beforeEach(){
        questionRepository.deleteAll();
        Answer answer = new Answer()
                .setCorrect(true)
                .setName("Testing answer for IT");
        Question saveQuestion = new Question()
                .setName("Testing question for IT")
                .setNumOfCorrect(1);
        saveQuestion.addAnswerToQuestion(answer);
        question = questionRepository.save(saveQuestion);
    }


    @Test
    void callQuestionControllerMethodGetByIdShouldReturnResponseWithStatusOK() throws Exception{
        mockMvc.perform(get("/question/" + question.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON));
    }

    @Test
    void callQuestionControllerMethodGetByIdShouldReturnQuestionEntity() throws Exception{
        mockMvc.perform(get("/question/" + question.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("id").value(question.getId()))
                .andExpect(jsonPath("name").value(question.getName()))
                .andExpect(jsonPath("numOfCorrect").value(question.getNumOfCorrect()))
                .andExpect(jsonPath("answers").isNotEmpty());
    }
}
