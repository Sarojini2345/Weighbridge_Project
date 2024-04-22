package com.weighbridge.services.impls;

import com.weighbridge.dtos.LoginDto;
import com.weighbridge.dtos.ResetPasswordDto;
import com.weighbridge.entities.*;
import com.weighbridge.exceptions.ResourceNotFoundException;
import com.weighbridge.payloads.LoginResponse;
import com.weighbridge.repsitories.SiteMasterRepository;
import com.weighbridge.repsitories.UserAuthenticationRepository;
import com.weighbridge.repsitories.UserMasterRepository;
import com.weighbridge.services.UserAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;
    @Autowired
    private UserMasterRepository userMasterRepository;

    @Value("${app.default-password}")
    private String defaultPassword;

    @Autowired
    private SiteMasterRepository siteMasterRepository;
    @Autowired
    HttpServletRequest request;

    @Override
    public LoginResponse loginUser(LoginDto dto) {
        // Fetch user authentication details along with roles
        UserAuthentication userAuthentication = userAuthenticationRepository.findByUserIdWithRoles(dto.getUserId());
        if (userAuthentication == null) {
            throw new ResourceNotFoundException("User", "userId", dto.getUserId());
        }

        // Fetch user details along with company and site information
        UserMaster userMaster = userMasterRepository.findByUserIdWithCompanyAndSite(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", dto.getUserId()));

        if (userMaster.getUserStatus().equals("INACTIVE")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is inactive");
        }

        if (!userAuthentication.getUserPassword().equals(dto.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId or password");
        }

        if (dto.getUserPassword().equals(defaultPassword)) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage("Please reset your password.");
            loginResponse.setUserId(dto.getUserId());
            return loginResponse;
        }

//        String password = userAuthenticationRepository.findPasswordByUserId(dto.getUserId());
//        if (!password.equals(dto.getUserPassword())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId or password");
//        }

        // Set session attributes
        HttpSession session = request.getSession();
        session.setAttribute("userId", dto.getUserId());
        session.setAttribute("userSite", userMaster.getSite().getSiteId());
        session.setAttribute("userCompany", userMaster.getCompany().getCompanyId());

        // Prepare login response
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("User logged in successfully!");

        // Set roles in the response and session
        Set<String> roles = userAuthentication.getRoles().stream()
                .map(RoleMaster::getRoleName)
                .collect(Collectors.toSet());
        loginResponse.setRoles(roles);
        session.setAttribute("roles", roles);

        // Set user name in the response
        String userName = userMaster.getUserFirstName();
        if (userMaster.getUserMiddleName() != null) {
            userName += " " + userMaster.getUserMiddleName();
        }
        userName += " " + userMaster.getUserLastName();
        loginResponse.setUserName(userName);
        loginResponse.setUserId(userMaster.getUserId());

        return loginResponse;
    }


    @Override
    public UserAuthentication resetPassword(String userId, ResetPasswordDto resetPasswordDto) {
        UserAuthentication userAuthentication = userAuthenticationRepository.findByUserId(userId);
        System.out.println("password: "+resetPasswordDto.getPassword());
        userAuthentication.setUserPassword(resetPasswordDto.getPassword());
        UserAuthentication saveUser = userAuthenticationRepository.save(userAuthentication);
        return saveUser;
    }
}