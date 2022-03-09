package com.revature.erm.services;

import com.revature.erm.daos.ReimbursementDAO;
import com.revature.erm.dtos.requests.ListUserReimbursementsRequest;
import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.UpdateReimbursementRequest;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.models.*;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;
import org.postgresql.util.ReaderInputStream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;


    public ReimbursementService(ReimbursementDAO reimbursementDAO) {this.reimbursementDAO = reimbursementDAO;}

    public List<Reimbursement> getReimbursementByStatusId(String statusId){
        return null;
    }

    public List<Reimbursement> getReimbursementByAuthorId(ListUserReimbursementsRequest lrur) { //String authorId){
        //todo extract author_id from listuserreimbursementsrequest
        //todo verify the two ids
        //todo implement getbyid in reimbursementdao

        String author = lrur.getAuthorId();

        List<Reimbursement> reimbursements = reimbursementDAO.getAllByAuthorId(author);//new ArrayList<>();

        //UpdateReimbursementRequest updateReimbursementRequest =
        return reimbursements;
    }

    public Reimbursement submitNewReimbursment(NewReimbursementRequest newReimbursementRequest) {

        System.out.println("beginning of submitNewReimbursement in ReimbursementService.java");
        Reimbursement newReimbursement = newReimbursementRequest.extractReimbursement();

        // TODO encrypt provided password before storing in the database


        System.out.println("setting up id w/uuid next in ReimbursementService.java");
        newReimbursement.setId(UUID.randomUUID().toString());
        System.out.println("setting author via hardcoding in ReimbursementService.java");
        /*newReimbursement.setAuthor_id(new User("38a63ca8-f46d-4fc1-b1c7-b87863e82c9b", "bidol567",
                "billy_idol@revature.com", "p4$$WoRD","Billy","Idol",
                true, new UserRole("2", "Employee")));*/

        System.out.println("testing is author is active in ReimbursementService.java");
        /*if (!newReimbursementRequest.isAuthorActive(newReimbursement)) {
            throw new InvalidRequestException("Author is not active");
        }*/
        newReimbursement.setAuthor_id(newReimbursementRequest.getAuthorId());
        newReimbursement.setResolver_id("ba2fa4f0-35cd-4522-8cb7-a4589f9bebe7");
        /*newReimbursement.setResolver(new User("ba2fa4f0-35cd-4522-8cb7-a4589f9bebe7", "finance_Manager",
                "financier3@gmail.com", "pA$$W0rD","Billy","Crystal",
                true, new UserRole("1", "Finance Manager")));*/

        //newReimbursement.setId(UUID.randomUUID().toString());

        newReimbursement.setType_id("3");//setType(new ReimbursementType("3", "Other"));
        newReimbursement.setStatus_id("0");//setStatus(new ReimbursementStatus("0", "pending"));
        newReimbursement.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
        //newUser.setIsActive(true);
        reimbursementDAO.save(newReimbursement);

        return newReimbursement;//newUser;
    }

    public Reimbursement changeReimbursementStatus(UpdateReimbursementRequest updateReimbursementRequest) {

        System.out.println("start of changeReimbursementStatus() in ReimbursementService.java");
        Reimbursement updateThisReimbursement = updateReimbursementRequest.extractReimbursement();
        System.out.println("extracted reimbursement from request");
        reimbursementDAO.update(updateThisReimbursement);
        System.out.println("update from reimbursementDAO has been run");


        return reimbursementDAO.getById(updateThisReimbursement.getId());
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
