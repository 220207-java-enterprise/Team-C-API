package com.revature.erm.services;

import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.UpdateReimbursementRequest;
import com.revature.erm.dtos.responses.ReimbursementResponse;
import com.revature.erm.models.*;
import com.revature.erm.repos.ReimbursementRepos;
import com.revature.erm.repos.UserRepos;
import com.revature.erm.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReimbursementService {

    private ReimbursementRepos reimbursementRepos;
    private UserRepos userRepos;

    @Autowired
    public ReimbursementService(ReimbursementRepos reimbursementRepos, UserRepos userRepos) {
        this.reimbursementRepos = reimbursementRepos;
        this.userRepos = userRepos;
    }

    public List<ReimbursementResponse> getAllReimbursements() {

        //Old way preJava 8
        /* List<Reimbursement> Reimbursement = reimbursementRepos.findAll();
        List<ReimbursementResponse> reimbursementResponses = new ArrayList<>();
        for (Reimbursement reimbursement : Reimbursement) {
            reimbursementResponses.add(new ReimbursementResponse((Reimbursement)));
        return reimbursementResponses;*/

        return reimbursementRepos.findAll()
                .stream()
                .map(ReimbursementResponse::new)
                .collect(Collectors.toList());

    }


    public List<ReimbursementResponse> getReimbursementByAuthorId(String lrur) { //String authorId)

        return reimbursementRepos.getReimbursementByAuthor_Id(lrur)
                .stream()
                .map(ReimbursementResponse::new)
                .collect(Collectors.toList());
    }

    public Reimbursement submitNewReimbursement(NewReimbursementRequest newReimbursementRequest) {
        //necessary to only have to input user by Id
        Optional<User> Extract = userRepos.findById(newReimbursementRequest.getAuthor_id().getId());

        Reimbursement newReimbursement = newReimbursementRequest.extractReimbursement();
        newReimbursement.setAuthor_id(Extract.get());
        newReimbursement.setReimb_id(UUID.randomUUID().toString());
        newReimbursement.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
        newReimbursement.setStatus_id(new ReimbursementStatus("0", "Pending"));

        reimbursementRepos.save(newReimbursement);

        return newReimbursement;
    }


    public Reimbursement approveOrDenyReimbursementStatus(UpdateReimbursementRequest updateReimbursementRequest) {

        Reimbursement originalReimbursement = reimbursementRepos.findById(updateReimbursementRequest.getReimb_id())
                .orElseThrow(ResourceNotFoundException::new);

        Optional<User> Extract = userRepos.findById(updateReimbursementRequest.getResolver_id().getId());

        Reimbursement updateThisReimbursement = updateReimbursementRequest.extractReimbursement();
        updateThisReimbursement.setReimb_id(originalReimbursement.getReimb_id());
        updateThisReimbursement.setAmount(originalReimbursement.getAmount());
        updateThisReimbursement.setAuthor_id(originalReimbursement.getAuthor_id());
        updateThisReimbursement.setSubmitted(originalReimbursement.getSubmitted());
        updateThisReimbursement.setDescription(originalReimbursement.getDescription());
        updateThisReimbursement.setType_id(originalReimbursement.getType_id());
        updateThisReimbursement.setResolver_id(Extract.get());
        updateThisReimbursement.setResolved(Timestamp.valueOf(LocalDateTime.now()));

        // TODO validate that this update is good to persist

        // TODO map new/updated values from updateThisReimb to the originalReimb

        reimbursementRepos.save(updateThisReimbursement);

        return updateThisReimbursement;

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
        } catch (Exception e) {
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



