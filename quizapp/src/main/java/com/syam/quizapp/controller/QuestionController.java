package com.syam.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syam.quizapp.model.Question;
import com.syam.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuestion(@RequestBody Question question) {
        System.out.println("Received question: " + question);
        return questionService.createQuestion(question);
    }

    @PutMapping("update/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer questionId, @RequestBody Question question) {
        System.out.println("Received question: " + question + " " + questionId);
        return questionService.updateQuestion(questionId, question);
    }

    @DeleteMapping("delete/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer questionId) {
        return questionService.deleteQuestion(questionId);
    }

}
