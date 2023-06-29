package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping("/")
//@RequestMapping("/sbb")
//@ResponseBody
public String index() {
//	public void index() {
	//System.out.println("index");
	//return "안녕하세요 sbb에 오신 것을 환영합니다!";
	return "redirect:/question/list";
}
}
