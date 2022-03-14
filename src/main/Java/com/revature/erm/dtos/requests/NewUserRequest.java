package com.revature.erm.dtos.requests;

import com.revature.erm.models.User;
import com.revature.erm.models.UserRole;

public class NewUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private UserRole role_id;
    //private Role role

    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String firstName, String lastName, String email, String username, String password, UserRole role_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole_id() {
        return role_id;
    }

    public void setRole_id(UserRole role_id) {
        this.role_id = role_id;
    }

    public User extractUser() {
        return new User(firstName, lastName, email, username, password, role_id);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}