package com.revature.erm.services;

import com.revature.erm.daos.UserDAO;
import com.revature.erm.dtos.requests.LoginRequest;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateUserRequest;
import com.revature.erm.dtos.responses.UserResponse;
import com.revature.erm.models.User;
import com.revature.erm.repos.UserRepos;
import com.revature.erm.util.exceptions.AuthenticationException;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    /*
     field injection: really readable, but requires Spring to use Reflection
     in order to set the private field
     */
    // @Autowired // field injection
    private UserRepos userRepos; // a dependency of UserService

    /* Constructor injection: a little less readable, can't change the
     * dependency later, but it makes the most sense logically with
     * what a dependency is supposed to be (a requirement to create the object)
     *
     * if you only have one constructor, you can leave out the Autowired annotation
     */
    //@Autowired
    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    /* setter injection: less readable, but allows you to change
     * the value of the dependency later in the code if needed
     */
//    @Autowired
//    public void setUserDao(UserDAO userDao) {
//    	this.userDAO = userDao;
//    }

    public List<UserResponse> getAllUsers() {

        // Pre-Java 8 mapping logic (without Streams)
//        List<AppUser> users = userDAO.getAll();
//        List<AppUserResponse> userResponses = new ArrayList<>();
//        for (AppUser user : users) {
//            userResponses.add(new AppUserResponse(user));
//        }
//        return userResponses;

        // Java 8+ mapping logic (with Streams)
        //TODO needs new logic
        return userRepos.findAll()
                .stream()
                .map(UserResponse::new) // intermediate operation
                .collect(Collectors.toList()); // terminal operation
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
        newUser.setIsActive(false);
        userRepos.save(newUser);

        return newUser;
    }
    public User updatedUser(UpdateUserRequest updateRequest) {
        User updatedUser = updateRequest.extractUser();

        //TODO needs update to userRepos
        userRepos.update(updatedUser);
        return updatedUser;
    }
    public User login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (!isUsernameValid(username) || !isPasswordValid(password)) {
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        // TODO encrypt provided password (assumes password encryption is in place) to see if it matches what is in the DB

        User authUser = userRepos.findUserByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return authUser;

    }

    public boolean isUserValid(User appUser) {

        // First name and last name are not just empty strings or filled with whitespace
        if (appUser.getFirstName().trim().equals("") || appUser.getLastName().trim().equals("")) {
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (!isUsernameValid(appUser.getUsername())) {
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (!isPasswordValid(appUser.getPassword())) {
            return false;
        }

        // Basic email validation
        return isEmailValid(appUser.getEmail());

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
        //TODO make findUserByUsername
        return userRepos.findUserByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        if (email == null || !isEmailValid(email)) return false;
        //TODO make findUserByEmail
        return userRepos.findUserByEmail(email) == null;
    }
}
