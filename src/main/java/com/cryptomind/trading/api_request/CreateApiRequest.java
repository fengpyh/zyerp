package com.cryptomind.trading.api_request;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@ToString
public class CreateApiRequest implements Serializable {
    @NotBlank(message = "memo is not blank")
    private String memo;


    private ArrayList<String> permissionIdList;

    private ArrayList<String> trustIpList;

    public void trim(){
        this.memo = StringUtils.trim(memo);
    }
}
