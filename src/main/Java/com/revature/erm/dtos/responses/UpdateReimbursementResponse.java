package com.revature.erm.dtos.responses;

import com.revature.erm.models.Reimbursement;

public class UpdateReimbursementResponse {
    private String id;
    private Double amount;
    private String description;
    private String authorfirstname;
    private String authorlastname;
    private String resolverfirstname;
    private String resolverlastname;
    private String status_id;
    private String type_id;

    public UpdateReimbursementResponse(Reimbursement reimbursement){
        this.id = reimbursement.getReimb_id();
        this.amount = reimbursement.getAmount();
        this.description = reimbursement.getDescription();
        this.authorfirstname = reimbursement.getAuthor_id().getFirstName();
        this.authorlastname = reimbursement.getAuthor_id().getLastName();
        this.resolverfirstname = reimbursement.getResolver_id().getFirstName();
        this.resolverlastname = reimbursement.getResolver_id().getLastName();
        this.status_id = reimbursement.getStatus_id().getStatus();
        this.type_id = reimbursement.getType_id().getType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorfirstname() {
        return authorfirstname;
    }

    public void setAuthorfirstname(String authorfirstname) {
        this.authorfirstname = authorfirstname;
    }

    public String getAuthorlastname() {
        return authorlastname;
    }

    public void setAuthorlastname(String authorlastname) {
        this.authorlastname = authorlastname;
    }

    public String getResolverfirstname() {
        return resolverfirstname;
    }

    public void setResolverfirstname(String resolverfirstname) {
        this.resolverfirstname = resolverfirstname;
    }

    public String getResolverlastname() {
        return resolverlastname;
    }

    public void setResolverlastname(String resolverlastname) {
        this.resolverlastname = resolverlastname;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
