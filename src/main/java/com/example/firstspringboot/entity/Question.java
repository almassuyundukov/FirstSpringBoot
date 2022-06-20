package com.example.firstspringboot.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Questions")
@Accessors(chain = true)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer numOfCorrect;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "level_id")
    private Level level;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
}
