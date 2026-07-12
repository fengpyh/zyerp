package com.cryptomind.trading.api_response;

import com.alibaba.fastjson.JSONObject;
import com.cryptomind.trading.utils.OperResult;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p></p>
 *
 * @author ryu
 * @version 2018/1/15
 */
public class NewEntrustResponse implements Serializable {
    private static final long serialVersionUID = -1003369387489106147L;

    private int code;
    private String msg;
    private String subMsg;
    private long elapseMills;
    private Object data;


    /**
     * 返回信息
     */
    public static NewEntrustResponse createByJsonObject(OperResult<FentrustVO> result) {
        List<FentrustVO> returnObject = result.getReturnObject();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fentrustVOS",returnObject);
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = map;
        bean.code = "0000".equals(result.getErrorCode())?0:Integer.parseInt(result.getErrorCode());
        bean.msg = result.getErrorMessage();
        bean.subMsg = "";
        return bean;
    }
    public static NewEntrustResponse buildSlaveSuccess(String postResult) {

        JSONObject jsonObject = JSONObject.parseObject(postResult);
        Integer code = jsonObject.getInteger("code");
        String msg = jsonObject.getString("msg");
        Object data = jsonObject.get("data");
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = data;
        bean.code = code;
        bean.msg = msg;
        bean.subMsg = "";
        return bean;
    }
    public static NewEntrustResponse buildError(HttpStatus httpStatus) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fentrustVOS",new ArrayList<>());
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = map;
        bean.code = httpStatus.value();
        bean.msg = httpStatus.getReasonPhrase();
        bean.subMsg = "";
        return bean;
    }
    public static NewEntrustResponse buildSuccessNoData() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fentrustVOS",new ArrayList<>());
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = map;
        bean.code =0;
        bean.msg = "success";
        bean.subMsg = "";
        return bean;
    }

    public static NewEntrustResponse buildEntrustLog(OperResult<EntrustLogRes> newEntrustListImpl, JSONObject jsonObject) {
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = jsonObject;
        bean.code = ("0000".equals(newEntrustListImpl.getErrorCode())||"".equals(newEntrustListImpl.getErrorCode()))?0:Integer.parseInt(newEntrustListImpl.getErrorCode());
        bean.msg = newEntrustListImpl.getErrorMessage();
        return bean;
    }
    public static NewEntrustResponse buildEntrustLogError(HttpStatus httpStatus) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fentrustlogs",new ArrayList<>());
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = map;
        bean.code = httpStatus.value();
        bean.msg = httpStatus.getReasonPhrase();
        bean.subMsg = "";
        return bean;
    }
    public static NewEntrustResponse buildEntrustLogSuccessNoData() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fentrustlogs",new ArrayList<>());
        NewEntrustResponse bean = new NewEntrustResponse();
        bean.data = map;
        bean.code =0;
        bean.msg = "success";
        bean.subMsg = "";
        return bean;
    }


    public boolean isSuccess(){
        return this.getCode() == 0;
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

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getElapseMills() {
        return elapseMills;
    }

    public void setElapseMills(long elapseMills) {
        this.elapseMills = elapseMills;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", elapseMills='" + elapseMills + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", data=" + data +
                '}';
    }
}

