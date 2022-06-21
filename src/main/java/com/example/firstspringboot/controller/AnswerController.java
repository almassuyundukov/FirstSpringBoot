package com.example.firstspringboot.controller;

import com.example.firstspringboot.entity.Answer;
import com.example.firstspringboot.globalAdvice.AnswerRepresentationsModel;
import com.example.firstspringboot.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerRepresentationsModel model;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Answer>> findById(@PathVariable Integer id){
        EntityModel<Answer> model = this.model.toModel(answerService.findById(id));
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/")
    public ResponseEntity<List<Answer>> findAll() {
        return ResponseEntity.ok().body(answerService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@Validated @RequestBody Answer answer){
        answerService.save(answer);
        return ResponseEntity.ok("Question is created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Answer answer, @PathVariable Integer id){
        answerService.update(answer,id);
        return ResponseEntity.ok("Question is updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        answerService.delete(id);
        return ResponseEntity.ok("Question is deleted");
    }
}
