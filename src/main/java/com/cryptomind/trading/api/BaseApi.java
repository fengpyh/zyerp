package com.cryptomind.trading.api;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cryptomind.trading.dto.Consts;
import com.cryptomind.trading.dto.Id_Role;
import com.cryptomind.trading.web.MutableHttpServletRequest;
import com.fastfintech.sdk.util.ExceptionUtil;

import java.util.Map;

@Slf4j
public class BaseApi {
	//public static final String X_TY_USER_ID = "X-TY-USER-ID";
    //public static final String X_TY_ACCESS_TOKEN = "accessToken";

    protected Long getUserId(HttpServletRequest request) {
    	String value = null;
        try {
            if(request instanceof MutableHttpServletRequest) {
                MutableHttpServletRequest v = (MutableHttpServletRequest)request;
                value = v.getHeader(Consts.HEADER_USER_ID);
                Long userId = Long.parseLong(value);
                return userId;
            }else{
            	value = request.getHeader(Consts.HEADER_USER_ID);
                return Long.parseLong(value);
            }

        }catch(Exception e) {
            log.error("ERROR.getUserId.parseLong: {}, {}", value, e.getMessage());
            return 0L;
        }
    }
    
    
    protected Id_Role getId(HttpServletRequest request) {
    	try {
            if(request instanceof MutableHttpServletRequest) {
                MutableHttpServletRequest v = (MutableHttpServletRequest)request;
                String value = v.getHeader(Consts.HEADER_USER_ID);
                Long userId = Long.parseLong(value);
                
                String email = v.getHeader(Consts.HEADER_USER_EMAIL);
                
                Id_Role idrole =  Id_Role.builder()
						.userId(userId)
						.roleId(0)
						.email(email)
						.build();
                return idrole;
            }else{
            	String value = request.getHeader(Consts.HEADER_USER_ID);
            	Long userId = Long.parseLong(value);
            	String email = request.getHeader(Consts.HEADER_USER_EMAIL);
                
                Id_Role idrole =  Id_Role.builder()
						.userId(userId)
						.roleId(0)
						.email(email)
						.build();
                return idrole;
            }
        }catch(Exception e) {
            log.error("ERROR.getUserId.parseLong: {}",e.getMessage());
            
        }
    	return Id_Role.builder()
				.userId(0L)
				.roleId(0)
				.email("--")
				.build();
    }
    
    protected String getEmail(HttpServletRequest request) {
    	String value = null;
        try {
            if(request instanceof MutableHttpServletRequest) {
                MutableHttpServletRequest v = (MutableHttpServletRequest)request;
                value = v.getHeader(Consts.HEADER_USER_EMAIL);
                return value;
            }else{
            	value = request.getHeader(Consts.HEADER_USER_EMAIL);
                return value;
            }

        }catch(Exception e) {
            log.error("ERROR.getUserId.parseLong: {}, {}", value, e.getMessage());
            return "";
        }
    }

    protected String getToken(HttpServletRequest request) {
        try {
            if(request instanceof MutableHttpServletRequest) {
                MutableHttpServletRequest v = (MutableHttpServletRequest)request;
                return v.getHeader(Consts.HEADER_ACCESS_TOKEN);
            }else{
                return request.getHeader(Consts.HEADER_ACCESS_TOKEN);
            }

        }catch(Exception e) {
            log.error(ExceptionUtil.toString(e));
            return null;
        }
    }

    protected void addCookie(HttpServletResponse response, Map<String,Object> data) {
        for(Map.Entry<String,Object> e: data.entrySet()) {
            String k = e.getKey();
            String v = e.getValue().toString();
            if(k.startsWith("verify_status")) {
                continue;
            }
            Cookie c = new Cookie(k, v);
            c.setPath("/");
            c.setMaxAge(7*60*60*24);
            response.addCookie(c);
        }
    }

    protected void addCookie(HttpServletResponse response, Map<String,Object> data, String path, String domain, Integer maxAge) {
        for(Map.Entry<String,Object> e: data.entrySet()) {
            String k = e.getKey();
            String v = e.getValue().toString();
            if(k.startsWith("verify_status")) {
                continue;
            }
            Cookie c = new Cookie(k, v);
            c.setPath(path);
            c.setMaxAge(maxAge);
            c.setDomain(domain);
            response.addCookie(c);
        }
    }
}
