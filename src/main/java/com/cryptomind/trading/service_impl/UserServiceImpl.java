package com.cryptomind.trading.service_impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cryptomind.dao.UserBaseDao;
import com.cryptomind.dto.RegisterParam;
import com.cryptomind.dto.UserDto;
import com.cryptomind.entity.UserBase;
import com.cryptomind.trading.api_request.LoginParam;
import com.cryptomind.trading.api_request.UserLoginResponse;
import com.cryptomind.trading.service.UserService;
import com.cryptomind.trading.utils.ExceptionUtil;
import com.cryptomind.trading.utils.SaltUtil;
import com.cryptomind.trading.utils.TokenUtil;
import com.cryptomind.trading.utils.UserPasswordUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserBaseDao userBaseDao;


	@Value("${project.secure.rsaPublicKey}")
	private String rsaPublicKey;
	
	@Value("${project.secure.rsaPrivateKey}")
	private String rsaPrivateKey;
	
	@Value("${project.secure.aesPrivateKey}")
	private String aesKey;
	

	
	
	@Override
	public UserLoginResponse register(RegisterParam rp)  {
		if(!rp.getPassword().equals(rp.getRepeatPassword())) {
			log.warn("PASSWORD NOT MATCH");
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(0);
			ulr.setUser(new UserBase());
			String content = String.format("%d,%d", 0, System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			ulr.setToken(token);
			return ulr;
		}
		
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.DAY_OF_MONTH);
		int m = cal.get(Calendar.MONTH);
		int y = cal.get(Calendar.YEAR);
		y = y%10;
		m = m%10;
		d = d%10;
		char[] c = {(char)('0'+y),(char)('0'+m),(char)('0'+d),'c'};
		String code = String.valueOf(c);
		log.info("CODE: {}, {}", code, rp.getCode_text());
		if( rp.getCode_text().contentEquals(code) == false ) {
			log.warn("INVITE CODE ERROR");
			
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(0);
			ulr.setMsg("Invitation Code Error");
			ulr.setUser(new UserBase());
			String content = String.format("%d,%d", 0, System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			ulr.setToken(token);
			return ulr;
		}
		
		UserBase u = null;
		if(rp.getEmail()!=null && !rp.getEmail().trim().isEmpty()) {
			u = userBaseDao.findByEmail(rp.getEmail());
			log.info("findByEmail, email={}, user={}", rp.getEmail(), u);
			
		}
		if(rp.getPhone()!=null && !rp.getPhone().trim().isEmpty()) {
			u = userBaseDao.findByPhone(rp.getPhone());
			log.info("findByPhone, email={}, user={}", rp.getPhone(), u);
			
		}
 		
		if(u!=null && u.getId()>0) {
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(0);
			ulr.setUser(new UserBase());
			String content = String.format("%d,%d", 0, System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			ulr.setToken(token);
			ulr.setMsg("Email Duplicated!");
			return ulr;
		}
		
		try {
			UserBase user = new UserBase();
			user.setEmail(rp.getEmail()!=null?rp.getEmail():"");
			user.setPhone(rp.getPhone()!=null?rp.getPhone():"");
			user.setNick_name(rp.getNickName()!=null?rp.getNickName():"");
			user.setPasswd_str(rp.getPassword());
			user.setCreate_time(new java.sql.Timestamp(System.currentTimeMillis()));
			user.setFrole_id(0);
			user.setSrole_id(0);
			user.setTrole_id(0);
			
			String salt = SaltUtil.generateSalt32_36();
			user.setSalt(salt);
			UserBase newUser = userBaseDao.save(user);
			
			log.info("newUser={}",newUser);
			Long userId = 0L;
			if(newUser.getId()>0) {
				userId = newUser.getId();
				String encrypt = UserPasswordUtil.encrypt_v2(newUser.getPasswd_str(), newUser.getSalt(), userId, aesKey, rsaPrivateKey);
				if(encrypt!=null) {
					newUser.setPasswd_str(encrypt);
					userBaseDao.save(newUser);
				}
			}
			
			
			
			newUser.setId(0L);
			newUser.setPasswd_str("******");
			
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(1);
			ulr.setMsg("Success");
			ulr.setUser(newUser);
			String content = String.format("%d,%d", userId, System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			
			ulr.setToken(token);
			return ulr;
		}catch(Exception e) {
			log.error(ExceptionUtil.toString(e));
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(0);
			ulr.setUser(new UserBase());
			String content = String.format("%d,%d", 0, System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			ulr.setToken(token);
			return ulr;
		}
	}


	@Override
	public UserDto getByUserIdRoleId(Long userId){
		//log.info("token={}",token);
		UserDto userDto = null;
		UserBase u = null;
		Optional<UserBase> userOpt = userBaseDao.findById(userId);
		if(userOpt.isPresent()) {
			u = userOpt.get(); //userBaseDao.findById(userId).get();
			userDto = UserDto.builder().id(u.getId())
					.email(u.getEmail())
					.phone(u.getPhone())
					.nick_name(u.getNick_name())
					.create_time(u.getCreate_time())
					.vip_level(0)
					.roleId(0).build();
		}else {
			userDto = UserDto.builder().id(-1L)
					.email("")
					.phone("")
					.nick_name("")
					.sig("")
					.vip_level(0)
					.create_time(new java.sql.Timestamp(System.currentTimeMillis()))
					.roleId(0).build();
		}
		
		return userDto;
	}

	@Override
	public List<UserDto> findAll() {
		List<UserBase> list = userBaseDao.findAll();
		
		List<UserDto> dto_list = new ArrayList<>(list.size() );
		for(UserBase u : list) {
			UserDto userDto = UserDto.builder().id(u.getId())
					.email(u.getEmail())
					.phone(u.getPhone())
					.nick_name(u.getNick_name())
					.create_time(u.getCreate_time())
					.vip_level(0)
					.id(u.getId())
					.roleId(0)
					.build();
			//userDto.setId(0L);
			dto_list.add(userDto);
		}
		
		return dto_list;
	}

	@Override
	public UserLoginResponse login(LoginParam loginParam)  {
		UserBase u = userBaseDao.findByEmail(loginParam.getEmail());
		log.info("findByEmail, email={}, user={}", loginParam.getEmail(), u);
		
		String password = UserPasswordUtil.decrypt_v2(u.getPasswd_str(), aesKey, rsaPublicKey);
		
		if(u!=null && u.getId()>0 && password!=null && password.equals(loginParam.getPassword())) {
			Long userId = u.getId();
		
			u.setId(0L);
			u.setPasswd_str("******");
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setUser(u);
			ulr.setStatus(1);
			
			Integer roleId = u.getFrole_id();//+u.getSrole_id()*10 + u.getTrole_id()*100+100000;

			String content = String.format("%d,%d,%d", userId,roleId,System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			ulr.setToken(token);
			return ulr;
		}else {
			log.info("login,error,param={}", loginParam);
			UserLoginResponse ulr = new UserLoginResponse();
			ulr.setStatus(0);
			ulr.setUser(new UserBase());
			String content = String.format("%d,%d", 0, System.currentTimeMillis());
			String token = TokenUtil.rasEncryptAndAesSign(content, rsaPrivateKey, aesKey);
			ulr.setToken(token);
			return ulr;
		}
	}
	
	

}
