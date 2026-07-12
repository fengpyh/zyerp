package com.cryptomind.trading.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cryptomind.entity.UserBase;
import com.cryptomind.trading.api_request.LoginParam;
import com.cryptomind.trading.api_request.RegisterParam;
import com.cryptomind.trading.api_request.UserLoginResponse;
import com.cryptomind.trading.dto.UserDto;
import com.cryptomind.trading.service.UserService;
import com.fastfintech.sdk.util.ExceptionUtil;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;


@Slf4j
@RestController
@RequestMapping(value="/api/user")
public class UserApi extends BaseApi {

	@Resource
	private UserService userService;

	@Autowired
	HttpServletRequest request;

	private Gson gson = new Gson();

	@PostMapping("/register")
	public @ResponseBody
	ResponseEntity<UserLoginResponse> register(@RequestBody RegisterParam registerParam) {
		log.info("registerParam={}", registerParam);
		
		UserLoginResponse ulr = new UserLoginResponse();
		ulr.setStatus(-1);
		ulr.setUser(null);
		ulr.setToken("-");
		ulr.setMsg("ERROR");
		return ResponseEntity.ok(ulr);
		
		/*
		try {
			UserLoginResponse user = userService.register(registerParam);
			return ResponseEntity.ok(user);
					
		} catch (Exception e) {
			log.error("error={}", ExceptionUtil.toString(e));
			
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(-1);
			ulr.setUser(null);
			ulr.setToken("-");
			ulr.setMsg("ERROR");
			return ResponseEntity.ok(ulr);
		}
		*/
	}

	@PostMapping("/login")
	public @ResponseBody
	ResponseEntity<UserLoginResponse> login(@RequestBody LoginParam loginParam) {
		log.info("loginParam={}", loginParam);

		//String content= authClient.auth(loginParam.getEmail(), loginParam.getPassword());
		//UserLoginResponse user= gson.fromJson(content, UserLoginResponse.class);
		//return ResponseEntity.ok(user);
		
		try {
			UserLoginResponse user = userService.login(loginParam);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			log.error("error={}", ExceptionUtil.toString(e));
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(-1);
			ulr.setUser(null);
			ulr.setToken("-");
			return ResponseEntity.ok(ulr);
		}
		
	}

	/*
	@PostMapping("/info")
	public @ResponseBody
	ResponseEntity<UserDto> userinfo() {

		try {
			Long userId = getUserId(request);

			UserDto user = null;
			if(userId!=null && userId>0L) {
				user = userService.getByUserIdRoleId(userId);
				//user.setId(0L);
			}
			return ResponseEntity.ok(user);
		
		} catch (Exception e) {
			log.error("error={}", ExceptionUtil.toString(e));
			UserDto user = UserDto.builder()
					.id(-1L)
					.email("")
					.nick_name("")
					.phone("")
					.roleId(0)
					.build();
			return ResponseEntity.ok(user);
		}

	}
	*/
	
	/*
	@GetMapping("/list")
	public @ResponseBody
	ResponseEntity<List<UserDto>> api_user_list() {
		//TODO 要检查权限
		Long userId = getUserId(request);
		try {
			List<UserDto> user = userService.findAll();
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			log.error("error={}", ExceptionUtil.toString(e));
			UserDto user = UserDto.builder()
					.id(-1L)
					.email("")
					.nick_name("")
					.phone("")
					.roleId(0)
					.build();
			List<UserDto> list = new ArrayList<>(1);
			list.add(user);
			return ResponseEntity.ok(list);
		}

	}*/
}
