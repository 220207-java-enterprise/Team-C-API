package com.revature.erm.controllers;

import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.UpdateReimbursementRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.dtos.responses.ReimbursementResponse;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.dtos.responses.UpdateReimbursementResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reimbursement")
public class ReimbursementController {
    private final ReimbursementService reimbursementService;
    private final TokenService tokenService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService, TokenService tokenService) {
        this.reimbursementService = reimbursementService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<ReimbursementResponse> getallReimbursements(){
        return reimbursementService.getAllReimbursements();
    }

    @GetMapping(path = "fromuser")
    public List<ReimbursementResponse> getallReimbursementsById(@RequestParam Map<String, String> requestParams){
        String author_id = requestParams.get("author_id");
        if (requestParams.size() != 1) {
            throw new InvalidRequestException("This endpoint expects only a single request parameter to be provided; " +
                    "author_id.");
        }
        return reimbursementService.getReimbursementByAuthorId(author_id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ReimbursementResponse postRemibursement(@RequestBody NewReimbursementRequest newReimbursementRequest,
                                                      HttpServletRequest req){

        Principal ifEmployee = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if (ifEmployee == null){
            throw new ResourceNotFoundException("Login token issue: wrong token, not provided, or may have expired.");
        }
        if(!(ifEmployee.getRole().equals("Employee"))) {
            throw new InvalidRequestException("Must be an Employee to create reimbursement!");
        }

        Reimbursement newReimbursement = reimbursementService.submitNewReimbursement(newReimbursementRequest);
        return new ReimbursementResponse(newReimbursement);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public UpdateReimbursementResponse approveOrDenyRemibursement
            (@RequestBody UpdateReimbursementRequest updateReimbursementRequest, HttpServletRequest req){
        Principal ifManager = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if (ifManager == null){
            throw new ResourceNotFoundException("Login token issue: wrong token, not provided, or may have expired.");
        }
        if(!(ifManager.getRole().equals("Finance Manager"))) {
            throw new InvalidRequestException("Must be a Finance Manager to approve reimbursement!");
        }

        Reimbursement updatedReimbursement = reimbursementService.approveOrDenyReimbursementStatus
                (updateReimbursementRequest);

        return new UpdateReimbursementResponse(updatedReimbursement);
    }
}
