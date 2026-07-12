package com.cryptomind.trading.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RatingDTO implements Serializable {

    private String teamScore;

    private String ecologyScore;

    private String abbreviate;

    private String pdfUrl;

    private String anticipation;

    private String name;

    private String description;

    private String warning;

    private String levelResult;

    private String mobilePdfUrl;

    private String subjectScore;

}