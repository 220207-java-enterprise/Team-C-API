package com.revature.erm.repos;

import com.revature.erm.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepos extends CrudRepository <User, String> {

    @Modifying
    @Query("update ers_users u set u.username = ?, u.email = ?, u.password = ?, u.first_name = ?, " +
            "u.last_name = ?, u.is_active = ?, u.role_id = ? where u.user_id = ?")
    List<User> update(User updatedUser);

    @Query
    User findUserByUsernameAndPassword (String username, String password);

    @Query
    User findUserByUsername (String username);

    @Query
    User findUserByEmail (String email);
}
