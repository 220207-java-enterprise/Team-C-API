package com.revature.erm.dtos.requests;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.ReimbursementStatus;
import com.revature.erm.models.User;

import java.time.LocalDateTime;

public class UpdateReimbursementRequest {

    private String reimbId;
    private String resolverId;
    private String status;

    public UpdateReimbursementRequest(){
        super();
    }

    public UpdateReimbursementRequest(String reimbId, String resolverId, String status) {
        this.reimbId = reimbId;
        this.resolverId = resolverId;
        this.status = status;
    }

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
