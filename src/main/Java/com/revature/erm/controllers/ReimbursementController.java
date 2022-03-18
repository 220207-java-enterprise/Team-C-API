package com.revature.erm.controllers;

import com.revature.erm.dtos.responses.ReimbursementResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reimburse")
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

}
