package com.revature.erm.services;

import com.revature.erm.dtos.requests.ListUserReimbursementsRequest;
import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.UpdateReimbursementRequest;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.models.*;
import com.revature.erm.repos.ReimbursementRepos;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;
import org.postgresql.util.ReaderInputStream;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReimbursementService {

    private ReimbursementRepos reimbursementRepos;


    public ReimbursementService(ReimbursementRepos reimbursementRepos) {this.reimbursementRepos = reimbursementRepos;}

    public List<Reimbursement> getReimbursementByStatusId(String statusId){
        return null;
    }

    public List<Reimbursement> getReimbursementByAuthorId(ListUserReimbursementsRequest lrur) { //String authorId)

        User author = lrur.getAuthorId();

        List<Reimbursement> reimbursements = reimbursementRepos.getReimbursementByAuthor_Id(author);//new ArrayList<>();

        return reimbursements;
    }

    public Reimbursement submitNewReimbursment(NewReimbursementRequest newReimbursementRequest) {

        Reimbursement newReimbursement = newReimbursementRequest.extractReimbursement();
        System.out.println("is new reimbursement null?: " + newReimbursement==null);
        // TODO encrypt provided password before storing in the database

        newReimbursement.setId(UUID.randomUUID().toString());

//        newReimbursement.setAuthor_id(newReimbursementRequest);
//        newReimbursement.setResolver_id("5c24b9ca-58ed-41c9-a619-7a19136b21f6");

        newReimbursement.setType_id("3");//setType(new ReimbursementType("3", "Other"));
        newReimbursement.setStatus_id("0");//setStatus(new ReimbursementStatus("0", "pending"));
        newReimbursement.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
        //newUser.setIsActive(true);
        reimbursementRepos.save(newReimbursement);

        return newReimbursement;//newUser;
    }



    public List<Reimbursement> changeReimbursementStatus(UpdateReimbursementRequest updateReimbursementRequest) {

        Reimbursement updateThisReimbursement = updateReimbursementRequest.extractReimbursement();
        //TODO create Update in reimbursementRepos
        reimbursementRepos.update(updateThisReimbursement);

        //TODO FIXME
        return reimbursementRepos.getReimbursementsByreimbId(updateThisReimbursement.getId());
    }

    public Boolean approveReimbursement(String reimbId) {
        //Reimbursement newReimbursement = newReimbursementRequest.extractReimbursement();
        try {
            if (reimbId == "1")
                return true;
            else if (reimbId == "2")
                return false;
            else
                throw new Exception();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("invalid user input");
        }
        return null;
    }
    public boolean denyReimbursement(String reimbId) {
        return false;
    }

    // List<Reimbursement> getReimbursementsByAuthorId(String authorId);
    // List<Reimbursement> getReimbursementsByStatusId(String statusId);
    // ResourceCreationResponse saveNewReimbursement(NewReimbursementRequest req);
    // boolean approveReimbursement(String reimbId);
    // boolean denyReimbursement(String reimbId);

}
