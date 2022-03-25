package com.revature.erm.controllers;

import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateUserRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.dtos.responses.UserResponse;
import com.revature.erm.models.User;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin(exposedHeaders = "authorization")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping(produces = "application/json")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/availability")
    public void checkAvailability(@RequestParam Map<String, String> requestParams, HttpServletResponse resp) {
        if (requestParams.size() != 1) {
            throw new InvalidRequestException("This endpoint expects only a single request parameter to be provided; " +
                    "either: username or email.");
        }
        String username = requestParams.get("username");
        String email = requestParams.get("email");
        if (username != null) {
            if (userService.isUsernameAvailable(username)) {
                resp.setStatus(204);
            } else {
                resp.setStatus(409);
            }
        } else if (email != null) {
            if (userService.isEmailAvailable(email)) {
                resp.setStatus(204);
            } else {
                resp.setStatus(409);
            }
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse registerNewUser(@RequestBody NewUserRequest newUserRequest) {
        User newUser = userService.register(newUserRequest);
        return new ResourceCreationResponse(newUser.getId());
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Principal updateUser(@RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest req){
        Principal ifAdmin = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if (ifAdmin == null){
            throw new ResourceNotFoundException("Login token issue: wrong token, not provided, or may have expired.");
        }
        if(!(ifAdmin.getRole().equals("Admin"))) {
            throw new InvalidRequestException("Not an Admin!");
        }
        User updatedUser = userService.updateUser(updateUserRequest);
        return new Principal(updatedUser);
    }
}
