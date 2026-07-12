package com.cryptomind.trading.utils;



import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;


@Slf4j
public class AESUtil {

	
	public static void main(String[] args) {
        String content = "test";
        String password = "816d1ff3373315075960e227db180b24";
        //加密
        System.out.println("加密前：" + content);
        String encryptResultStr = AESEncode(password, content).get();
        System.out.println("加密后：" + encryptResultStr);
        //解密
        String decryptResultStr = AESDecode(password, encryptResultStr).get();
        System.out.println("解密后：" + decryptResultStr);

	}
	  /**
	  * 随机生成秘钥
	  */
	  public static String getKey() {
	    try {
	      KeyGenerator kg = KeyGenerator.getInstance("AES");
	      kg.init(128);
	      //要生成多少位，只需要修改这里即可128, 192或256
	      SecretKey sk = kg.generateKey();
	      byte[] b = sk.getEncoded();
	      String s = byteToHexString(b);
	      log.info(s);
	      return s;
	      //System.out.println("十六进制密钥长度为"+s.length());
	      //System.out.println("二进制密钥的长度为"+s.length()*4);
	    }catch (Exception e) {
	      log.info(ExceptionUtil.toString(e));
	    }
	    return null;
	  }

	  /**
	  * byte数组转化为16进制字符串
	  * @param bytes
	  * @return
	  */
	  public static String byteToHexString(byte[] bytes) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < bytes.length; i++) {
	      String strHex=Integer.toHexString(bytes[i]);
	      if(strHex.length() > 3) {
	        sb.append(strHex.substring(6));
	      } else {
	        if(strHex.length() < 2) {
	          sb.append("0" + strHex);
	        } else {
	          sb.append(strHex);
	        }
	      }
	    }
	    return sb.toString();
	  }

    public static Optional<String> AESEncode(String key, String content){
        try {
            return Optional.of(AESUtil.parseByte2HexStr(Objects.requireNonNull(AESEncode(key, content.getBytes()))));
        } catch (Exception e) { }
        return Optional.empty();
    }

    public static Optional<String> AESDecode(String key, String content){
        try {
            return Optional.of(new String(Objects.requireNonNull(AESDecode(key, AESUtil.parseHexStr2Byte(content)))));
        } catch (Exception e) { }
        return Optional.empty();
    }


	public static byte[] AESEncode(String encodeRules,byte[] contentBytes){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            
            keygen.init(128, random);
              //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
              //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            //byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(contentBytes);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            //String AES_encode=new String(Base64.encode(byte_AES), "utf-8");
            //11.将字符串返回
            return byte_AES;
        } catch (Exception e) {
        	log.info( "AESEncode" + "error");
//        	log.error("error AESEncode, content={}, bytes_length={}, {}",encodeRules, contentBytes!=null?contentBytes.length:0,e.getMessage());
            //log.error(ExceptionUtil.toString(e));
        }
        return null;         
    }
    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static byte[] AESDecode(String encodeRules, byte[] contentBytes){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            
            keygen.init(128, random);
              //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
              //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            //String byte_content= Base64.decode(content);
  
            byte [] byte_decode=cipher.doFinal(contentBytes);
            //String AES_decode=new String(byte_decode,"utf-8");
            return byte_decode;
        } catch (Exception e) {
        	log.info( "AESDecode" + "error");
        }
        return null;         
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
