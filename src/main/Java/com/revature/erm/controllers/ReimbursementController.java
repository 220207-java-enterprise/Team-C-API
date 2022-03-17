package com.revature.erm.controllers;

import com.revature.erm.dtos.responses.ReimbursementResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(path = "fromuser")
    List<Reimbursement> getallReimbursements (){
        return reimbursementService.getAllReimbursements();
    }

}
