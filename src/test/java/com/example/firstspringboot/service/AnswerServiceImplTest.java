package com.example.firstspringboot.service;

import com.example.firstspringboot.entity.Answer;
import com.example.firstspringboot.entity.Question;
import com.example.firstspringboot.exception.AnswerNotFoundException;
import com.example.firstspringboot.repository.AnswerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private AnswerServiceImpl answerService;

    private Answer answer;
    private Answer answer1;
    private List<Answer> answers;

    @BeforeEach
    public void initAnswer() {
        answer = new Answer()
                .setQuestion(new Question())
                .setCorrect(true)
                .setName("Test answer");

        answer1 = new Answer()
                .setQuestion(new Question())
                .setCorrect(true)
                .setName("Test answer2");

        answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);

    }

    @Test
    void AnswerIsNotNullIfTheyAreDatabase() {
        Mockito.doReturn(answers).when(answerRepository).findAll();
        List<Answer> responseAnswer = answerService.findAll();
        assertThat(responseAnswer).isNotEmpty().hasSize(2);
    }

    @Test
    void answerIsNotEmptyIfFoundInDatabase(){
        Mockito.doReturn(Optional.of(answer)).when(answerRepository).findById(answer.getId());
        Answer responseAnswer = answerService.findById(answer.getId());
        assertThat(responseAnswer).isNotNull();
    }

    @Test
    void checkingSaveAnswerWithReceivedFromDatabase(){
        Mockito.doReturn(Optional.of(answer)).when(answerRepository).findById(answer.getId());
        Answer responseAnswer = answerService.findById(answer.getId());
        assertThat(responseAnswer).isEqualTo(answer);
    }

    @Tag(value = "AnswerThrow")
    @Nested
    class ThrowException{
        @Test
        void throwExceptionIfThereAreNoAnswersInDatabase(){
            Mockito.doReturn(List.of()).when(answerRepository).findAll();
            assertAll(
                    () -> assertThat(
                            assertThrows(AnswerNotFoundException.class,
                                    () -> answerService.findAll())
                                    .getMessage())
                            .isEqualTo("Answer is not found in database")
                    );
        }

        @Test
        void throwAnAnsweerNotFoundExceptionIfNoRepliesFoundById(){
            Mockito.doReturn(Optional.empty()).when(answerRepository).findById(answer.getId());
            assertAll(
                    () -> assertThat(
                            assertThrows(AnswerNotFoundException.class,
                                    () -> answerService.findById(answer.getId()))
                                    .getMessage())
                            .isEqualTo("Answer with id null not found")
            );
        }
    }
}