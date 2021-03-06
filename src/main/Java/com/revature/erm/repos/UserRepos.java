package com.revature.erm.repos;

import com.revature.erm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepos extends JpaRepository <User, String> {

//    @Modifying
//    @Query("update ers_users u set u.username = ?1, u.email = ?2, u.password = ?3, u.first_name = ?4, " +
//            "u.last_name = ?5, u.is_active = ?6, u.role_id = ?7 where u.user_id = ?8")
//    User update(String id);
    @Query(value = "Select id, first_name, last_name, role_id from ers_users e where e.id = ?1",
            nativeQuery = true)
    User displayUserByIdFirstNameLastNameRoleName(String id);

    User findUserByUsernameAndPassword (String username, String password);

    User findUserByUsername (String username);

    User findUserByEmail (String email);


}
