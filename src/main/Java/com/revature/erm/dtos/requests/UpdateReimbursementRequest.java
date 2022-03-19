package com.revature.erm.dtos.requests;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.ReimbursementStatus;
import com.revature.erm.models.User;

public class UpdateReimbursementRequest {

    private String reimb_id;
    private User resolver_id;
    private ReimbursementStatus status_id;

    public UpdateReimbursementRequest(){
        super();
    }

    public UpdateReimbursementRequest(String reimb_id, User resolver_id, ReimbursementStatus status_id) {
        this.reimb_id = reimb_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public User getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(User resolver_id) {
        this.resolver_id = resolver_id;
    }

    public ReimbursementStatus getStatus_id() {
        return status_id;
    }

    public void setStatus_id(ReimbursementStatus status_id) {
        this.status_id = status_id;
    }

    public Reimbursement extractReimbursement() {

        return new Reimbursement(this.reimb_id, this.resolver_id, this.status_id);
    }

}
