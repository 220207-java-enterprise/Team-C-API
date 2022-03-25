package com.revature.erm.dtos.requests;

import com.revature.erm.models.User;
import com.revature.erm.models.UserRole;

public class UpdateUserRequest {

    private String userId;
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private UserRole roleId;

    public UpdateUserRequest() {
        super();
    }

    public UpdateUserRequest(String userId, String userName, String email, String password, String firstName,
                             String lastName, boolean isActive, UserRole roleId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRole roleId) {
        this.roleId = roleId;
    }

    public User extractUser() {
        return new User(this.userId, this.userName, this.email, this.password, this.firstName, this.lastName,
                this.isActive, this.roleId);
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + password + '\'' +
                ", givenName='" + firstName + '\'' +
                ", surname='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
