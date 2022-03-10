package com.revature.erm.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.ListUserReimbursementsRequest;
import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateReimbursementRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.dtos.responses.ReimbursementResponse;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.dtos.responses.UserResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.services.TokenService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

    private final TokenService tokenService;
    private final ReimbursementService reimbursementService;
    private final ObjectMapper mapper;
    // doGet: get reimbs by id, by status, by authorid
    // doPost: saving a new reimb
    // doPut: updating a reimb (approve/deny)

    public ReimbursementServlet(TokenService tokenService, ReimbursementService reimbursementService, ObjectMapper mapper) {
        this.tokenService = tokenService;
        this.reimbursementService = reimbursementService;
        this.mapper = mapper;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        try {
            if (requester == null) {
                System.out.println("Unauthenticated request made to UserServlet#doGet");
                resp.setStatus(401);
            }
            if (!requester.getRole().equals("FManager")) {
                System.out.println("Unauthorized request made by user: " + requester.getUsername());
                resp.setStatus(403); // FORBIDDEN
                return;
            }
            String sessionUserId = parseSessionUserId((HttpSession) requester);//pull id from the session so the authorId can match

            ListUserReimbursementsRequest listUserReimbursementsRequest = mapper.readValue(req.getInputStream(), ListUserReimbursementsRequest.class);
            listUserReimbursementsRequest.setAuthorId(sessionUserId);

            List<Reimbursement> reimbursementResponses = reimbursementService.getReimbursementByAuthorId
                    (listUserReimbursementsRequest);
            String payload = mapper.writeValueAsString(reimbursementResponses);
            resp.setContentType("application/json");
            resp.getWriter().write(payload);
            }

        catch (InvalidRequestException | DatabindException e) {
            e.printStackTrace();
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();

        try {
            Principal ifEmp = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            if(!(ifEmp.getRole().equals("Employee"))){
                throw new InvalidRequestException("Not an Employee!");
            }
            if (ifEmp == null) {
                resp.setStatus(401);
                return;
            }
            NewReimbursementRequest reimbursementRequest = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
            Reimbursement newReimbursement = reimbursementService.submitNewReimbursment(reimbursementRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(newReimbursement.getId()));
            respWriter.write(payload);
        }
        catch (InvalidRequestException | DatabindException e) {
            e.printStackTrace();
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }

    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter respWriter = resp.getWriter();

        try {
            Principal ifAdmin = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            //check if time expired on token = null
            //System.out.println(ifAdmin);
            if(!(ifAdmin.getRole().equals("Finance Manager"))){
                throw new InvalidRequestException("Not an Finance Manager!");
            }
            if (ifAdmin == null) {
                resp.setStatus(401);
                return;
            }
            UpdateReimbursementRequest updateReimbursement = mapper.readValue(req.getInputStream(), UpdateReimbursementRequest.class);
            Reimbursement updatedReimburement = reimbursementService.changeReimbursementStatus(updateReimbursement);

            resp.setStatus(201); // Succesful
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(updatedReimburement.getId()));
            respWriter.write(payload);
        }
                /*NewReimbursementRequest newReimbursementRequest = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
                System.out.println("about to launch submitNewReimbursement in ReimbursementServlet.java");

                newReimbursementRequest.setAuthorId(sessionUserId); //set authorId being pulled from the session
                System.out.println("setAuthorId(sessionUserId) in reimbursementServlet has been called");
                Reimbursement newReimbursement = reimbursementService.submitNewReimbursment(newReimbursementRequest);
                resp.setStatus(201); // CREATED
                resp.setContentType("application/json");
                String payload = mapper.writeValueAsString(new ResourceCreationResponse(newReimbursement.getId()));
                respWriter.write(payload);*/

        catch (InvalidRequestException | DatabindException e) {
            e.printStackTrace();
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }

    }



    public String parseSessionUserId(HttpSession session){

        Principal parseThis = (Principal) session.getAttribute("authUser");

        return parseThis.getId();
    }

}
