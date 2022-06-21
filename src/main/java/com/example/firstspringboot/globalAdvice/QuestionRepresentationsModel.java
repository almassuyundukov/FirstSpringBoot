package com.example.firstspringboot.globalAdvice;

import com.example.firstspringboot.controller.QuestionController;
import com.example.firstspringboot.entity.Question;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionRepresentationsModel implements RepresentationModelAssembler<Question, EntityModel<Question>> {
    @Override
    public EntityModel<Question> toModel(Question entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(QuestionController.class).delete(entity.getId()))
                        .withSelfRel().withRel("Http method " + HttpMethod.DELETE).expand(),
                linkTo(methodOn(QuestionController.class).findAll())
                        .withSelfRel().withRel("Http method " + HttpMethod.GET).expand(),
                linkTo(methodOn(QuestionController.class).update(entity, entity.getId()))
                        .withSelfRel().withRel("Http method " + HttpMethod.PUT).expand(),
                linkTo(methodOn(QuestionController.class).addQuestion(entity))
                        .withSelfRel().withRel("Http method " + HttpMethod.POST).expand()
                );
    }
}
