package org.commerce.controller;



import org.commerce.entity.User;
import org.commerce.exception.GeneralException;
import org.commerce.model.user.ReqRes;
import org.commerce.model.user.UserLoginDTO;
import org.commerce.model.user.UserRegisterDTO;
import org.commerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDTO> registerUser(@RequestBody UserRegisterDTO user) throws GeneralException {
        var user1 = authService.createUser(user);
        log.info("User created:  " + user1);
        return new ResponseEntity(user1, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginDTO data) throws GeneralException {
        Object users = authService.login(data);
        log.info("User logged in:  " + users);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Object> refreshToken(@RequestBody UserLoginDTO data) throws GeneralException {
        Object users = authService.refreshToken(data);
        log.info("User refreshed:  " + users);
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
