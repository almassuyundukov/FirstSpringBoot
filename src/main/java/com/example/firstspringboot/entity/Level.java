package com.example.firstspringboot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Levels")
@Data
@Accessors(chain = true)
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<Question> questions;
}
