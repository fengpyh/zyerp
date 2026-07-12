package com.cryptomind.trading.dto;

import lombok.*;

import java.util.HashMap;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse_v1<T> {

    private Integer code;
    private String msg;
    private String subMsg;
    private Long elapseMills;
    private T data;

    private Boolean success;
    private String apicode;


    /**
     * 返回信息
     * @param codeMessage 自定义返回码
     * @return
     */
    public static ApiResponse_v1 createByJsonObject(ApiCode.CodeMessage codeMessage) {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = new HashMap<>();
        bean.msg = codeMessage.getMsg();
        bean.subMsg = "";
        bean.code = codeMessage.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = true;
        return bean;
    }


    /**
     * 返回成功
     * @param codeMessage 自定义返回码
     * @return
     */
    public static ApiResponse_v1 createSuccess(ApiCode.CodeMessage codeMessage) {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = new HashMap<>();
        bean.msg = codeMessage.getMsg();
        bean.subMsg = codeMessage.getMsg();
        bean.code = codeMessage.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = true;
        return bean;
    }

    public static ApiResponse_v1 ofSuccess(Object data) {
        return createSuccess(ApiCode.Common.SYSTEM_SUCCESS, data);
    }


    /**
     * 返回成功
     * @param codeMessage 自定义返回码
     * @param data 返回数据
     * @return 返回RestResponse
     */
    public static ApiResponse_v1 createSuccess(ApiCode.CodeMessage codeMessage, Object data) {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = data;
        bean.msg = codeMessage.getMsg();
        bean.subMsg = codeMessage.getMsg();
        bean.code = codeMessage.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = true;
        return bean;
    }

    /**
     * 返回失败
     * @param codeMessage 自定义失败信息
     * @return
     */
    public static ApiResponse_v1 createFailure(ApiCode.CodeMessage codeMessage) {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = new HashMap<>();
        bean.msg = codeMessage.getMsg();
        bean.subMsg = "";
        bean.code = codeMessage.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = false;
        return bean;
    }

    /**
     * 返回失败
     * @param codeMessage 自定义内容
     * @return
     */
    public static ApiResponse_v1 createFailure(ApiCode.CodeMessage codeMessage, Object data) {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = data;
        bean.msg = codeMessage.getMsg();
        bean.subMsg = "";
        bean.code = codeMessage.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = false;
        return bean;
    }

    /**
     * 返回失败
     * @param codeMessage 自定义内容
     * @return
     */
    public static ApiResponse_v1 createFailure(ApiCode.CodeMessage codeMessage, String subMessage) {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = new HashMap<>();
        bean.msg = codeMessage.getMsg();
        bean.subMsg = subMessage;
        bean.code = codeMessage.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = false;
        return bean;
    }



    /**
     * 返回失败
     * @return
     */
    public static ApiResponse_v1 createFailure() {
        ApiResponse_v1 bean = new ApiResponse_v1();
        bean.data = new HashMap<>();
        bean.msg = ApiCode.Common.SYSTEM_FAILED.getMsg();
        bean.subMsg = "";
        bean.code = ApiCode.Common.SYSTEM_FAILED.getCode();
        bean.apicode="A00001";
        bean.elapseMills = 0L;
        bean.success = false;
        return bean;
    }
}
