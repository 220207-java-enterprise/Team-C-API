package com.revature.erm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=foundations-project";
        String username = "postgres";
        String password = "revature";

        try {
            Connection connection = DriverManager.getConnection(URL, username, password);

            System.out.println("Connection successful");

            Scanner scanner = new Scanner(System.in);

            String input;
            do {
                System.out.println("What would you like to do?");
                System.out.print("Login: 1\nRegister: 2\nQuit: q\nEnter your choice here -->");
                input = scanner.next();
                switch(input){
                    case "1":
                        System.out.println("login");
                        break;
                    case "2":
                        System.out.println("register");
                        break;
                    case "q":
                        System.out.println("quit");
                        break;
                    default:
                        System.out.println("You entered " + "'" + input + "'" + " which is not valid\nPlease enter a valid option\n");
                }
            } while (!input.equals("q"));

            System.out.println("done here");

        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL");
            e.printStackTrace();
        }


        /*System.out.println("this is my project");
        User testUser = new User("iambob", "bobsemail@gmail.com",
                "asdf1234", "bill", "samolian");*/
    }
}