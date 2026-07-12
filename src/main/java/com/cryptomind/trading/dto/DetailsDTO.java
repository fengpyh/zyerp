package com.cryptomind.trading.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DetailsDTO implements Serializable {

    private String coinAbbreviation;

    private String coinFullName;

    private String issueDate;

    private String issuePrice;

    private String circulating;

    private String totalSupply;

    private String websiteUrl;

    private String whitePaperUrl;

    private String projectDetailedIntroduction;

}
