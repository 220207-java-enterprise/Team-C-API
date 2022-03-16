package com.revature.erm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.LoginRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.models.User;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/Auth")
public class AuthController {

//    private TokenService tokenService;
//    private UserService userService;
//
//    @Autowired
//    public AuthController(TokenService tokenService, UserService userService) {
//        this.tokenService = tokenService;
//        this.userService = userService;
//    }
//
//    @PostMapping(value = "login", produces = "application/json", consumes = "application/json")
//    public void login(@RequestBody LoginRequest credentials, HttpServletResponse resp) {
//
//        Principal principal = new Principal(userService.login(credentials));
//
//        String token = tokenService.generateToken(principal);
//
//        if (credentials.getUsername().equals("test") && credentials.getPassword().equals("p4$$W0RD")) {
//            resp.setHeader("Authorization", token);
//        } else {
//            resp.setStatus(401);
//        }
//    }

}
