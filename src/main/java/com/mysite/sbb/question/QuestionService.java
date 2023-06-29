package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository qRepo;

	// 모든 질문을 검색하여 리스트로 리턴
//	public List<Question> getList(){
	public Page<Question> getList(int page) {
		// 현재 페이지와 페이지당 10개를 입력하여 가져올 데이처를 pageable에 설정
		Pageable pageable = PageRequest.of(page, 10, Sort.by("createDate").descending());// 현재 페이지에 표시할 데이터를 가져옴,페이지당
																							// 10개의 게시물
		return this.qRepo.findAll(pageable);
	}
	
//	public Page<Question> questSerch(int page, String serchkey){
//	Pageable pageable = PageRequest.of(page, 10, Sort.by("createDate").descending());
//		return this.qRepo.findByTitle(serchkey, pageable);
//	}


	public Question getQuestion(Integer id) {
		Optional<Question> question = this.qRepo.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	// 질문을 저장한다.
	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.qRepo.save(q);
	}

	// 질문을 수정한다.
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.qRepo.save(question);
	}

	// 질문 삭제하기
	public void delete(Question question) {
		this.qRepo.delete(question);
	}
	
	//글 추천 메소드
    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        this.qRepo.save(question);
    }
}
