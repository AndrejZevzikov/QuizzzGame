package com.backend.quizzz.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Answer> answers;
    private String imageLink;
}