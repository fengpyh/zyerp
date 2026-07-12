package com.cryptomind.trading.api_request;


import com.cryptomind.entity.UserBase;

import lombok.Data;

@Data
public class UserLoginResponse {


	private Integer status;
	private String msg;
	private UserBase user;
	private String token;
	
}
