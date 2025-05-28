package com.zby.usercenter_backend.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 9093783127350856166L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
