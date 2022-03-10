package com.revature.erm.models;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reimbursement {
    private String id;
    private Double amount;
    private Timestamp submitted;
    private Timestamp resolved = null;
    private String description;
    private String payment_id;
    /*private User author;
    private User resolver = null;*/
    private String author_id;
    private String resolver_id;
    //private ReimbursementStatus status;
    //private ReimbursementType type;
    private String status_id;
    private String type_id;

    public Reimbursement() { super(); }

    public Reimbursement(User resolver, Double amount, String description, Timestamp submitted) {
        this.resolver_id = resolver.getId();
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
    }

    public Reimbursement(String id, Double amount, String description, Timestamp submitted) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
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

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
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

    /*public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }*/

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

    /*public ReimbursementStatus getStatus() {
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
    }*/

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

    Reimbursement(String id, Double amount, Timestamp submitted, Timestamp resolved, String description,
                  String payment_id, String author, User resolver, String status, String type) {
        //this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        //this.resolved = resolved;
        this.description = description;
        //this.payment_id = payment_id;
        this.author_id = author;
        //this.resolver = resolver;
        this.status_id = status;
        this.type_id = type;

    }
    public Reimbursement(Double amount, String description/*, Timestamp submitted, User author, ReimbursementType type*/) {
        this.amount = amount;
        //this.submitted = submitted;
        this.description = description;
        //this.author = author;
        //this.type = type;
    }

    public Reimbursement(String id, String status_id){ this.id = id; this.status_id = status_id; }

}