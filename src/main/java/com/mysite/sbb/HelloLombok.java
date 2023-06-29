package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import lombok.Setter;

@Getter
@RequiredArgsConstructor
//@Setter
public class HelloLombok {
	//롬복의 @Required생성자로 사용시 생성자에 입력할 필드변수는 final을 적용
//	private String hello;
//	private int lombok;
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("hello", 5);
//		HelloLombok helloLombok = new HelloLombok();
//		helloLombok.setHello("Hello");
//		helloLombok.setLombok(5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}

}
