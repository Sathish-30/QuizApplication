package com.project.quiz_application.repositry;

import com.project.quiz_application.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionDao extends JpaRepository<QuestionModel , Integer> {

    List<QuestionModel> findByCategory(String category);

    @Query(value = "SELECT * FROM question_model q WHERE q.category =:category ORDER BY RANDOM() LIMIT :count" , nativeQuery = true)
    List<QuestionModel> findRandomQuestionByCategory(Integer count ,String category);

}
