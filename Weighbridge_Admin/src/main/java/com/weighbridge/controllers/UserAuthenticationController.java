package com.weighbridge.controllers;

import com.weighbridge.dtos.LoginDto;
import com.weighbridge.dtos.ResetPasswordDto;
import com.weighbridge.entities.UserAuthentication;
import com.weighbridge.payloads.LoginResponse;
import com.weighbridge.services.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1/auths")
public class UserAuthenticationController {
    @Autowired
    private UserAuthenticationService userAuthenticationService;


    @PostMapping("/logIn")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto){
        LoginResponse response = userAuthenticationService.loginUser(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset/{userId}")
    public ResponseEntity<String> resetPassword(@PathVariable String userId,@RequestBody ResetPasswordDto resetPasswordDto){
        UserAuthentication userAuthentication=userAuthenticationService.resetPassword(userId,resetPasswordDto);
        return new ResponseEntity<>("Password Reset Succesful!",HttpStatus.OK);
    }


}
