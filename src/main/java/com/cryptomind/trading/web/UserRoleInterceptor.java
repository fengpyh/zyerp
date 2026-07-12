package com.cryptomind.trading.web;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cryptomind.dto.UserDto;
import com.cryptomind.trading.dto.Consts;
import com.cryptomind.trading.service.UserService;
import com.fastfintech.sdk.util_auth.AuthSsoInterceptorFunc;
import com.fastfintech.sdk.util_auth.Id_Role;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserRoleInterceptor extends HandlerInterceptorAdapter {


	@Value("${project.secure.rsaPublicKey}")
	private String rsaPublicKey;
	
	@Value("${project.secure.rsaPrivateKey}")
	private String rsaPrivateKey;
	
	@Value("${project.secure.aesPrivateKey}")
	private String aesKey;

	@Resource
	private UserService userService;

	private AuthSsoInterceptorFunc authFunc;
		
	@PostConstruct
	private void initPostConstruct() {
		authFunc = new AuthSsoInterceptorFunc(rsaPublicKey, rsaPrivateKey, aesKey);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String uri = request.getRequestURI();

		if(uri.endsWith("jpg") || uri.endsWith("css") || uri.endsWith("js")) {
			return super.preHandle(request, response, handler);
		}

		Id_Role idRole = authFunc.parseToken_fromCookie(request);
		UserDto userDto = userService.getByUserIdRoleId(idRole.getUserId());
		if(userDto!=null) {
			idRole.setEmail(userDto.getEmail());
		}

		log.info("uri={}, userId={}, token={}", uri, idRole!=null?idRole.getUserId():0, idRole.getToken()!=null?idRole.getToken().length():0);

		authFunc.reflectSetparam(request, Consts.HEADER_USER_ID, idRole.getUserId()+"", Consts.HEADER_ACCESS_TOKEN, idRole.getToken());
		authFunc.reflectSetparam(request, Consts.HEADER_USER_EMAIL, idRole.getEmail(), Consts.HEADER_ACCESS_TOKEN, idRole.getToken());
		//request.setAttribute(BaseApi.X_TY_USER_ID, tokenDto.getUserId()+"");
		//request.setAttribute(BaseApi.X_TY_ACCESS_TOKEN, token);

		//MyTokenHttpServletRequest req = new MyTokenHttpServletRequest(request);
		//req.addHeader(BaseApi.X_TY_USER_ID, userId+"");
		//req.addHeader(BaseApi.X_TY_ACCESS_TOKEN, token);
		//log.info("uri={}, tokenDto={}", uri, tokenDto);

		//MutableHttpServletRequest request1 = new MutableHttpServletRequest(request);
		//request1.putHeader(BaseApi.X_TY_USER_ID, tokenDto.getUserId()+"");
		//request1.putHeader(BaseApi.X_TY_ACCESS_TOKEN, token);
		
		if(idRole==null || idRole.getUserId()==null || idRole.getUserId()<=0L) {
			response.sendRedirect(request.getContextPath() +"/login");
			return false;
		}else {
			return super.preHandle(request, response, handler);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//log.info("========= postHandle ===========");
		//Cookie c = new Cookie("ty_tag", "server_v1");
		//c.setPath("/");
		//c.setMaxAge(7*60*60*24);
		//response.addCookie(c);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
	}
}
