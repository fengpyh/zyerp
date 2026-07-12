package com.cryptomind.trading.utils;

import org.apache.commons.codec.binary.Base64;


import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {

	/** 指定key的大小 */
	private final static int KEYSIZE = 2048;
	/**
     * RSA最大加密明文大小
     */
    //private static final int   MAX_ENCRYPT_BLOCK   = 117;
    public static final String CHAR_ENCODING = "UTF-8";
    public static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
	/**
	 * 生成密钥对
	 */

	public static KeyDto getKeyPair(String seed) throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom(seed.getBytes());

		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.encodeBase64(publicKeyBytes), CHAR_ENCODING);
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.encodeBase64(privateKeyBytes), CHAR_ENCODING);

		KeyDto kpDto = KeyDto.builder()
				.publicKey(pub)
				.privateKey(pri)
				.aesPrivateKey("816d1ff3373315075960e227db180b24")
				.build();
		return kpDto;
	}

	public static Map<String, String> generateKeyPair() throws Exception {
		return generateKeyPair("test");
	}

	public static Map<String, String> generateKeyPair(String seed) throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom(seed.getBytes());

		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.encodeBase64(publicKeyBytes), CHAR_ENCODING);
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.encodeBase64(privateKeyBytes), CHAR_ENCODING);

		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.encodeBase64(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public static byte[] encrypt2Bytes(String source, String privateKey)
			throws Exception {
		Key key = getPrivateKey(privateKey);
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		//return new String(Base64.encodeBase64(b1),
		//		CHAR_ENCODING);
		return b1;
	}
	
	public static String encrypt(String source, String privateKey)
			throws Exception {
		//Key key = getPrivateKey(privateKey);
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		//Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		//cipher.init(Cipher.ENCRYPT_MODE, key);
		//byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = encrypt2Bytes(source, privateKey);
		return new String(Base64.encodeBase64(b1),
				CHAR_ENCODING);
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static byte[] decrypt(byte[] cryptograph, String publicKey)
			throws Exception {
		Key key = getPublicKey(publicKey);
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		//byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(cryptograph);
		return b;
	}
	
	
	public static String decrypt(String cryptograph, String publicKey)
			throws Exception {
		byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
		byte[] new_b1= decrypt(b1, publicKey);
		return new String(new_b1);
	}

	/**
	 * 得到公钥
	 *
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 *
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static String sign(String content, String privateKey) {
		String charset = CHAR_ENCODING;
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decodeBase64(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance("SHA1WithRSA");

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {

		}

		return null;
	}


    /**
     *
     * @param data
     * @param publicKey
     * @param sign base64字符串
     * @return
     * @throws Exception
     */
    public static boolean verifySign(byte[] data, PublicKey publicKey, String sign) throws Exception {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(Base64.decodeBase64(sign));
    }



    /**
     *
     * @param data
     * @param privateKey
     * @return base64字符串
     * @throws Exception
     */
    public static String sign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(data);

        return new String(Base64.encodeBase64(signature.sign()));

    }

}