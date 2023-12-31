package com.mysite.sbb.question;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.answer.AnswerService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

@Controller
@RequestMapping("/question")
public class QuestionController {
//	@Autowired
//	private QuestionRepository qRepo;

	@Autowired
	private QuestionService qService;
	
	@Autowired
	private UserService uService;
	

   //RequestMapping(get,post 둘 다 포함)
//@RequestMapping("/question/list")
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page" , defaultValue = "0") int page, Model model 
	 ,@RequestParam(value="serchkey", required = false) String serchkey) {
//		model.addAttribute("qList",qRepo.findAll());
//		model.addAttribute("qList", qService.getList());
		
//		Page<Question> paging = null;
//		if(serchkey == null) {
//			paging = this.qService.getList(page);
//		} else {
//			paging = this.qService.questSerch(page, serchkey);
//		}
		Page<Question> paging = this.qService.getList(page);
		model.addAttribute("paging", paging);
		return "question_list";
	}
	
//	@GetMapping("/question/detail/{id}")
	@GetMapping("/detail/{id}")
	public String detail(AnswerForm answerForm,Model model,@PathVariable("id") Integer id) {
	//	System.out.printf("id : %d \n", id);
		model.addAttribute("question", qService.getQuestion(id));
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm){
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,
			BindingResult result, Principal principal
	/*
	 * @RequestParam String subject, @RequestParam String content
	 */) {
		//질문을 저장한다.
		if(result.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = this.uService.getUser(principal.getName());
//		this.qService.create(subject,content);
		this.qService.create(questionForm.getSubject(),questionForm.getContent(), siteUser);
		return "redirect:/question/list";
	}
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.qService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.qService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.qService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.qService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.qService.delete(question);
        return "redirect:/";
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.qService.getQuestion(id);
        SiteUser siteUser = this.uService.getUser(principal.getName());
        this.qService.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
    

}
