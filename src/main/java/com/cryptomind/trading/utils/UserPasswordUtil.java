package com.cryptomind.trading.utils;

//import com.easeft.trade.common_dto.KeyPairDto;
//import com.easeft.trade.common_dto.TokenUserDto;
//import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Optional;

@Slf4j
public class UserPasswordUtil {
    private final static String SEP = "##-#-##";
    //private static Gson gson = new Gson();


    /*
    public static String encrypt(String password, String salt, Long userId, String aesPriKey, String rsaPriKey) {
        try{
        	String content = String.format("%s%s%s%s%s", password,SEP,salt,SEP,userId);
            String rsaContent = RSAUtils.encrypt(content, rsaPriKey);
            log.info("rsaContent={}", rsaContent);
            String sign = null;
            //String salt = SaltUtil.generateSalt32_36(); //salt for sign
            Optional<String> aesSignOpt = AESUtil.AESEncode(aesPriKey, rsaContent +salt);
            if(aesSignOpt.isPresent()) {
                sign = aesSignOpt.get();
            }
            log.info("sign={}", sign);
            String token = Base64.getEncoder().encodeToString((rsaContent  +SEP + sign).getBytes() );
            log.info("abc={}", token);
            return token;
        } catch(Exception e) {
            log.error("error.encrypt,{}, {}", userId, ExceptionUtil.toString(e));
        }
        return null;
    }

    public static String decrypt(String enryptStr, String aesPriKey, String rsaPubKey) {
        try {
            String content_sign = new String(Base64.getDecoder().decode(enryptStr));
            String[] arr = content_sign.split(SEP);
            if (arr != null && arr.length == 2) {
                //TODO check sign
                //arr[1] is signature, should be check
                log.info("rsaContent={}", arr[0]);
                log.info("sign={}", arr[1]);

                String content = RSAUtils.decrypt(arr[0], rsaPubKey);
                log.info("content={}", content);
                //TokenUserDto tokenDto = gson.fromJson(content, TokenUserDto.class);
                //return tokenDto;
                String[] arr_List = content.split(SEP);
                return arr_List[0];
                

                //parse
                //Optional<String> aesContentOpt = AESUtil.AESDecode(keyPairDto.getAesPrivateKey(), content_sign);
                //if( aesContentOpt.isPresent()) {
                //    String content = RSAUtils.decrypt(arr[0], keyPairDto.getPublicKey());
                //    TokenUserDto tokenDto = gson.fromJson(content, TokenUserDto.class);
                //    return tokenDto;
                //}
            }
        }catch (Exception e) {
            log.error("error.parseToken, {}", ExceptionUtil.toString(e));
        }

        return null;
    }*/
    
    
    
    public static String encrypt_v2(String password, String salt, Long userId, String aesPriKey, String rsaPriKey) {
        try{
        	String content = String.format("%s%s%s%s%s", password,SEP,salt,SEP,userId);
            String rsaContent = RSAUtils.encrypt(content, rsaPriKey);
            //log.info("rsaContent={}", rsaContent);
            String sign = null;
            //String salt = SaltUtil.generateSalt32_36(); //salt for sign
            Optional<String> aesSignOpt = AESUtil.AESEncode(aesPriKey, rsaContent +salt);
            if(aesSignOpt.isPresent()) {
                sign = aesSignOpt.get();
            }
            //log.info("sign={}, {}", sign.length(),sign);
            String token = Base64.getEncoder().encodeToString((sign).getBytes() );
            //log.info("abc={}", token);
            return token;
        } catch(Exception e) {
            log.error("error.encrypt,{}, {}", userId, ExceptionUtil.toString(e));
        }
        return null;
    }

    public static String decrypt_v2(String enryptStr, String aesPriKey, String rsaPubKey) {
        try {
                
                //parse
        	String content_sign = new String(Base64.getDecoder().decode(enryptStr));
                Optional<String> aesContentOpt = AESUtil.AESDecode(aesPriKey, content_sign);
                if( aesContentOpt.isPresent()) {
                	String c = aesContentOpt.get();
                    String content1 = RSAUtils.decrypt(c, rsaPubKey);
                    //log.info("content1:{}", content1);
                    String[] arr = content1.split(SEP);
                    return arr[0];
                }
        }catch (Exception e) {
            log.error("error.parseToken, {}", ExceptionUtil.toString(e));
        }

        return null;
    }


    /*
    public static void main(String[] args) {
    	 String privateKey ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkuCiC5N4Uwf8qGO5bjfl2rTt4LeLrBX19YQdvTFoW+x2WpNKBVG13mF0jTI+hEUw60/FFzdPlB/7ooSy1wE0hI0VUyssofFZ6c2CI/+6DunxYGJSW+Svk6sJCY5ztuSY3jSqJCncvUWhPYNXK4g0HufiHExVem7qWt16J5LeUPmsuEqDzihSbNRdJIwILMyTti1m/1b3UuQPdejtVNaKhRPeMgiszB1656MbAJLokAP8pWGZukFvXDJvZg+BzJ45eyASYOLC7xUBI/sp//xl9q8m3fu439Xbrz6V5UHT5rLyJ7/ItsDrfhwky/zkewyZ5Ru4ukjMS7Tjv9fSb2CnvAgMBAAECggEAc+6hpK7Ml7OKkeiGKq4cPCjkyEsTgPKWRh56ix0j8zS+uH49IoV3nksAko2aMXTpQCZXlBev4WzlPnnh6/Hy1zXp4CnzHwWUIA8AjKwFBXXFeoOiYTfyauXH384Yb0B243ZZz6YsCIO1dNjk4CyFm0CK6RbHXZUhmF5rDYaKpqy+FzW6OHgvHe/uGNAbBd53FGwQ1v8DmdR3A2wlidtpO2e9WaSnCxZU5+y5Vc2qBggCBq1Ap5SkiHxk2RxyvfBnpVvvOzq3W2HTwbIjkiAhtNgjKHIiLBo93eff6v8gRuvnfgl9/Enp/nRJj7afnrgnTfqfszvduyPNm1poh8u2EQKBgQDcsltfYEeHOF33tRc+L+cqq9iPd5VDA+xH69FYuJnkjtyceLvRxShW/9R+C4ooz6nMCIvh3jDbn3Vw9P/nkM1wkkJbjysJHZ69ZkMT3Ggr1NrnBms/BbRnRGvBWEV/hFskpeT/9/+k3Pqg2icjWu1nyxOhpQi4w2LcKFIzizfM7QKBgQC/EYArfC5GWzhK9tXJWe3ELvdeyJ0KInCqc3IoHsA+kop+yhqYQb2vi/uc3Oy1eQxaFd6PIGZcK0lZU6n9mXwscPAmlNNBFhRmmv6NAFfTW6kXhk0iettt78wQ3lNpqgr4/oLILNUgLlvVcwF96LuSIqPdvM969cQZwzXuXPASywKBgQDTjqmmYN3QUolJVa/FLhhTwE2lIYXUquMKApU4jiT/gq8Z02Z/7JYB59VjSl6TRpXG/35P8zU0osPqlwDkkttMiPfRWwlvAJTVjDzKwiKdubppca7GyAoqX91Hhv2AAnl4RQqjELFf8XDTVqvj6Vrgfzv/XPtiWfHfh0tdG5xO9QKBgAxwrEKojC7Cg7DFz3eTplpuoGaIc0kUovdGGvmnvuaUDfZhXEfrh9klKRW2gKb0Iay/3cS93ExF+Tmaq+e6FY6jL8+jQvnq4RspHke57P3y6tiAQwdqrlzDDytN/mpTyvEZSRSVbq7wtcP4//DdtVC3dqnaQDwGQmVXA2zomizvAoGBANno4dQzwaS6Jh4PcoVMEMWSLxoFqBor+y0vFHL3+IDgiCU/KVYzRmRVEjG7WIOpn/EHYGEB8rCrMNiaphlfiikmLUH/JOkjj0s0/zf9wl+vwL0iuyV/KfAyToU3iPqG9YJDQ3YCs/XIlnIyvUO9pudr/TJnwBFQ5cwOBVcJPmcm";
    	 String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApLgoguTeFMH/KhjuW435dq07eC3i6wV9fWEHb0xaFvsdlqTSgVRtd5hdI0yPoRFMOtPxRc3T5Qf+6KEstcBNISNFVMrLKHxWenNgiP/ug7p8WBiUlvkr5OrCQmOc7bkmN40qiQp3L1FoT2DVyuINB7n4hxMVXpu6lrdeieS3lD5rLhKg84oUmzUXSSMCCzMk7YtZv9W91LkD3Xo7VTWioUT3jIIrMwdeuejGwCS6JAD/KVhmbpBb1wyb2YPgcyeOXsgEmDiwu8VASP7Kf/8ZfavJt37uN/V268+leVB0+ay8ie/yLbA634cJMv85HsMmeUbuLpIzEu047/X0m9gp7wIDAQAB";
    	 String aesPrivateKey="816d1ff3373315075960e227db180b24";
    	String salt = SaltUtil.generateSalt32_36();
        String token = encrypt_v2("XZpp1234!", salt, 1L, aesPrivateKey, privateKey);
        log.info("token={},{}", token.length(),token);
        String newT = decrypt_v2(token,aesPrivateKey, publicKey);
        log.info("user={}", newT);
    }*/

}
