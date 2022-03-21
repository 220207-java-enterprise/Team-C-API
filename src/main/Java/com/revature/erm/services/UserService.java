package com.revature.erm.services;

import com.revature.erm.dtos.requests.LoginRequest;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateUserRequest;
import com.revature.erm.dtos.responses.UserResponse;
import com.revature.erm.models.User;
import com.revature.erm.models.UserRole;
import com.revature.erm.repos.UserRepos;
import com.revature.erm.util.exceptions.AuthenticationException;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepos userRepo;

    @Autowired
    public UserService(UserRepos userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserResponse> getAllUsers() {
//        List<User> users = userRepo.findAll();
//        List<UserResponse> userResponses = new ArrayList<>();
//        for (User user : users) {
//            userResponses.add(new UserResponse((user)));
//
//        return userResponses;
        return userRepo.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }


    public User register(NewUserRequest newUserRequest) {

        User newUser = newUserRequest.extractUser();
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Bad registration details given.");
        }

        boolean usernameAvailable = isUsernameAvailable(newUser.getUsername());
        boolean emailAvailable = isEmailAvailable(newUser.getEmail());

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new ResourceConflictException(msg);
        }

        // TODO encrypt provided password before storing in the database

        newUser.setId(UUID.randomUUID().toString());
        newUser.setRoleId(new UserRole("2", "Employee")); // All newly registered users start as Employee
        newUser.setActive(false);//All users are inactive at first an admin must activate and change roles
        userRepo.save(newUser);

        return newUser;
    }

    public User login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (!isUsernameValid(username) || !isPasswordValid(password)) {
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        // TODO encrypt provided password (assumes password encryption is in place) to see if it matches what is in the DB

        User authUser = userRepo.findUserByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return authUser;

    }

    public User updateUser(UpdateUserRequest updateRequest) {
        User updatedUser = updateRequest.extractUser();
        userRepo.save(updatedUser);
        return updatedUser;
    }

    public boolean isUserValid(User user) {

        // First name and last name are not just empty strings or filled with whitespace
        if (user.getFirstName().trim().equals("") || user.getLastName().trim().equals("")) {
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (!isUsernameValid(user.getUsername())) {
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (!isPasswordValid(user.getPassword())) {
            return false;
        }

        // Basic email validation
        return isEmailValid(user.getEmail());

    }

    public boolean isEmailValid(String email) {
        if (email == null) return false;
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }

    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    public boolean isUsernameAvailable(String username) {
        if (username == null || !isUsernameValid(username)) return false;
        return userRepo.findUserByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        if (email == null || !isEmailValid(email)) return false;
        return userRepo.findUserByEmail(email) == null;
    }
}
