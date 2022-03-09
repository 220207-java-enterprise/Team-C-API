package com.revature.erm.models;
import javax.management.relation.RelationNotification;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public String getRole_id() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(){
        this.id = null;
        this.username = null;
        this.email = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.isActive = false;
        this.role = null;
    }
    public User(String id, String username, String email, String password,
                String firstName, String lastName, boolean isActive, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.role = role;

        //System.out.println(this.id);
    }

    public User(String firstName, String lastName, String email, String username, String password, String role_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role_id;
    }
}
