package com.revature.erm.repos;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimburstmentRepos extends CrudRepository<Reimbursement, String> {
    List<Reimbursement> getReimbursementsByreimbId(double size);

    @Query("from Reimbursement b where b.owner = ?1")
    List<Reimbursement> getBootsByOwner(User owner);

    @Query(
            value = "SELECT * from reimbursement where material = ?1",
            nativeQuery = true
    )
    List<Reimbursement> findReimbursementByMaterial(String material);
}
