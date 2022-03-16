package com.revature.erm.models;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table (name = "ers_reimbursements")
public class Reimbursement {
    @Id
    private String id;
    @Column (name = "amount", nullable = false, columnDefinition = "numeric (6,2)")
    private Double amount;
    @Column (name = "submitted", columnDefinition = "timestamp default current_timestamp")
    private Timestamp submitted;
    @Column (name = "resolved", columnDefinition = "timestamp default current_timestamp")
    private Timestamp resolved = null;
    @Column (name = "description", nullable = false)
    private String description;
    @Column (name = "payment_id")
    private String payment_id;
    /*private User author;
    private User resolver = null;*/
    //TODO @OneToOne or @ManyToOne on com.revature.erm.models.Reimbursement.author_id references an unknown entity
    @OneToOne
    @JoinColumn (name = "author_id", nullable = false)
    private User author_id;
    @OneToOne
    @JoinColumn (name = "resolver_id", nullable = false)
    private User resolver_id;
    //private ReimbursementStatus status;
    //private ReimbursementType type;
    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private String status_id;
    @OneToOne
    @JoinColumn(name = "type_id", nullable = false)
    private String type_id;

    public Reimbursement() { super(); }

    public Reimbursement(User resolver, Double amount, String description, Timestamp submitted) {
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.resolver_id = resolver;
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

    public User getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(User author_id) {
        this.author_id = author_id;
    }

    public User getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(User resolver_id) {
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
                  String payment_id, User author, User resolver, String status, String type) {
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