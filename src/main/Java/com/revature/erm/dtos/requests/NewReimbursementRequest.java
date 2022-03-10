package com.revature.erm.dtos.requests;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.ReimbursementStatus;
import com.revature.erm.models.ReimbursementType;
import com.revature.erm.models.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NewReimbursementRequest {

    //private String id;
    private Double amount;
    private Timestamp submitted;
    //private LocalDateTime resolved;
    private String description;
    private String payment_id = null;
    //private User author;
    private String author_id;
    private String resolver;
    private ReimbursementStatus status;
    private ReimbursementType type;

    public NewReimbursementRequest(){ super(); }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAuthorId() {
        return author_id;
    }

    public void setAuthorId(String author) {
        this.author_id = author;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
        this.status = status;
    }

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

    public Reimbursement extractReimbursement() { return new Reimbursement(resolver, amount, description,
            new Timestamp(System.currentTimeMillis())/*, submitted,author, type*/); }

    //public boolean isAuthorActive(Reimbursement reimbursement) { return reimbursement.getAuthor().isActive(); }

    public NewReimbursementRequest(Double amount, String description, String payment_id,
                                   User author, User resolver, ReimbursementType type){

        this.amount = amount;
        this.description = description;
        //this.payment_id = payment_id;
        //this.author = author;
        //this.resolver = resolver;
        //this.type = type;
        //this.status = new ReimbursementStatus();
        //this.submitted = Timestamp.;

    }

    @Override
    public String toString() {
        return "NewReimbursementRequest{" +
                "amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' + //.getId() + '\'' +
                ", resolver_id='" + resolver + '\'' +
                ", type='" + type.getId() + '\'' +
                ", status='" + status.getId() + '\'' +
                ", submitted='" + submitted + '\'' +
                '}';
    }

}
