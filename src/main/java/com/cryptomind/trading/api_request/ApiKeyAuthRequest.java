package com.cryptomind.trading.api_request;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@ToString
public class ApiKeyAuthRequest implements Serializable {

    private String apiKey;

    private String content;

    private String clientSignedData;


    private Integer signCheckResult;
}
