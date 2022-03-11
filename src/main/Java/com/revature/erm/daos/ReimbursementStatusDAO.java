package com.revature.erm.daos;

import com.revature.erm.models.ReimbursementStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReimbursementStatusDAO implements CrudDAO<ReimbursementStatus>{
    public void save(ReimbursementStatus newObject) {

    }

    public ReimbursementStatus getById(String id) {
        return null;
    }

    public List<ReimbursementStatus> getAll() {
        return null;
    }

    public void update(ReimbursementStatus updatedObject) {

    }

    public void deleteById(String id) {

    }
}
