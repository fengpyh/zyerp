package com.cryptomind.trading.api_response;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 *
 * @author zhousl
 * @version 2019/12/24
 */
@Data
public class EntrustQueryResponse<T> implements Serializable {
    private static final long serialVersionUID = -1003369387489106147L;
    protected static final Integer SUCCESS_CODE = 0;
    protected static final String SUCCESS_MSG = "success";

    protected Integer code;
    protected String msg;
    protected T data;

    public static EntrustQueryResponse buildSuccess(String data) {
        EntrustQueryResponse bean = new EntrustQueryResponse();
        bean.data = data;
        bean.code = SUCCESS_CODE;
        bean.msg = SUCCESS_MSG;
        return bean;
    }

    public static EntrustQueryResponse buildSuccess(List<?> list) {
        EntrustQueryResponse bean = new EntrustQueryResponse();
        bean.data = list;
        bean.code = SUCCESS_CODE;
        bean.msg = SUCCESS_MSG;
        return bean;
    }


    public static EntrustQueryResponse buildSuccess(OrderDetailResult entrustVO) {
        EntrustQueryResponse bean = new EntrustQueryResponse();
        bean.data = entrustVO;
        bean.code = SUCCESS_CODE;
        bean.msg = SUCCESS_MSG;
        return bean;
    }
    public static EntrustQueryResponse buildSuccess(EntrustVO entrustVO) {
        EntrustQueryResponse bean = new EntrustQueryResponse();
        bean.data = entrustVO;
        bean.code = SUCCESS_CODE;
        bean.msg = SUCCESS_MSG;
        return bean;
    }

    public static EntrustQueryResponse buildError(HttpStatus httpStatus) {
        EntrustQueryResponse bean = new EntrustQueryResponse();
        bean.code = httpStatus.value();
        bean.msg = httpStatus.getReasonPhrase();
        return bean;
    }

    public static EntrustQueryResponse buildError(HttpStatus httpStatus, String msg) {
        EntrustQueryResponse bean = new EntrustQueryResponse();
        bean.code = httpStatus.value();
        bean.msg = msg;
        return bean;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.getCode());
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

