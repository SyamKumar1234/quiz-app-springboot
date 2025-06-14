package com.syam.quizapp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syam.quizapp.dao.QuestionDao;
import com.syam.quizapp.dao.QuizDao;
import com.syam.quizapp.dto.QuestionDto;
import com.syam.quizapp.dto.QuizDto;
import com.syam.quizapp.model.Question;
import com.syam.quizapp.model.Quiz;
import com.syam.quizapp.model.QuizResponse;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String title, String category, Integer qCount) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            List<Question> questions = questionDao.createRandomQuestionsByCategory(category, qCount);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create Quiz", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getQuiz(Integer id) {
        try {
            Optional<Quiz> optionalQuiz = quizDao.findById(id);
            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                List<Question> questionsFromDb = quiz.getQuestions();
                List<QuestionDto> questionsForUser = questionsFromDb.stream().map(q -> {
                    QuestionDto questionDto = new QuestionDto(q.getId(), q.getCategory(), q.getDifficulty(),
                            q.getOption1(), q.getOption2(), q.getOption3(), q.getTitle());
                    return questionDto;

                }).collect(Collectors.toList());
                QuizDto quizDto = new QuizDto(quiz.getId(), quiz.getTitle(), questionsForUser);
                return new ResponseEntity<>(quizDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Quiz not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<QuizResponse> quizResponse) {
        try {
            Optional<Quiz> quizFromDb = quizDao.findById(quizId);
            if (quizFromDb.isPresent()) {
                Quiz quiz = quizFromDb.get();
                List<Question> questions = quiz.getQuestions();
                Map<Integer, String> correctAnswers = questions.stream().collect(Collectors.toMap(Question::getId, Question::getAnswer));
                Integer rightAnswers = 0;
                for(QuizResponse response: quizResponse) {
                    if(response.getAnswer().equals(correctAnswers.get(response.getQuestionId()))) {
                        rightAnswers++;
                    }
                }
                return new ResponseEntity<>(rightAnswers, HttpStatus.OK ) ;
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
