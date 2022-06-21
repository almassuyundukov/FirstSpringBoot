package com.example.firstspringboot.globalAdvice;

import com.example.firstspringboot.controller.AnswerController;
import com.example.firstspringboot.entity.Answer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnswerRepresentationsModel implements RepresentationModelAssembler<Answer, EntityModel<Answer>> {
    @Override
    public EntityModel<Answer> toModel(Answer entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AnswerController.class).delete(entity.getId()))
                        .withSelfRel().withRel("Http method " + HttpMethod.DELETE).expand(),
                linkTo(methodOn(AnswerController.class).findAll())
                        .withSelfRel().withRel("Http method " + HttpMethod.GET).expand(),
                linkTo(methodOn(AnswerController.class).update(entity, entity.getId()))
                        .withSelfRel().withRel("Http method " + HttpMethod.PUT).expand(),
                linkTo(methodOn(AnswerController.class).save(entity))
                        .withSelfRel().withRel("Http method " + HttpMethod.POST).expand()
        );
    }
}
