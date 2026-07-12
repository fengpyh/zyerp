package com.cryptomind.trading.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class TokenUtil {

	public static String rasEncryptAndAesSign(String content, String rasPrivateKey, String aesKey) {
		try {
			byte[] rsaContent = RSAUtils.encrypt2Bytes(content, rasPrivateKey);
			byte[] aesContent = AESUtil.AESEncode(aesKey, rsaContent);
			
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(aesContent));
		}catch(Exception e) {
			//log.error(ExceptionUtil.toString(e));
			log.error("error rasEncryptAndAesSign,content={}, {}", content, e.getMessage());
		}
		return null;
	}
	
	
	public static String rasDecryptAndAesVerifySign(String content, String rasPublicKey, String aesKey) {
		try {
			byte[] base64ContentByte = content.getBytes();
			byte[] content2 = org.apache.commons.codec.binary.Base64.decodeBase64(base64ContentByte);
			byte[] rsaContent = AESUtil.AESDecode(aesKey, content2);
			byte[] rawContent = RSAUtils.decrypt(rsaContent, rasPublicKey);
			return new String(rawContent);
		}catch(Exception e) {
			log.error("error rasDecryptAndAesVerifySign,content={}, {}",content, e.getMessage());
		}
		return null;
	}
	
	
	private static void generateKeySql() throws Exception{
		List<String> list = new ArrayList<>();
		for(int i=0;i<37;i++) {
			Map<String, String> keyPair = RSAUtils.generateKeyPair();
			String publicKey = keyPair.get("publicKey");
			String privateKey = keyPair.get("privateKey");
			
			String aesKey = AESUtil.getKey();
			
			log.info("rsaPubKey: {}", publicKey);
			log.info("privateKey: {}", privateKey);
			log.info("aesKey: {}", aesKey);
			
			String sql =  String.format("insert into kyl_internal_key(id,key1, key2, key3, create_time) values(%d,'%s','%s','%s', now());", i+1, privateKey, publicKey, aesKey);
			list.add(sql);
		}
		
		for(String s : list) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> keyPair = RSAUtils.generateKeyPair();
		String publicKey = keyPair.get("publicKey");
		String privateKey = keyPair.get("privateKey");
		
		String aesKey = AESUtil.getKey();
		
		log.info("rsaPubKey: {}", publicKey);
		log.info("privateKey: {}", privateKey);
		log.info("aesKey: {}", aesKey);
		
		String content = "123123_abcefg";
		log.info("1: {}", content);
		String a = rasEncryptAndAesSign(content, privateKey, aesKey);
		log.info("2: {}", a);
		String b = rasDecryptAndAesVerifySign(a, publicKey, aesKey);
		
		//log.info("1: {}", content);
		//log.info("2: {}", a);
		log.info("3: {}", b);
		
		
		generateKeySql();
	}
}
