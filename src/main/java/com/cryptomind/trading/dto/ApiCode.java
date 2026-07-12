package com.cryptomind.trading.dto;


public abstract class ApiCode {


    // 公共
    public static class Common {
        // NOTE: 成功
        public static CodeMessage SYSTEM_SUCCESS = new CodeMessage(0,"Success");
        // NOTE: 失败
        public static CodeMessage SYSTEM_FAILED = new CodeMessage(-1,"Failure");

        // NOTE: IP 限制操作
        public static CodeMessage IP_FREQUENTLY_LIMIT = new CodeMessage(-2,"Login too often, please try again after 2 hours");

        // NOTE: 非法提交
        public static CodeMessage PARAM_ERROR = new CodeMessage(-3,"Invalid request");

        // NOTE: 网络错误
        public static CodeMessage NETWORK_ERROR  = new CodeMessage(-4,"Network error");

        // NOTE: 重复的请求
        public static CodeMessage DUPLICATE_REQUEST_NO = new CodeMessage(-5,"Duplicate request");

        // NOTE: 用户未登录
        public static CodeMessage USER_NOT_LOGIN = new CodeMessage(-6,"User not login");

        // NOTE: 验证码有误
        public static CodeMessage IMG_VERIFICATION_CODE_ERROR   = new CodeMessage(-7,"Wrong captcha");
        public static CodeMessage SMS_VERIFICATION_CODE_ERROR   = new CodeMessage(-8,"Wrong SMS verification code");
        public static CodeMessage EMAIL_VERIFICATION_CODE_ERROR   = new CodeMessage(-9,"Wrong email verification code");

        // NOTE: GOOGLE验证码有误，您还有%d次机会
        public static CodeMessage GOOGLE_CODE_ERROR   = new CodeMessage(-12,"Invalid Google verification code，you have %d chances to retry");

        // NOTE: 手机验证码有误，您还有%d次机会
        public static CodeMessage PHONE_CODE_ERROR   = new CodeMessage(-13,"Invalid SMS verification code, you have %d chances to retry");

        // NOTE: 邮箱验证码有误，您还有%d次机会
        public static CodeMessage EMAIL_CODE_ERROR   = new CodeMessage(-14,"Invalid email verification code，you have %d chances to retry");

        // NOTE: 登录密码错误
        public static CodeMessage LOGIN_PWD_ERROR_   = new CodeMessage(-18,"Wrong login password，you have %d chances to retry");
        public static CodeMessage LOGIN_PWD_ERROR   = new CodeMessage(-19,"Wrong login password");

        // NOTE: 提现密码错误
        public static CodeMessage WITHDRAWAL_PWD_ERROR   = new CodeMessage(-20,"Wrong withdrawal password");

        // NOTE: 您还没有设置提现密码
        public static CodeMessage WITHDRAWAL_PWD_NOT_SET   = new CodeMessage(-21,"Withdrawal password has not been set");

        // NOTE: 提现密码有误，您还有%d次机会
        public static CodeMessage WITHDRAWAL_PWD_ERROR_LIMIT   = new CodeMessage(-22,"Invalid withdrawal password, you have %d chances to retry");

        // NOTE: 限次
        public static CodeMessage VISIT_TIME_LIMIT   = new CodeMessage(-30,"Visit too often, please try again later");

        // NOTE: 网络繁忙, 请重新刷新页面
        public static CodeMessage VISIT_AGAIN   = new CodeMessage(-31,"Network busy, please refresh the page");

        // NOTE: token 不存在
        public static CodeMessage TOKEN_ACCESS_NOT_FOUND   = new CodeMessage(-6,"Token not found");
        public static CodeMessage SIGN_ERROR   = new CodeMessage(-33,"Invalid signature");

        public static CodeMessage NEED_CODE_ERROR   = new CodeMessage(-34,"Missing verification code");

        public static CodeMessage SERVICE_NOT_OPEN   = new CodeMessage(-35,"Service unavailable");

        public static CodeMessage SERVICE_FORBIDDEN   = new CodeMessage(-36,"Forbidden");

        public static CodeMessage FORCE_UPDATE   = new CodeMessage(-37,"Force update");

        public static CodeMessage REQUEST_TIME_EXPIRED   = new CodeMessage(-40,"Request expired");
        public static CodeMessage ERROR_BUSINESS_APPID   = new CodeMessage(-41,"Wrong appId");
        public static CodeMessage ERROR_BUSINESS_SECRET   = new CodeMessage(-42,"Wrong appSecret");
        public static CodeMessage ERROR_BUSINESS_SERVICE   = new CodeMessage(-43,"Forbidden");
        public static CodeMessage ERROR_BUSINESS_SIGN   = new CodeMessage(-44,"Wrong signature");
        public static CodeMessage ERROR_BUSINESS_PARAM   = new CodeMessage(-45,"Invalid content");

    }



    // NOTE: 内部联调展示错误
    public static class SystemError {
        public static CodeMessage APP_ACCESS_TOKEN_EMPTY   = new CodeMessage(-6,"Missing accessToken");
        public static CodeMessage APP_SIGN_EMPTY   = new CodeMessage(-1,"Missing sign");
        public static CodeMessage APP_TIMESTAMP_EMPTY   = new CodeMessage(-1,"Missing timestamp");
    }

    // 用户登录
    public static class UserLogin {
        public static CodeMessage LOGINIP_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage LOGINNAME_ERROR = new CodeMessage(-1000,"Invalid username");
        public static CodeMessage LOGINNAME_OR_LOGINPWD_ERROR = new CodeMessage(-1001,"Invalid username or password");
        public static CodeMessage LOGINNAME_OR_LOGINPWD_ERROR_ = new CodeMessage(-1002,"Invalid username or password, you have %d chances to retry");
        public static CodeMessage LOGINNAME_NOT_EXIST = new CodeMessage(-1003,"Username not found");
        public static CodeMessage SITE_NOT_ALLOWED_LOGIN = new CodeMessage(-1004,"Service temporarily unavailable");
        public static CodeMessage LOGINACCOUNT_HAVE_SECURITY_RISKS  = new CodeMessage(-1005,"Account is frozen due to security policies. Please contact customer service.");
        public static CodeMessage S  = new CodeMessage(0,"Success");
    }

    //  用户注册
    public static class UserRegister {
        public static CodeMessage SUSPENSION_OF_REGISTRATION  = new CodeMessage(-1100,"Registration suspended");
        public static CodeMessage PWD_LENGTH_LIMIT  = new CodeMessage(-1101,"Password must be at least 6 characters long");
        public static CodeMessage PHONE_IS_REGISTERED  = new CodeMessage(-1102,"Phone already registered");
        public static CodeMessage WRONG_PHONE_FORMAT  = new CodeMessage(-1103,"Invalid phone number");
        public static CodeMessage SMS_VERIFICATION_CODE_ERROR = Common.SMS_VERIFICATION_CODE_ERROR;
        public static CodeMessage EMAIL_IS_REGISTERED  = new CodeMessage(-1105,"Email already registered");
        public static CodeMessage WRONG_EMAIL_FORMAT  = new CodeMessage(-1106,"Invalid email address");
        public static CodeMessage EMAIL_VERIFICATION_CODE_ERROR = Common.EMAIL_VERIFICATION_CODE_ERROR;
        public static CodeMessage INVITATION_VERIFICATION_CODE_ERROR  = new CodeMessage(-1108,"Wrong invitation code");
        public static CodeMessage INVITATION_VERIFICATION_HAS  = new CodeMessage(-1109,"Invitation code already exists");
        public static CodeMessage PHONE_IS_BIND  = new CodeMessage(-1110,"Phone already bound");
        public static CodeMessage MAIL_IS_BIND  = new CodeMessage(-1111,"Email already bound");
        public static CodeMessage NETWORK_ERROR  = Common.NETWORK_ERROR;
        public static CodeMessage S  = new CodeMessage(0,"Success");
    }


    // 发送验证码-短信
    public static class UserSendMessageCode {
        public static CodeMessage PARAM_ERROR   = Common.PARAM_ERROR;
        public static CodeMessage IMG_VERIFICATION_CODE_ERROR = Common.IMG_VERIFICATION_CODE_ERROR;
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT   = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage PHONE_NOT_FOUND   = new CodeMessage(-1203,"Phone not found");
        public static CodeMessage PHONE_NOT_EMPTY   = new CodeMessage(-1204,"Invalid phone number");
        public static CodeMessage WRONG_PHONE_FORMAT   = new CodeMessage(-1205,"Invalid phone number");
        public static CodeMessage PHONE_IS_EXIST   = new CodeMessage(-1206,"Phone already bound");
        public static CodeMessage PHONE_NOT_BIND   = new CodeMessage(-1207,"Phone not bound");
        public static CodeMessage PHONE_IS_BIND = UserRegister.PHONE_IS_BIND;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 发送验证码-邮箱
    public static class UserSendEmailCode {
        public static CodeMessage PARAM_ERROR   = Common.PARAM_ERROR;
        public static CodeMessage IMG_VERIFICATION_CODE_ERROR = Common.IMG_VERIFICATION_CODE_ERROR;
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT   = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage EMAIL_NOT_EMPTY   = new CodeMessage(-1304,"Invalid email address");
        public static CodeMessage EMAIL_NOT_FOUND   = new CodeMessage(-1305,"Email not found");
        public static CodeMessage EMAIL_IS_EXIST   = new CodeMessage(-1306,"Email already bound");
        public static CodeMessage MAIL_IS_BIND = UserRegister.MAIL_IS_BIND;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 发送验证码-google
    public static class UserSendGoogleCode {
        public static CodeMessage GOOGLE_NOT_BIND   = new CodeMessage(-1401,"Google authenticator not bound");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 手机认证
    public static class UserValidatePhone {
        public static CodeMessage WRONG_PHONE_FORMAT   = new CodeMessage(-1500,"Invalid phone number");
        public static CodeMessage HAS_BIND   = new CodeMessage(-1501,"Phone already bound");
        public static CodeMessage NOT_BIND   = new CodeMessage(-1502,"Phone not bound");
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;

        public static CodeMessage GOOGLE_CODE_ERROR = Common.GOOGLE_CODE_ERROR;
        public static CodeMessage PHONE_CODE_ERROR = Common.PHONE_CODE_ERROR;

        public static CodeMessage OLD_PHONE_CODE_ERROR   = new CodeMessage(-1505,"Wrong SMS verification code, you have %d chances to retry");

        public static CodeMessage PHONE_IS_EXIST   = new CodeMessage(-1507,"Phone already bound");
        public static CodeMessage IMG_VERIFICATION_CODE_ERROR = Common.IMG_VERIFICATION_CODE_ERROR;
        public static CodeMessage NETWORK_ERROR = Common.NETWORK_ERROR;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 邮箱认证
    public static class UserValidateEmail {
        public static CodeMessage WRONG_PHONE_FORMAT   = new CodeMessage(-1550,"Invalid mail");
        public static CodeMessage HAS_BIND   = new CodeMessage(-1551,"Mail already bound");
        public static CodeMessage NOT_BIND   = new CodeMessage(-1552,"Mail not bound");
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;

        public static CodeMessage GOOGLE_CODE_ERROR = Common.GOOGLE_CODE_ERROR;
        public static CodeMessage EMAIL_CODE_ERROR = Common.EMAIL_CODE_ERROR;

        public static CodeMessage OLD_EMAIL_CODE_ERROR   = new CodeMessage(-1555,"Wrong Mail verification code, you have %d chances to retry");

        public static CodeMessage MAIL_IS_EXIST   = new CodeMessage(-1557,"Mail already bound");
        public static CodeMessage IMG_VERIFICATION_CODE_ERROR = Common.IMG_VERIFICATION_CODE_ERROR;
        public static CodeMessage NETWORK_ERROR = Common.NETWORK_ERROR;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 身份证认证
    public static class UserValidateIdentity {
        public static CodeMessage WRONG_IDENTITY_FORMAT   = new CodeMessage(-1600,"Invalid ID number (should be 15 or 18 digits)");
        public static CodeMessage WRONG_IDENTITY_NUMBER   = new CodeMessage(-1601,"Wrong ID number");
        public static CodeMessage WRONG_IDENTITY_NUMBER_LENGTH   = new CodeMessage(-1602,"Invalid ID number");
        public static CodeMessage WRONG_REALNAME_FORMAT   = new CodeMessage(-1603,"Invalid name");
        public static CodeMessage IDENTITY_IS_EXIST  = new CodeMessage(-1604,"ID number already exists");
        public static CodeMessage FREQUENTLY_LIMIT  = Common.DUPLICATE_REQUEST_NO;
        public static CodeMessage WRONG_IDENTITY_REALNAME_NOT_CONSISTENCY  = new CodeMessage(-1606,"Name and ID number are not matching");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 更改|设置-登录|交易密码
    public static class UserModifyPwd {
        public static CodeMessage WRONG_RENEWPWD_NEWPWD_NOT_CONSISTENCY   = new CodeMessage(-1700,"Password mismatch");
        public static CodeMessage NEED_BIND_VALIDATE_CODE   = new CodeMessage(-1701,"User needs to bind Google authenticator or phone to change password.");
        public static CodeMessage OLD_LOGIN_PWD_ERROR   = new CodeMessage(-1702,"Wrong original login password");
        public static CodeMessage OLD_TRADE_PWD_ERROR   = new CodeMessage(-1703,"Wrong original trade password");
        public static CodeMessage OLD_LOGINPWD_NEWPWD_IS_CONSISTENCY   = new CodeMessage(-1704,"Login passwords are the same");
        public static CodeMessage OLD_TRADEPWD_NEWPWD_IS_CONSISTENCY   = new CodeMessage(-1705,"Trade passwords are the same");
        public static CodeMessage LOGIN_NAME_NOT_EXITS   = new CodeMessage(-1706,"Invalid username");
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage PHONE_CODE_ERROR = Common.PHONE_CODE_ERROR;
        public static CodeMessage EMAIL_CODE_ERROR = Common.EMAIL_CODE_ERROR;
        public static CodeMessage NETWORK_ERROR = Common.NETWORK_ERROR;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 更改安全设置
    public static class UserModifySecurity {
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage EMAIL_VERIFICATION_CODE_ERROR = Common.EMAIL_VERIFICATION_CODE_ERROR;
        public static CodeMessage GOOGLE_CODE_ERROR = Common.GOOGLE_CODE_ERROR;
        public static CodeMessage PHONE_CODE_ERROR = Common.PHONE_CODE_ERROR;
        public static CodeMessage EMAIL_CODE_ERROR = Common.EMAIL_CODE_ERROR;

    }


    // 交易委托
    public static class TradeEntrust {
        public static CodeMessage COIN_NOT_TRADE   = new CodeMessage(-1800,"Service not available");
        public static CodeMessage NETWORK_ERROR = Common.NETWORK_ERROR;
        public static CodeMessage TRADE_MIN_COUNT_LIMIT   = new CodeMessage(-1802,"Minimum count %s");
        public static CodeMessage TRADE_MAX_COUNT_LIMIT   = new CodeMessage(-1803,"Maximum count %s");
        public static CodeMessage TRADE_MIN_PRICE_LIMIT   = new CodeMessage(-1804,"Minimum price %s");
        public static CodeMessage TRADE_MIN_AMOUNT_LIMIT   = new CodeMessage(-1805,"Minimum amount %s");
        public static CodeMessage TRADE_PRICE_H_LIMIT   = new CodeMessage(-1806,"Trade price higher than limit %s");
        public static CodeMessage TRADE_PRICE_L_LIMIT   = new CodeMessage(-1807,"Trade price lower than limit %s");
        public static CodeMessage TRADE_MIN_TRADE_AMOUNT_LIMIT   = new CodeMessage(-1808,"Minimum amount limit %s");
        public static CodeMessage TRADE_MIN_TRADE_COUNT_LIMIT   = new CodeMessage(-1809,"Minimum count limit %s");
        public static CodeMessage TRADE_TIME_LIMIT   = new CodeMessage(-1810,"Trade time limit");
        public static CodeMessage BALANCE_NOT_ENOUGH   = new CodeMessage(-1811,"%s balance not enough");
        public static CodeMessage SELL_MAX   = new CodeMessage(-1812,"The maximum you can sell today is %s");
        public static CodeMessage TRADE_PWD_ERROR = Common.WITHDRAWAL_PWD_ERROR;
        public static CodeMessage TRADE_PWD_NOT_FUND = Common.WITHDRAWAL_PWD_NOT_SET;
        public static CodeMessage TRADE_MAX_ENTRUST_COUNT_LIMIT   = new CodeMessage(-1815,"Maximum entrust count within a day %d");
        public static CodeMessage COIN_NOT_LIMIT_TRADE   = new CodeMessage(-1816,"Invalid coin type");
        public static CodeMessage TRADE_LIMIT_BY_ACCOUNT   = new CodeMessage(-1817,"Service not available");
        public static CodeMessage TRADE_MAX_PRICE_LIMIT = new CodeMessage(-1818, "Maximum price %s");
        public static CodeMessage TRADE_AMOUNT_LIMIT   = new CodeMessage(-1819,"Exceed the maximum purchase amount");
        public static CodeMessage TRADE_CAN_NOT_SELL   = new CodeMessage(-1820,"Order prohibited");
        public static CodeMessage TRADE_UN_EXIT_ACTIVITY   = new CodeMessage(-1821,"Un exit activity");
        public static CodeMessage TRADE_QUOTA_AMOUNT_LIMIT   = new CodeMessage(-1822,"Can not quota amount");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 交易查询
    public static class TradeQuery {
        public static CodeMessage MAPPING_COIN_ERROR   = new CodeMessage(-1900,"Invalid request");
        public static CodeMessage LIMIT_CYN_TRADE   = new CodeMessage(-1901,"Operation not supported");
        public static CodeMessage NO_RULES_FOUND   = new CodeMessage(-1902,"No rules found");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 市场查询
    public static class MarketQuery {
        public static CodeMessage NOT_RECORDS   = new CodeMessage(-2000,"No record");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }



    // 市场查询
    public static class Account {
        public static CodeMessage PARAM_ERROR = Common.PARAM_ERROR;
        public static CodeMessage MUST_BIND_GOOGLE_OR_PHONE   = new CodeMessage(-2101,"Please bind Google authenticator or phone.");
        public static CodeMessage WITHDRAW_COUNT_LIMIT   = new CodeMessage(-2102,"Maximum withdraw within a day %d times");
        public static CodeMessage MIN_WITHDRAW_COUNT   = new CodeMessage(-2103,"Minimum withdraw count %s");
        public static CodeMessage MAX_WITHDRAW_COUNT   = new CodeMessage(-2104,"Maximum withdraw count %s");
        public static CodeMessage BALANCE_NOT_ENOUGH   = new CodeMessage(-2105,"Balance not enough");
        public static CodeMessage WITHDRAW_TO_SEFT_LIMIT   = new CodeMessage(-2106,"Unsupported operation");
        public static CodeMessage WITHDRAW_RISK_LIMIT   = new CodeMessage(-2107,"Exceed daily withdrawal quota, for your safety, please wait 24 hours and try again");
        public static CodeMessage WITHDRAW_NEED_KYC   = new CodeMessage(-2108,"withdraw before submit kyc");
        public static CodeMessage MUST_SET_WITHDRAWAL_PWD = Common.WITHDRAWAL_PWD_NOT_SET;
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage WITHDRAWAL_PWD_ERROR = Common.WITHDRAWAL_PWD_ERROR;
        public static CodeMessage GOOGLE_CODE_ERROR = Common.GOOGLE_CODE_ERROR;
        public static CodeMessage PHONE_CODE_ERROR = Common.PHONE_CODE_ERROR;
        public static CodeMessage NETWORK_ERROR = Common.NETWORK_ERROR;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 更新提现地址
    public static class AccountModifyWithdrawAddr {
        public static CodeMessage NEED_BIND_VALIDATE_CODE   = new CodeMessage(-2200,"User needs to bind Google authenticator or phone to change address.");
        public static CodeMessage WITHDRAWAL_PWD_ERROR = Common.WITHDRAWAL_PWD_ERROR;
        public static CodeMessage WITHDRAWAL_PWD_NOT_SET = Common.WITHDRAWAL_PWD_NOT_SET;
        public static CodeMessage COIN_NOT_FOUND   = new CodeMessage(-2203,"Coin does not exist");
        public static CodeMessage NOTE_LENGTH_LIMIT   = new CodeMessage(-2204,"Request too long");
        public static CodeMessage MEMO_NOT_EMPTY   = new CodeMessage(-2205,"Memo is empty");
        public static CodeMessage WRONG_ADDRESS_FORMAT   = new CodeMessage(-2206,"Invalid address");
        public static CodeMessage REPEAT_ADDRESS   = new CodeMessage(-2207,"Address already exists");
        public static CodeMessage NOT_ALLOW_RECHARGE_ADDRESS   = new CodeMessage(-2208,"Address can not be updated");
        public static CodeMessage ERROR_ADDRESS   = new CodeMessage(-2209,"Address does not exist");
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;
        public static CodeMessage PHONE_CODE_ERROR = Common.PHONE_CODE_ERROR;
        public static CodeMessage GOOGLE_CODE_ERROR = Common.GOOGLE_CODE_ERROR;
        public static CodeMessage NETWORK_ERROR = Common.NETWORK_ERROR;
        public static CodeMessage EMAIL_CODE_ERROR = Common.EMAIL_CODE_ERROR;
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 账户查询
    public static class AccountQuery {
        public static CodeMessage MAPPING_COIN_ERROR   = new CodeMessage(-2300,"No matching coin");
        public static CodeMessage WITHDRAW_FORBIDDEN = new CodeMessage(-2301, "Withdraw forbidden");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 账户查询
    public static class UserUpload {
        public static CodeMessage NOT_MATCH_EXT   = new CodeMessage(-2400,"Unsupported file extension");
        public static CodeMessage HAS_POST   = new CodeMessage(-2401,"Request already submitted");
        public static CodeMessage HAS_IMG   = new CodeMessage(-2402,"Already validated");
        public static CodeMessage LIMIT_SIZE   = new CodeMessage(-2403,"Request too long");
        public static CodeMessage NOT_DATA   = new CodeMessage(-2404,"No data submitted");
        public static CodeMessage ERROR_IDENTITYNO   = new CodeMessage(-2405,"Invalid identity information");
        public static CodeMessage IDENTITYNO_EXITS   = new CodeMessage(-2406,"ID number already exists");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }


    // 市场查询
    public static class AccountCertification {
        public static CodeMessage PARAM_ERROR = Common.PARAM_ERROR;
        public static CodeMessage ERROR_UUID  = new CodeMessage(-2501,"Invalid UID");
        public static CodeMessage EXPIRED_UUID  = new CodeMessage(-2502,"UID expired");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 谷歌认证
    public static class UserValidateGoogle {
        public static CodeMessage HAS_BIND   = new CodeMessage(-2690,"Google authenticator already bound");
        public static CodeMessage NOT_BIND   = new CodeMessage(-2691,"Google authenticator not bound");
        public static CodeMessage GOOGLE_CODE_ERROR = Common.GOOGLE_CODE_ERROR;
        public static CodeMessage IP_SEND_FREQUENTLY_LIMIT = Common.IP_FREQUENTLY_LIMIT;

        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 投票
    public static class UserVote {
        public static CodeMessage DUPLICATE_VOTE   = new CodeMessage(-2700,"You have already voted this coin today."); // 您今天已经给这个币投过票了
        public static CodeMessage VOTE_OVER_TIMES   = new CodeMessage(-2701,"You have already voted 11 times today.");// 您今天已经投过票了
        public static CodeMessage INVALID_PARAMETER = new CodeMessage(-2702, "Invalid parameter");
        public static CodeMessage NOT_ENOUGH_COIN = new CodeMessage(-2703, "You don't have enough coin to vote.");
        public static CodeMessage NOT_ENOUGH_TRIES = new CodeMessage(-2704,"You don't have enough times to vote.");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 抽奖
    public static class Lottery {
        public static CodeMessage DUPLICATE_SHARE   = new CodeMessage(-2705,"You have already shared today."); // 每天只能分享一次
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    public static class Innov {
        public static CodeMessage PROJECT_NOT_FOUND   = new CodeMessage(-2802,"This project not found"); // 每天只能分享一次
        public static CodeMessage PROJECT_NOT_ALLOW_INVEST   = new CodeMessage(-2803,"This project not allow invest"); // 不许投资
        public static CodeMessage PROJECT_INVEST_MAX_LIMIT   = new CodeMessage(-2804,"Invest limit"); // 不许投资
        public static CodeMessage BALANCE_NOT_ENOUGH   = new CodeMessage(-2805,"Balance not enough");
        public static CodeMessage LOCK_FLOW_NOT_FOUND   = new CodeMessage(-2806,"This lock seq not found"); // 每天只能分享一次
        public static CodeMessage LOCK_FLOW_NOT_ALLOW_RENEWAL   = new CodeMessage(-2807,"This lock not allow renewal"); // 每天只能分享一次
        public static CodeMessage LOCK_FLOW_AMOUNT_MULTIPLE   = new CodeMessage(-2808,"This lock amount is a multiple of 100"); // 锁仓金额是100的倍数
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    // 添加google设备
    public static class UserAddValidateGoogle {
        public static CodeMessage HAS_BIND   = new CodeMessage(-3000,"Google authenticator already bound");
        public static CodeMessage S   = new CodeMessage(0,"Success");
    }

    /**
     *   -3001 设备被冻结1小时
     *   -3002 设备被冻结注册1小时
     *   -3003 设备被冻结登录1小时
     *   -3004 设备被冻结修改密码1小时
     *   -3005 找回密码请通过人工客服
     *   -3006 设备被冻结找回密码1小时
     *   -3007 设备被冻提现24小时
     */
    public static class Frozen {

        // NOTE: 设备冻结注册1小时
        public static CodeMessage DEVICE_   = new CodeMessage(-3001,"Account is frozen due to security policies, please try again after %d hours");
        public static CodeMessage REGISTER   = new CodeMessage(-3002,"Account is frozen due to security policies, please try again after %d hours");
        public static CodeMessage LOGIN   = new CodeMessage(-3003,"Account is frozen due to security policies, please try again after %d hours");
        public static CodeMessage MODIFY_PWD   = new CodeMessage(-3004,"Account is frozen due to security policies, please try again after %d hours");
        public static CodeMessage FIND_PWD_BY_CUSTOMER   = new CodeMessage(-3005,"Account is frozen due to security policies, 通过客服找回");
        public static CodeMessage FIND_PWD   = new CodeMessage(-3006,"Account is frozen due to security policies, please try again after %d hours");
        public static CodeMessage WITHDRAW   = new CodeMessage(-3007,"Account is frozen due to security policies, please try again after 24 hours");

    }

    public static class OAuth {
        public static CodeMessage S = new CodeMessage(0, "Success");
        public static CodeMessage DUPLICATE_MEMO = new CodeMessage(-3101, "Duplicate memo");
        public static CodeMessage INVALID_REQUEST = new CodeMessage(-3102, "Fields are missing");
        public static CodeMessage SIZE_LIMIT = new CodeMessage(-3103, "Size of trustIpList should be less than or equal to 10");
        public static CodeMessage QUANTITY_LIMIT = new CodeMessage(-3104, "User could create at most 5 APIs");
        public static CodeMessage INVALID_PROJECT = new CodeMessage(-3105, "Invalid project name");
    }

    public static class CoinDetail {
        public static CodeMessage S = new CodeMessage(0, "Success");
        public static CodeMessage INVALID_COIN_TYPE = new CodeMessage(-3201, "Invalid coinType");
        public static CodeMessage INVALID_TYPE = new CodeMessage(-3202, "Invalid type");
    }

    public static class Galaxy {
        public static CodeMessage S = new CodeMessage(0, "Success");
        public static CodeMessage NOT_CONFIGURED = new CodeMessage(-3301, "Not configured");
        public static CodeMessage FORBIDDEN = new CodeMessage(-3202, "User doesn't have permission to view this page");
    }


    public static class CodeMessage{
        private int code;
        private String msg;

        public CodeMessage(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}