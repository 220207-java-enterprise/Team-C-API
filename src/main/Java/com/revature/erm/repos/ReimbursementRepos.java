package com.revature.erm.repos;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepos extends CrudRepository<Reimbursement, String> {

    @Query("from ers_reimbursements r where r.reimb_id = ?1")
    List<Reimbursement> getReimbursementsByreimbId(String reimb_id);

    @Query("from ers_reimbursements r where r.author_id = ?1")
    List<Reimbursement> getReimbursementByAuthor_Id(String author_id);

    /*@Query(
            value = "SELECT * from reimbursement where material = ?1",
            nativeQuery = true
    )
    List<Reimbursement> findReimbursementByMaterial(String material);*/

    @Modifying
    @Query("update ers_reimbursements r set r.status_id = ?1, r.resolved = ?2, where r.reimb_id = ?3")
    List<Reimbursement> update(Reimbursement updatedReimbursement);
}
