package sr.unasat.movies.services;

import sr.unasat.movies.dao.UsersDao;
import sr.unasat.movies.services.applicationService.REG_Verification;
import sr.unasat.movies.services.applicationService.application;

import java.util.Scanner;

public class ServiceImpl {
    public void start() {
        Scanner input = new Scanner(System.in);
        REG_Verification regVerification = new REG_Verification();
        application application = new application();
        boolean exit = false;
        System.out.println("-------------------------------------------Movie Space------------------------------------------\n");
        System.out.println("Welcome to Movie Space \n");
        while(!exit){
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. List Movies");
            System.out.println("0. Exit");

            System.out.println("Please enter your choice: ");

            int choice = input.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Register");
                    regVerification.registerUser();
                    break;
                case 2:
                    System.out.println("Login");
                    regVerification.loginUser();
                    break;
                case 3:
                    System.out.println("List Movies");
                    application.displayAllMovies(0);
                    break;
                case 0:
                    System.out.println("Exit");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
