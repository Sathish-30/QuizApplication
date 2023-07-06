package com.project.quiz_application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
    private List<QuestionModel> questions;
}
