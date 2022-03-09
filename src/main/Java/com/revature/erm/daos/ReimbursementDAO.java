package com.revature.erm.daos;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.util.ConnectionFactory;
import com.revature.erm.util.exceptions.DataSourceException;
import com.revature.erm.util.exceptions.ResourcePersistenceException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {

    /*private final String rootSelect = "SELECT " +
            "eu.user_id, eu.username, eu.email, eu.password, eu.first_name, eu.last_name, eu.is_active, eu.role_id, eur.role " +
            "FROM ers_users eu " +
            "JOIN ers_reimbursments er " +
            "ON eu.user_id = eur._id ";*/
    private final String rootSelect = "SELECT " +
            "reimb_id, amount, submitted, resolved, description, payment_id, author_id, resolver_id, status_id, type_id " +
            "FROM ers_reimbursments ";

    public void save(Reimbursement newReimbursement) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_reimbursments VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newReimbursement.getId());
            pstmt.setInt(2, newReimbursement.getAmount());
            pstmt.setTimestamp(3, newReimbursement.getSubmitted());
            pstmt.setTimestamp(4, newReimbursement.getResolved());
            pstmt.setString(5, newReimbursement.getDescription());
            pstmt.setString(6, newReimbursement.getPayment_id());
            pstmt.setString(7, newReimbursement.getAuthor_id());//.getId());
            //if(newReimbursement.getResolver() != null)
                pstmt.setString(8, newReimbursement.getResolver_id());//.getId());
            //else
                //pstmt.setString(8, null);
            pstmt.setString(9, newReimbursement.getStatus_id());//getStatus().getId());
            pstmt.setString(10, newReimbursement.getType_id());//getType().getId());

            System.out.println(pstmt);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("pstmt.executeUpdate() ran in ReimbursementDao.java?");
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist reimbursement to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public Reimbursement getById(String id) {

        Reimbursement matchingReimbursement = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE reimb_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                matchingReimbursement= new Reimbursement();
                //authUser.setId(rs.getString("user_id"));
                matchingReimbursement.setId(rs.getString("reimb_id"));
                matchingReimbursement.setAmount(rs.getInt("amount"));
                matchingReimbursement.setSubmitted(rs.getTimestamp("submitted"));
                matchingReimbursement.setResolved(rs.getTimestamp("resolved"));
                matchingReimbursement.setDescription(rs.getString("description"));
                matchingReimbursement.setPayment_id(rs.getString("payment_id"));
                matchingReimbursement.setStatus_id(rs.getString("status_id"));
                matchingReimbursement.setResolver_id(rs.getString("resolver_id"));
                matchingReimbursement.setType_id(rs.getString("type_id"));
                matchingReimbursement.setAuthor_id(rs.getString("author_id"));
                //todo figure out how to pull the ers_users table, ers_user_roles, and ers_reimbursments table in a single select statement
                //matchingReimbursement.setAuthor(new User(rs.getString("author_id"), rs.getString("SELECT username FROM ers_users WHERE user_id = author_id")));

                System.out.println("updated reimbursement found after update was made");
                return matchingReimbursement;
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return null;
    }

    public List<Reimbursement> getAllByAuthorId(String author_id){


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE author_id = ?");
            pstmt.setString(1, author_id);

            ResultSet rs = pstmt.executeQuery();
            ArrayList<Reimbursement> allMatchingReimbursements = new ArrayList<Reimbursement>();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setId(rs.getString("reimb_id"));
                reimbursement.setAmount(rs.getInt("amount"));
                reimbursement.setSubmitted(rs.getTimestamp("submitted"));
                reimbursement.setResolved(rs.getTimestamp("resolved"));
                reimbursement.setDescription(rs.getString("description"));
                reimbursement.setPayment_id(rs.getString("payment_id"));
                reimbursement.setAuthor_id(rs.getString("author_id"));
                reimbursement.setResolver_id(rs.getString("resolver_id"));
                reimbursement.setStatus_id(rs.getString("status_id"));
                reimbursement.setType_id(rs.getString("type_id"));
                allMatchingReimbursements.add(reimbursement);

            }
            return allMatchingReimbursements;

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public List<Reimbursement> getAll() {
        return null;
    }

    //todo just call the save function above instead of using this function
    public void update(Reimbursement updateThisReimbursement) {

        //Reimbursement temp = getById(updateThisReimbursement.getId());
        //temp.setStatus_id(updateThisReimbursement.getStatus_id());

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ers_reimbursments set status_id = ?, resolved = ? WHERE reimb_id = ?");
            pstmt.setString(1, updateThisReimbursement.getStatus_id());
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(3, updateThisReimbursement.getId());

            System.out.println("UPDATE command sent to database");
            /*pstmt.setString(1, newReimbursement.getId());
            pstmt.setInt(2, newReimbursement.getAmount());
            pstmt.setTimestamp(3, newReimbursement.getSubmitted());
            pstmt.setTimestamp(4, newReimbursement.getResolved());
            pstmt.setString(5, newReimbursement.getDescription());
            pstmt.setString(6, newReimbursement.getPayment_id());
            pstmt.setString(7, newReimbursement.getAuthor_id());//.getId());
            //if(newReimbursement.getResolver() != null)
            pstmt.setString(8, newReimbursement.getResolver_id());//.getId());
            //else
            //pstmt.setString(8, null);
            pstmt.setString(9, newReimbursement.getStatus_id());//getStatus().getId());
            pstmt.setString(10, newReimbursement.getType_id());//getType().getId());

            System.out.println(pstmt);*/

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("pstmt.executeUpdate() ran in ReimbursementDao.java?");
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist reimbursement to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        //return temp;
    }

    public void deleteById(String id) {

    }
}
