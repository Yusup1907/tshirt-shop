package org.commerce.service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.commerce.entity.User;
import org.commerce.exception.GeneralException;
import org.commerce.model.user.ReqRes;
import org.commerce.model.user.UserLoginDTO;
import org.commerce.model.user.UserRegisterDTO;
import org.commerce.model.user.UserRegisterRDTO;
import org.commerce.repository.UserRepository;
import org.commerce.util.JwtUtils;
import org.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<User> isEmailAlreadyExists(String email) {
        return userRepository.findByEmail(email);
    }

    private Optional<User> isUserNameAlreadyExists(String username) {
        return userRepository.findByUsername(username);
    }

    private String passwordEncrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean isValidCustomerData(User user) {
        boolean isValidUsername = user.getUsername() != null && user.getUsername().length() >= 3;
        boolean isValidFirstName = user.getFirstName() != null && user.getFirstName().length() >= 3;
        boolean isValidLastName = user.getLastName() != null && user.getLastName().length() >= 3;
        boolean isValidAddress = user.getAddress() != null && user.getAddress().length() >= 3;
        boolean isValidEmail = user.getEmail() != null && user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$");
        boolean isValidPassword = user.getPassword() != null && user.getPassword().length() >= 8;

        return isValidUsername && isValidFirstName && isValidLastName && isValidAddress && isValidEmail
                && isValidPassword;
    }



    @Transactional
    public UserRegisterRDTO createUser(UserRegisterDTO request) throws GeneralException {
        UserRegisterRDTO response = new UserRegisterRDTO();
        try {
            User user = new User();
            String idUser = "OID" + StringUtil.setUUID();
            user.setId(idUser);
            user.setUsername(request.getUsername());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setAddress(request.getAddress());
            user.setRole("USER");

            if (isUserNameAlreadyExists(request.getUsername()).isPresent()) {
                log.error("Username already exists");
                throw new GeneralException("400", null, "Username already exists");
            }

            if (request.getPhone() != null && request.getPhone().length() == 12) {
                user.setPhone(request.getPhone());
            } else {
                log.error("Invalid phone number");
                throw new GeneralException("Status Code : 400", null, "Phone number must be a single character");
            }

            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            if (!isValidCustomerData(user)) {
                log.error("Invalid customer data");
                throw new GeneralException("400", null, "Invalid customer data");
            }

            if (isEmailAlreadyExists(request.getEmail()).isPresent()) {
                log.error("Email already exists");
                throw new GeneralException("400", null, "Email already exists");
            }


             userRepository.save(user);

            response.setStatusCode(201);
            response.getMessage("Register successfully!");
            response.setUsername(user.getUsername());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setAddress(user.getAddress());
            response.setPhone(user.getPhone());
            return response;

        } catch (GeneralException e) {
            log.error("rc : {} message system : {}  rm : {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error creating user", e);
            throw new GeneralException("500", null, "Internal server error");
        }

    }

    public ReqRes login(UserLoginDTO requests) throws GeneralException {
        ReqRes response = new ReqRes();

        try {
            User user = userRepository.findByEmail(requests.getEmail())
                    .orElseThrow(() -> new GeneralException("401", null, "Invalid email or password"));
            log.info("User is" + user);

            var jwtToken = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            response.setStatusCode(200);
            response.setToken(jwtToken);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            return response;
        } catch (GeneralException e) {
            log.error("rc : {} message system : {}  rm : {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
        } catch (Exception e) {
            log.error("Error during login", e.getMessage());
            throw new GeneralException("500", null, "Internal server error");
        }

    }

    public ReqRes refreshToken(UserLoginDTO refreshTokenReq) throws GeneralException {
        ReqRes response = new ReqRes();

        try {
            String username = jwtUtils.extracUsername(refreshTokenReq.getToken());
            UserDetails user = userRepository.findByUsername(username)
                    .orElseThrow();

            if (jwtUtils.isTokenValid(refreshTokenReq.getToken(), user)) {
                String jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReq.getToken());
            } else {
                throw new GeneralException("401", null, "Invalid refresh token");
            }

            return response;

        } catch (GeneralException e) {
            log.error("rc : {} message system : {}  rm : {}", e.getStatusCode(), e.getMessage(), e.getGeneralMessage());
            throw new GeneralException(e.getStatusCode(), e.getMessage(), e.getGeneralMessage());

        } catch (Exception e) {
            log.error("Error during token refresh", e);
            throw new GeneralException("500", null, "Internal server error");
        }
    }


}
