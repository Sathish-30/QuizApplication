package com.project.quiz_application.repositry;

import com.project.quiz_application.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface QuizDao extends JpaRepository <Quiz,Integer>{
}
