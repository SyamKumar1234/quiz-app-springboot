package com.syam.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syam.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value="SELECT * FROM question where category=:category ORDER BY RANDOM() LIMIT :qCount", nativeQuery = true)
    List<Question> createRandomQuestionsByCategory(String category, Integer qCount);
    
}  


