package com.weighbridge.services;

import com.weighbridge.payloads.UpdateRequest;
import com.weighbridge.payloads.UserRequest;
import com.weighbridge.entities.UserMaster;
import com.weighbridge.payloads.UserResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserMasterService {

    String createUser(UserRequest userRequest, HttpSession session);

    Page<UserResponse> getAllUsers(Pageable pageable);
    UserResponse getSingleUser(String userId);

    String deleteUserById(String userId);

    UserResponse updateUserById(UpdateRequest updateRequest, String userId,HttpSession session);



}