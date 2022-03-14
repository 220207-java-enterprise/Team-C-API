package com.revature.erm.repos;

import com.revature.erm.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends CrudRepository <User, String> {
}
