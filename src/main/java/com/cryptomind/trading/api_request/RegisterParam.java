package com.cryptomind.trading.api_request;

import lombok.Data;

@Data
public class RegisterParam {

	private String nickName;
	private String email;
	private String phone;
	private String password;
	private String repeatPassword;
	
	
}
