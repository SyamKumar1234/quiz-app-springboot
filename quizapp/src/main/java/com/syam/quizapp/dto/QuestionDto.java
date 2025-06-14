package com.syam.quizapp.dto;

import lombok.Data;

@Data
public class QuestionDto {

    private Integer id;
    private String category;
    private String difficulty;
    private String option1;
    private String option2;
    private String option3;
    private String title;

    public QuestionDto(Integer id, String category, String difficulty, String option1, String option2, String option3,
            String title) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.title = title;
    }

}
