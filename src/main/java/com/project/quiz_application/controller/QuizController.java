package com.project.quiz_application.controller;


import com.project.quiz_application.model.QuestionWrapper;

import com.project.quiz_application.model.Response;
import com.project.quiz_application.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category , @RequestParam int count , @RequestParam String title){
        return quizService.createQuiz(category , count , title );
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses){
        return quizService.checkResponse(id , responses);
    }
}
