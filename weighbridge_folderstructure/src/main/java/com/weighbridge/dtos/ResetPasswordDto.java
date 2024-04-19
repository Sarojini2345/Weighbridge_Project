package com.weighbridge.dtos;


import lombok.Data;

@Data
public class ResetPasswordDto {


    private String password;
    private String rePassword;
}
