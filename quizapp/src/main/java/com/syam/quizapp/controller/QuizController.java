package com.syam.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syam.quizapp.model.QuizResponse;
import com.syam.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam Integer qCount) {
        return quizService.createQuiz(title, category,qCount);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer quizId, @RequestBody List<QuizResponse> quizResponse) {
        System.out.println(quizResponse);
        return quizService.calculateResult(quizId, quizResponse);
    }
}
