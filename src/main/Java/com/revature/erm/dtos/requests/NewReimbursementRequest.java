package com.revature.erm.dtos.requests;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.ReimbursementStatus;
import com.revature.erm.models.ReimbursementType;
import com.revature.erm.models.User;
import com.revature.erm.repos.UserRepos;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NewReimbursementRequest {

    private Double amount;
    private String description;
    private User author_id;
    private ReimbursementType type_id;

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

    public User getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(User author_id) {
        this.author_id = author_id;
    }

    public ReimbursementType getType() {
        return type_id;
    }

    public void setType(ReimbursementType type) {
        this.type_id = type;
    }

    public Reimbursement extractReimbursement() {
        String reimb_id = null;
        Timestamp submitted = null;
        Timestamp resolved = null;
        String payment_id = null;
        User resolver = null;
        ReimbursementStatus status = null;
        ReimbursementType newType = new ReimbursementType(this.type_id.getId(), this.type_id.getType());
        return new Reimbursement(reimb_id, this.amount, submitted, resolved, this.description,
                payment_id, this.author_id, resolver, status,
                newType);
    }

    public NewReimbursementRequest(Double amount, String description,
                                   User author_id, ReimbursementType type_id) {
        this.amount = amount;
        this.description = description;
        this.author_id = author_id;
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "NewReimbursementRequest{" +
                "amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", author_id='" + author_id + '\'' +
                ", type='" + type_id + '\'' +
                '}';
    }

}
