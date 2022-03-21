package com.revature.erm.util;

import com.revature.erm.models.User;
import com.revature.erm.repos.ReimbursementRepos;
import com.revature.erm.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Profile("local")
@Component
public class DummyDataInserter implements CommandLineRunner {

    private final UserRepos userRepo;
    private final ReimbursementRepos reimbursementRepo;

    @Autowired
    public DummyDataInserter(UserRepos userRepo, ReimbursementRepos reimbursementRepo) {
        this.userRepo = userRepo;
        this.reimbursementRepo = reimbursementRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Write persistence logic here for dummy data
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setFirstName("Tester");
        user1.setLastName("McTesterson");
        user1.setUsername("tester12");
        user1.setPassword("p4$$word");
        user1.setEmail("tester@revature.com");
        user1.setActive(true);
        //user1.setRoleId();
        /*user1.setAddress(new Address("123", "Main Street", "Tampa", "Florida", "33647"));
        user1.setShoeSize(10.5);*/

        /*Reimbursement hikingReimbursement = new Reimbursement();
        hikingReimbursement.setId(UUID.randomUUID().toString());
        hikingReimbursement.setAmount(45.0);
        hikingReimbursement.setSubmitted(Timestamp.valueOf("yes"));
        hikingReimbursement.setResolved(10.5);
        hikingReimbursement.setDescription(79.99);
        hikingReimbursement.setPayment_id(String.valueOf(true));
        hikingReimbursement.setAuthor_id("leather");
        hikingReimbursement.setResolver_id("leather");
        hikingReimbursement.setStatus_id("leather");
        hikingReimbursement.setType_id("leather");
        hikingReimbursement.setOwner(user1);

        Boot hikingBoots2 = new Boot();
        hikingBoots2.setId(UUID.randomUUID().toString());
        hikingBoots2.setBrand("Timberland");
        hikingBoots2.setColor("brown");
        hikingBoots2.setSize(7);
        hikingBoots2.setPrice(79.99);
        hikingBoots2.setWaterproof(true);
        hikingBoots2.setMaterial("leather");
        hikingBoots2.setOwner(user1);*/

        /*user1.addReimbursementToReimbursementList(hikingReimbursement*//*, hikingBoots2*//*);
        userRepo.save(user1); // the Reimbursement purchased by the Users are automatically persisted*/

    }

}
