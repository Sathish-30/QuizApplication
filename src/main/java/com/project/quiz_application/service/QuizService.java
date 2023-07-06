package com.project.quiz_application.service;

import com.project.quiz_application.model.QuestionModel;
import com.project.quiz_application.model.QuestionWrapper;
import com.project.quiz_application.model.Quiz;
import com.project.quiz_application.model.Response;
import com.project.quiz_application.repositry.QuestionDao;
import com.project.quiz_application.repositry.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;
    public ResponseEntity<String> createQuiz(String category , int count , String title ) {
        List<QuestionModel> questions = questionDao.findRandomQuestionByCategory(count , category);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Succes", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
        try{
            Optional<Quiz> quiz = quizDao.findById(id);
            List<QuestionModel> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for(QuestionModel question : questionsFromDB){
                QuestionWrapper qn = new QuestionWrapper(question.getId() , question.getQuestionTitle() , question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
                questionsForUser.add(qn);
            }
            return new ResponseEntity<>(questionsForUser , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> checkResponse(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<QuestionModel> questions = quiz.get().getQuestions();
        int right = 0 ;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right == responses.size() ? "User choose all right answer" : "User doesn't get all the rigth answer" , HttpStatus.OK);
    }
}
