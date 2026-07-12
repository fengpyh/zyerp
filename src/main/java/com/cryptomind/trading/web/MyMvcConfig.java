package com.cryptomind.trading.web;

//import java.util.Locale;

//import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	@Resource
	private UserRoleInterceptor userRoleInterceptor;


	/*
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//registry.addMapping("/**").allowedOrigins("*");

		//registry.addMapping("/**").allowedOrigins("http://localhost");
		//registry.addMapping("/**").allowedOrigins("http://127.0.0.1:8080");
		registry.addMapping("/**").allowedOrigins("http://127.0.0.1:8087");
		registry.addMapping("/**").allowedOrigins("http://127.0.0.1:31127");
	}

	 */


	@Bean
	public LocaleChangeInterceptor localeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}

	
	//@Bean
	//public LocaleResolver localeResolver() {
	//	return new CookieLocaleResolver();
	//}
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	    //sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
	    //sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
	    return sessionLocaleResolver;
	}

	private static String[] user_role_add_patterns = new String[] {
			"/**",
	};

	private static String[] exclude_patterns = new String[] {
			"/vendor/**",
			"/js/**",
			"/css/**",
			"/img/**",
			"/scss/**",
			"/api/sign/in",
			"/api/sign/generate",
			"/sign/login",
			"/api/balance/query",
			"/api/balance/push",
			"/api/balance/pushBase",
			"/api/balance/monitor",
			"/api/quote/**",
			"/spot/**",
			"/api_key/findByApiKey",
			
			"/api/user/login",
			"/login"
			//"/api/sign/out",
			//"/api/sign/send_emai_code",
			//"/api/sign/verify_emai_code",
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
		registry.addInterceptor(userRoleInterceptor).addPathPatterns(user_role_add_patterns).excludePathPatterns(exclude_patterns);
	}
}