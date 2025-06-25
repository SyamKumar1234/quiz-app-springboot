package com.syam.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syam.quizapp.model.Question;
import com.syam.quizapp.repository.QuestionDao;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> createQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> updateQuestion(Integer questionId, Question question) {
        try {
            String difficulty = question.getDifficulty();
            if (difficulty == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Question existingQuestion = questionDao.findById(questionId)
                    .orElse(null);
            existingQuestion.setDifficulty(difficulty);
            return new ResponseEntity<>(questionDao.save(existingQuestion), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> deleteQuestion(Integer questionId) {
        try {
            if (!questionDao.existsById(questionId)) {
                return new ResponseEntity<>("Question not found with ID: " + questionId, HttpStatus.NOT_FOUND);
            }
            questionDao.deleteById(questionId);
            return new ResponseEntity<>("Deleted question with: " + questionId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete the question with: " + questionId, HttpStatus.BAD_REQUEST);
        }

    }

}
