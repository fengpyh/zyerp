package com.cryptomind.trading.web;


import com.cryptomind.dto.UserDto;
import com.cryptomind.trading.dto.Consts;
import com.cryptomind.trading.dto.Id_Role;
import com.cryptomind.trading.service.UserService;
import com.cryptomind.trading.utils.ExceptionUtil;
import com.cryptomind.trading.utils.TokenUtil;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
//import javax.annotation.Resource;
//import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.util.Optional;
//import java.util.HashMap;
//import java.util.Map;

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
	
	//private AuthSsoFacade authClient = new AuthSsoFacade("http://localhost:9096");
	private Gson gson = new Gson();
	
	private String getToken_fromCooike(HttpServletRequest request) {
		String token  = request.getHeader("Authorization");
		//log.info("Authorization:{}", token!=null?token.length():0);
		if(token!=null) {
			return token;
		}

		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if("accessToken".equals(cookie.getName())) {
					try {
						token = cookie.getValue();
						token = URLDecoder.decode(token,"utf-8");
						//break;
					}catch(Exception e) {
						log.info("cookie.error={}",e.getMessage());
					}
				}
			}
		}
		log.info("cookies:{}", token!=null?token.length():"");
		return token;
	}

	private final static String SAMPLE_TOKEN = "sM9d2K8PqMZfFB9a0astBAVjeKAVn4P6T1spd3WEmYGxXLH4tghEBpMWnCdcvguR4Bxa4ZeeWs/3YFZbcmLHcPwCMsBbSA5j3OFXRLZ1Tl9E28nG9h7/BFYK0wuh0Ng3QHjR/ef4+au7WwIYWr48fXsVn6dwdK2dtLF39B1VJYaAqD6KP2AoZvcn5bnCgBGlgWGb8nGckJ7d0bn4wuJLDD3a533TtJjjNqnul6NPGv2mSMREk4tiKopQoFaI1rdtLYxkT8hHiWSs+YXQEraiGCJI/KQuxHEQ4oyFGQZX14RBkeITC9kdAdf036sgj3mUmCs+FWoZuFzvTvW3Mc+0KTlpsCkDkrdFpHzB/ApF/Lw=";

	private Id_Role parseToken_fromCookie(String token) {
		//TokenUserDto tokenDto = null;
				try {
					//log.info("ty_token={}", token);
					if(token!=null && !token.isEmpty()) {
						try {
							//tokenDto = RSATokenUtil.parseToken(token);
							//String tokenStr = TokenUtil.rasDecryptAndAesVerifySign(token, rsaPublicKey, aesKey);
							//log.info("RSATokenUtil.rasDecryptAndAesVerifySign(), {}, {}", tokenStr, token);
							//String[] arr = tokenStr.split(",");
							//String id_str = arr[0];
							//String role_id_str = arr[1];

							//token = URLEncoder.encode(token,"UTF-8");
							//Optional<UserDto> userDtoOpt =userAuthService.apiUserTokenParse(token);
							log.info("AUTH token : {}", token);
							//String content = authClient.tokenAuth(token);
							//UserDto user = gson.fromJson(content, UserDto.class);
							//log.info("AUTH userDto : {}", user);
							UserDto user = null;
							
							
							if(user==null) {
								String tokenStr = TokenUtil.rasDecryptAndAesVerifySign(token, rsaPublicKey, aesKey);
								log.info("AUTH tokenParse.RSATokenUtil.rasDecryptAndAesVerifySign().3, tokenStr= {}", tokenStr);
								if(tokenStr==null && token.length()> SAMPLE_TOKEN.length()) {
									token = URLDecoder.decode(token, "UTF-8");
									tokenStr = TokenUtil.rasDecryptAndAesVerifySign(token, rsaPublicKey, aesKey);
									log.warn("AUTH tokenParse.RSATokenUtil.rasDecryptAndAesVerifySign().4, #### tokenStr= {}", tokenStr);
								}
	
								String[] arr = tokenStr.split(",");
								String id_str = arr[0];
								//String role_id_str = arr[1];
	
								Long userId=0L;
								//Integer roleId= 0;
								if(id_str!=null) {
									userId = Long.parseLong(id_str);
								}
	
								//if(role_id_str!=null) {
								//	roleId = Integer.parseInt(role_id_str);
								//}
								user = userService.getByUserIdRoleId(userId);
								user.setId(userId);
								user.setRoleId(0);
								log.info("AUTH userDto2: {}", user);
							}
							return Id_Role.builder()
									.userId(user.getId())
									.roleId(0)
									.email(user.getEmail())
									.build();
							
						}catch(Exception e) {
							log.info("AUTH token.error={}",e.getMessage());
						}
					}else{
						log.warn("AUTH token null");
					}
				} catch (Exception e) {
					log.error("AUTH error={}", ExceptionUtil.toString(e));
				}
				log.info("AUTH ID: {}, {}", 0, 0);
				return Id_Role.builder()
						.userId(0L)
						.roleId(0)
						.build();
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String uri = request.getRequestURI();

		if(uri.endsWith("jpg") || uri.endsWith("css") || uri.endsWith("js")) {
			return super.preHandle(request, response, handler);
		}


		String token = getToken_fromCooike(request);
		Id_Role tokenDto = parseToken_fromCookie(token);

		log.info("uri={}, userId={}, token={}", uri, tokenDto!=null?tokenDto.getUserId():0, token!=null?token.length():0);

		reflectSetparam(request, Consts.HEADER_USER_ID, tokenDto.getUserId()+"", Consts.HEADER_ACCESS_TOKEN, token);
		reflectSetparam(request, Consts.HEADER_USER_EMAIL, tokenDto.getEmail(), Consts.HEADER_ACCESS_TOKEN, token);
		//request.setAttribute(BaseApi.X_TY_USER_ID, tokenDto.getUserId()+"");
		//request.setAttribute(BaseApi.X_TY_ACCESS_TOKEN, token);

		//MyTokenHttpServletRequest req = new MyTokenHttpServletRequest(request);
		//req.addHeader(BaseApi.X_TY_USER_ID, userId+"");
		//req.addHeader(BaseApi.X_TY_ACCESS_TOKEN, token);
		//log.info("uri={}, tokenDto={}", uri, tokenDto);

		//MutableHttpServletRequest request1 = new MutableHttpServletRequest(request);
		//request1.putHeader(BaseApi.X_TY_USER_ID, tokenDto.getUserId()+"");
		//request1.putHeader(BaseApi.X_TY_ACCESS_TOKEN, token);
		
		if(tokenDto==null || tokenDto.getUserId()==null || tokenDto.getUserId()<=0L) {
			response.sendRedirect(request.getContextPath() +"/login");
			return false;
		}else {
			return super.preHandle(request, response, handler);
		}
		
		
		

		/*
		if(userId!=null && userId>0) {
			if(tokenDto.getStatus()!= TokenStatusConstants.LOGIN_EMAIL_CODE_VERIFIED.intValue()) {
				if(uri.startsWith("/api/sign/send_emai_code") || uri.startsWith("/api/sign/verify_emai_code")  || uri.startsWith("/sign/login_emailcode") ) {
					return super.preHandle(request, response, handler);
				}else{
					if(uri.startsWith("/api")) {
						returnJson(request,response);
					}else{
						response.sendRedirect(request.getContextPath() +"/sign/login");
					}
				}
			}else{
				return super.preHandle(request, response, handler);
			}
		}else{
			if(uri.startsWith("/api")) {
				returnJson(request,response);
			} else {
				response.sendRedirect(request.getContextPath() +"/sign/login");
			}
		}
		return true;
		*/
	}

	private Field reflectSetparam_getField(String api, Class requestClass, String name) {
		Field request1 = null;
		try{
			request1 = requestClass.getDeclaredField(name);
		}catch (Exception e){
			//log.error("Error, reflectSetparam_getField, msg={}", ExceptionUtil.toString(e));
			//log.error("Error, reflectSetparam_getField, msg={}", e.getMessage());
			Field[] fields = requestClass.getDeclaredFields();
			for(Field field: fields) {
				//log.info("field={}", field.getName());
				//log.error("error.reflectSetparam_getField, {}, reflectSetparam_getField, msg={}, name={}, field={}", api, e.getMessage(), name, field.getName());
				if(field.getName().equals(name)) {
					request1 = field;
					//break;

				}
			}

		}

		return request1;
	}

	private void reflectSetparam(HttpServletRequest request,String key,String value, String key2, String value2){

		String uri = request.getRequestURI();
		//System.out.println("request实现类="+requestClass.getName());
		try {
			Class<? extends HttpServletRequest> requestClass = request.getClass();
			//log.info("requestClass={}", requestClass);

			Field request1 = reflectSetparam_getField(uri, requestClass, "request"); //requestClass.getDeclaredField("request");
			if(request1==null) {
				return;
			}

			request1.setAccessible(true);
			Object o = request1.get(request);

			///Field coyoteRequest = o.getClass().getDeclaredField("coyoteRequest");
			Field coyoteRequest = reflectSetparam_getField(uri, o.getClass(), "coyoteRequest");
			if(coyoteRequest==null) {
				return;
			}
			coyoteRequest.setAccessible(true);
			Object o1 = coyoteRequest.get(o);
			//System.out.println("coyoteRequest实现类="+o1.getClass().getName());
			//Field headers = o1.getClass().getDeclaredField("headers");
			Field headers = reflectSetparam_getField(uri, o1.getClass(), "headers");
			if(headers==null) {
				return;
			}

			headers.setAccessible(true);
			MimeHeaders o2 = (MimeHeaders)headers.get(o1);
			o2.addValue(key).setString(value);
			o2.addValue(key2).setString(value2);
			//o2.addHeader(key,value);
			//o2.addHeader(key2,value2);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ERROR, {}", ExceptionUtil.toString(e));
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

	//private Gson gson = new Gson();
	/*
	private void returnJson(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			writer = response.getWriter();
			Map<String, Object> result = new HashMap<>();
			result.put("data", null);
			ApiResponse a = new ApiResponse(request.getRequestURI(), 0, 500, result);
			String content = gson.toJson(a);
			writer.print(content);
		} catch (Exception e){
			log.error("error={}", ExceptionUtil.toString(e));
		} finally {
			if(writer != null){
				writer.close();
			}
		}
	}*/
}
