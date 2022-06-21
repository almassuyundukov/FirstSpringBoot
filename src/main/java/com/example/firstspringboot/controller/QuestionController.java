package com.example.firstspringboot.controller;

import com.example.firstspringboot.entity.Question;
import com.example.firstspringboot.globalAdvice.QuestionRepresentationsModel;
import com.example.firstspringboot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepresentationsModel model;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Question>> findById(@PathVariable Integer id){
        EntityModel<Question> model = this.model.toModel(questionService.findById(id));
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> findAll(){
        return ResponseEntity.ok().body(questionService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        questionService.save(question);
        return ResponseEntity.ok().body("Question is created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Question question, @PathVariable Integer id){
        questionService.update(question,id);
        return ResponseEntity.ok().body("Question is updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        questionService.delete(id);
        return ResponseEntity.ok().body("Question is deleted");
    }
}
