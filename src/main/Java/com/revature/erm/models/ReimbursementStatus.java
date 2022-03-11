package com.revature.erm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "reimbursement_status")
public class ReimbursementStatus {
    @Id
    private String id;
    @Column (name = "status", unique = true)
    private String status;

    public ReimbursementStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void ReimbursementStatus(String id, String status) {
        this.id = id;
        this.status = status;
    };
}
