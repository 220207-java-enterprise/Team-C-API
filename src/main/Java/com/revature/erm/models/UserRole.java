package com.revature.erm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "ers_user_roles")
public class UserRole {
    @Id
    private String id;

    @Column (name = "role_name", nullable = false)
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
    public UserRole(){
        super();
    }
}