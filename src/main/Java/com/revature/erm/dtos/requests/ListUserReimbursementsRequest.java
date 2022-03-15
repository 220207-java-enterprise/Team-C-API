package com.revature.erm.dtos.requests;

import com.revature.erm.models.User;

public class ListUserReimbursementsRequest {

    private User author_id;

    public User getAuthorId() {
        return author_id;
    }

    public void setAuthorId(User author_id) {
        this.author_id = author_id;
    }

    public ListUserReimbursementsRequest(){ super(); }

    @Override
    public String toString() {
        return "ListUserReimbursementsRequest{" +
                "author_id='" + author_id + '}';
    }
}
