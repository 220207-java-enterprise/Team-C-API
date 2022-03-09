package com.revature.erm.models;

public class UserRole {
    private String id;
    private String rolename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return rolename;
    }

    public void setRoleName(String rolename) {
        this.rolename = rolename;
    }

    public UserRole(String id, String rolename){
        this.id = id;
        this.rolename = rolename;
    }

    public UserRole(String id) {
        this.id = id;
        switch (id){
            case "0": this.rolename = "Admin";
            break;
            case "1": this.rolename = "Finance Manager";
            break;
            case "2": this.rolename = "Employee";
            break;
        }
    }
}
