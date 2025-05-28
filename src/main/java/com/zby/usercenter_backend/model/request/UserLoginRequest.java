package com.zby.usercenter_backend.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -6140165619840834238L;
    private String userAccount;
    private String userPassword;
}
