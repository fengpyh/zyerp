package com.cryptomind.trading.service;


import java.util.List;

import com.cryptomind.entity.UserBase;
import com.cryptomind.trading.api_request.LoginParam;
import com.cryptomind.trading.api_request.UserLoginResponse;
import com.cryptomind.dto.RegisterParam;
import com.cryptomind.dto.UserDto;


public interface UserService {

	UserLoginResponse register(RegisterParam registerParam);

	UserDto getByUserIdRoleId(Long userId);
	
	UserLoginResponse login(LoginParam loginParam);

	
	
	
	
	List<UserDto> findAll();
}
