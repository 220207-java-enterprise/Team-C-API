package com.revature.erm.dtos.responses;

import com.revature.erm.models.Reimbursement;

public class ReimbursementResponse {
    private String id;
    private Double amount;
    private String description;
    private String author_id;
    private String resolver_id;
    private String status_id;
    private String type_id;

    public ReimbursementResponse(Reimbursement reimbursement) {
        this.id = reimbursement.getId();
        this.amount = reimbursement.getAmount();
        this.description = reimbursement.getDescription();
        this.author_id = reimbursement.getAuthor_id().getId();
        if (this.resolver_id == null){
            this.resolver_id = "Pending Resolver";
        }
        else {
            this.resolver_id = reimbursement.getResolver_id().getId();
        }
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

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(String resolver_id) {
        this.resolver_id = resolver_id;
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
