package com.revature.erm.dtos.requests;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.ReimbursementStatus;
import com.revature.erm.models.User;

import java.time.LocalDateTime;

public class UpdateReimbursementRequest {

    private String id;
    private String status_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public Reimbursement extractReimbursement() { return new Reimbursement(id, status_id); }

}
