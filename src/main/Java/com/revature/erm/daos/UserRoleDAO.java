package com.revature.erm.daos;

import com.revature.erm.models.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDAO implements CrudDAO<UserRole> {
    public void save(UserRole newObject) {

    }

    public UserRole getById(String id) {
        return null;
    }

    public List<UserRole> getAll() {return null;}


    public void update(UserRole updatedObject) {

    }

    public void deleteById(String id) {

    }
}
