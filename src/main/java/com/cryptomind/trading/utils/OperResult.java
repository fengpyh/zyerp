package com.cryptomind.trading.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class OperResult<T> implements Serializable {
    private String errorCode = "0000";
    private String errorMessage;
    private List<T> returnObject;
    private T obj;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Long totalPages;

    public OperResult(Integer pageNumber, Integer pageSize, Long totalElements
            , Long totalPages, String errorCode,
                      String errorMessage, List<T> returnObject) {
        this.pageNumber = pageNumber;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.returnObject = returnObject;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public OperResult(T obj,String errorCode,String errorMessage) {
        this.obj = obj;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public OperResult() {
        this(0, 0, 0L, 0L, "", "", null);
    }
    

    public OperResult(String errorCode, String errorMessage) {
        this(0, 0, 0L, 0L, errorCode, errorMessage, null);
    }

    public static OperResult manual(Integer pageNumber, Integer pageSize, Long countNum, List<?> returnObject) {
        return new OperResult(pageNumber, pageSize, countNum, getTotalPages(countNum, pageSize), "0000", "success", returnObject);
    }

    public static OperResult error(String errorMessage) {
        return new OperResult("1111", errorMessage);
    }

    public static Long getTotalPages(Long countNum, Integer pageSize) {
        if ((countNum % pageSize) == 0) {
            return ((countNum / pageSize));
        } else {
            return ((countNum / pageSize) + 1);
        }
    }

}
