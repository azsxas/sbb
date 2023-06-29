package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
	//관리자 또는 유저
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	private String value;

	private UserRole(String value) {
		this.value = value;
	}
}
