package com.revature.erm.dtos.requests;

public class ListUserReimbursementsRequest {

    private String author_id;

    public String getAuthorId() {
        return author_id;
    }

    public void setAuthorId(String author_id) {
        this.author_id = author_id;
    }

    public ListUserReimbursementsRequest(){ super(); }

    @Override
    public String toString() {
        return "ListUserReimbursementsRequest{" +
                "author_id='" + author_id + '}';
    }
}
