package com.revature.erm.daos;

import com.revature.erm.models.ReimbursementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReimbursementTypeDAO implements CrudDAO<ReimbursementType> {
    public void save(ReimbursementType newObject) {

    }

    public ReimbursementType getById(String id) {
        return null;
    }

    public List<ReimbursementType> getAll() {
        return null;
    }

    public void update(ReimbursementType updatedObject) {

    }

    public void deleteById(String id) {

    }
}
