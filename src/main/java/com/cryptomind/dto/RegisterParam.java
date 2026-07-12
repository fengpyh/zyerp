package com.cryptomind.dto;

import lombok.Data;

@Data
public class RegisterParam {

	private String nickName;
	private String email;
	private String phone;
	private String password;
	private String repeatPassword;
	
	
	private String code_text;
	
}
