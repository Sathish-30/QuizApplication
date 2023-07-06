package com.project.quiz_application.service;

import com.project.quiz_application.model.QuestionModel;
import com.project.quiz_application.repositry.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class QuestionService {
        @Autowired
        QuestionDao questionDao;
       public ResponseEntity<List<QuestionModel>> getQuestions(){
           try {
               return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
           }catch(Exception e){
               e.printStackTrace();
               return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
           }
       }

       public ResponseEntity<List<QuestionModel>> getQuestionsByCategory(String category){
            try{
                return new ResponseEntity<>(questionDao.findByCategory(category) , HttpStatus.OK);
            }catch(Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
            }
       }


    public ResponseEntity<String> addQuestion(QuestionModel questionModel) {
            try{
                questionDao.save(questionModel);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
            }
    }

    public String deleteQuestion(Integer id) {
           questionDao.deleteById(id);
           return "deleted successfully";
    }

}
