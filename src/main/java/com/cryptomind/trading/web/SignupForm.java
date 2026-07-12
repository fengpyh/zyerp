package com.cryptomind.trading.web;

import lombok.Data;

@Data
public class SignupForm {
    private String fullname;
    private String email;
    private String password;
    private String passwordConfirm;
}
