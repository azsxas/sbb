package com.mysite.sbb.answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.question.Question;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
//	List<Answer> findAllByQuestion(Question question);
}
