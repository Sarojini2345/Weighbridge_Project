package com.weighbridge.services;

import com.weighbridge.dtos.LoginDto;
import com.weighbridge.dtos.ResetPasswordDto;
import com.weighbridge.entities.UserAuthentication;
import com.weighbridge.payloads.LoginResponse;

public interface UserAuthenticationService {

    LoginResponse loginUser(LoginDto dto);

    UserAuthentication resetPassword(String userId, ResetPasswordDto resetPasswordDto);
}
