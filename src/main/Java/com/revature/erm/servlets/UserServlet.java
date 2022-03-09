package com.revature.erm.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateUserRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.dtos.responses.UserResponse;
import com.revature.erm.models.User;
import com.revature.erm.services.UserService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final UserService userService;
    private final ObjectMapper mapper;

    public UserServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] reqFrags = req.getRequestURI().split("/");
        if (reqFrags.length == 4 && reqFrags[3].equals("availability")) {
            checkAvailability(req, resp);
            return; // necessary, otherwise we end up doing more work than was requested
        }

        // TODO implement some security logic here to protect sensitive operations

        // get users (all, by id, by w/e)
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(401);
            return;
        }

        Principal requester = (Principal) session.getAttribute("authUser");

        if (!requester.getRole().equals("ADMIN")) {
            resp.setStatus(403); // FORBIDDEN
        }

        List<UserResponse> users = userService.getAllUsers();
        String payload = mapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().write(payload);


    }

    // registration endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();

        try {

            NewUserRequest newUserRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            User newUser = userService.register(newUserRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(newUser.getId()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter respWriter = resp.getWriter();

        try{
//            Principal ifAdmin = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
//            //check if time expired on token = null
//            //System.out.println(ifAdmin);
//            if(!(ifAdmin.getRoleId().equals("Admin"))){
//                throw new InvalidRequestException("Not an Admin!");
//            }
            UpdateUserRequest updateUser = mapper.readValue(req.getInputStream(), UpdateUserRequest.class);
            User updatedUser = userService.updatedUser(updateUser);
            System.out.println();

            resp.setStatus(201); // Succesful
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(updatedUser.getId()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            resp.setStatus(400); // BAD REQUEST
            e.printStackTrace();
        } catch (ResourceConflictException e) {
            e.printStackTrace();
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

    protected void checkAvailability(HttpServletRequest req, HttpServletResponse resp) {
        String usernameValue = req.getParameter("username");
        String emailValue = req.getParameter("email");
        if (usernameValue != null) {
            if (userService.isUsernameAvailable(usernameValue)) {
                resp.setStatus(204); // NO CONTENT
            } else {
                resp.setStatus(409);
            }
        } else if (emailValue != null) {
            if (userService.isEmailAvailable(emailValue)) {
                resp.setStatus(204);
            } else {
                resp.setStatus(409);
            }
        }
    }

}
