package com.syam.quizapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuizDto {
    private Integer id;
    private String title;
    private List<QuestionDto> questions;

    public QuizDto(Integer id, String title, List<QuestionDto> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }
}
