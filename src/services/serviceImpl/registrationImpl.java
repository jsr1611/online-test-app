package services.serviceImpl;

import enums.Role;
import javafx.animation.ScaleTransition;
import models.User;
import realization.main;
import services.registration;

import java.util.Scanner;

public class registrationImpl implements registration {
    private Scanner scanner;

    @Override
    public User findByEmail(String email) {
        for (User user : main.users) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean signUp() {
        scanner = new Scanner(System.in);

        String firstName = "", lastName = "", email = "", password = "";
        try {
            System.out.print("Enter your full name");
            String name = scanner.nextLine();
            if (name.contains(" ")) {
                firstName = name.substring(0, name.indexOf(" "));
                name = name.substring(name.indexOf(" "));
                lastName = name.trim();
            }
            boolean userExists = true;
            while (userExists) {
                System.out.print("Enter your email address: ");
                email = scanner.next();
                userExists = findByEmail(email) != null;
                System.out.println("This email has already been taken by some other user. Please use a different email address.");
            }

            System.out.print("Enter your password: ");
            password = scanner.next();
            System.out.println("Choose who you are: ");
            System.out.println("1. " + Role.APPLICANT);
            System.out.println("2. " + Role.ADMIN);
            String userType = scanner.next();
            Role chosenRole;
            if(userType.equals("1")){
                chosenRole = Role.APPLICANT;
            }
            else {
                chosenRole = Role.ADMIN;
            }
            User user = new User(
                    main.users.size()+1L,
                    firstName,
                    lastName,
                    email,
                    password,
                    chosenRole);
            main.users.add(user);
            System.out.println("===================================");
            System.out.println("User registration was successful!");
            System.out.println("===================================");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean signIn() {
        scanner = new Scanner(System.in);
        String email = "", password = "";
        int counter = 5;
        try {
            System.out.print("Enter your email address: ");
            email = scanner.next();

            System.out.print("Enter your password: ");
            password = scanner.next();

            User user = findByEmailAndPassword(email, password);
            if(user != null){
                System.out.println("Welcome, " + user.getFirstName() + "!");
                main.currentUser = user;
                return true;
            }else {
                if(findByEmail(email) == null){
                    System.out.println("User with the given email was not found in the database.");
                }
                else {
                    System.out.println("Please, check your password, and try again!");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;


    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        for (User user : main.users) {
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }
}
