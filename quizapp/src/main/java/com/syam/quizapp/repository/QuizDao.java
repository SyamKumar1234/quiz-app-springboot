package com.syam.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syam.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
