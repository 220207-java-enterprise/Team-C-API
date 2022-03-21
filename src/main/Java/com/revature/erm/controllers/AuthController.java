package com.revature.erm.controllers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.LoginRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.models.User;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import com.revature.erm.util.exceptions.AuthenticationException;
import com.revature.erm.util.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

//TO handle talking to VScode
@CrossOrigin(exposedHeaders = "authorization")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Principal authenticate(@RequestBody LoginRequest loginRequest, HttpServletResponse resp) {

        //Comment out if needed
        Principal subject = new Principal(userService.login(loginRequest));

        //Generates a new token
        String token = tokenService.generateToken(new Principal(userService.login(loginRequest)));
        //Sets the token
        resp.setHeader("Authorization", token);

        //Comment out if needed
        resp.setHeader("Authorization", tokenService.generateToken(subject));

        //return new Principal(userService.login(loginRequest));

        //Comment out if needed
        return(subject);
    }

    // TODO centralize exception handlers using an aspect

    @ExceptionHandler(value = {
            InvalidRequestException.class,
            JacksonException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequests(Exception e) {

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleFailedAuthentication(AuthenticationException e) {

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleServerError(Throwable t) {
        t.printStackTrace();
    }

}
