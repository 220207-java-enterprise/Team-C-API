package com.revature.erm.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table (name = "ers_reimbursements")
public class Reimbursement {

    @Id
    private String reimb_id;

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


    @ManyToOne
    @JoinColumn (name = "author_id", nullable = false)
    private User author_id;

    @ManyToOne
    @JoinColumn (name = "resolver_id")
    private User resolver_id;

    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ReimbursementStatus status_id;

    @OneToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ReimbursementType type_id;

    public Reimbursement() { super(); }

    public Reimbursement(String reimb_id, Double amount, Timestamp submitted, Timestamp resolved, String description,
                         String payment_id, User author_id, User resolver_id, ReimbursementStatus status_id,
                         ReimbursementType type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
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

    public ReimbursementStatus getStatus_id() {
        return status_id;
    }

    public void setStatus_id(ReimbursementStatus status_id) {
        this.status_id = status_id;
    }

    public ReimbursementType getType_id() {
        return type_id;
    }

    public void setType_id(ReimbursementType type_id) {
        this.type_id = type_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return Objects.equals(reimb_id, that.reimb_id)
                && Objects.equals(amount, that.amount)
                && Objects.equals(submitted, that.submitted)
                && Objects.equals(resolved, that.resolved)
                && Objects.equals(description, that.description)
                && Objects.equals(payment_id, that.payment_id)
                && Objects.equals(author_id, that.author_id)
                && Objects.equals(resolver_id, that.resolver_id)
                && Objects.equals(status_id, that.status_id)
                && Objects.equals(type_id, that.type_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimb_id, amount, submitted, resolved, description, payment_id, author_id,
                resolver_id, status_id, type_id);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id=" + author_id +
                ", resolver_id=" + resolver_id +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}