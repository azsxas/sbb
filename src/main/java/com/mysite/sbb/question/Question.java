package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)//오토인크리스
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")//글자 수에 제한이 없음
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> aList;
	
	@ManyToOne//글쓴이는 한명이지만 여러개의 글을 적을 수 있다.
	private SiteUser author;//글쓴이 추가
	
	private LocalDateTime modifyDate;
	
    @ManyToMany
    Set<SiteUser> voter;
}
