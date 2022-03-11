package com.revature.erm.models;

import javax.persistence.*;

@Entity
@Table (name = "ers_users"  )
public class User {
    @Id
    private String id;

    @Column (name = "user_name", nullable = false, unique = true)
    private String username;
    @Column (name = "email", nullable = false, unique = true)
    private String email;
    @Column (name = "password", nullable = false)
    private String password;
    @Column (name = "first_name", nullable = false)
    private String firstName;
    @Column (name = "last_name", nullable = false)
    private String lastName;
    @Column(columnDefinition = "boolean default false")
    private boolean isActive;
    @OneToOne
    @JoinColumn(name = "role_id")
    private UserRole roleId;

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

    public UserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRole roleId) {
        this.roleId = roleId;
    }

    public User(){
        super();
    }
    public User(String id, String username, String email, String password,
                String firstName, String lastName, boolean isActive, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.roleId = role;

        //System.out.println(this.id);
    }

    public User(String firstName, String lastName, String email, String username, String password, UserRole role_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = role_id;
    }
}
