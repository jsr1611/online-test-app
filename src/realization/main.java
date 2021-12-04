package realization;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import enums.Role;
import models.User;
import services.serviceImpl.registrationImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static Scanner scanner;
    public static List<User> users;
    public static User currentUser;
    private static registrationImpl registrationService;
    public static void main(String[] args) {
        System.out.println("Online Test");
        users = new ArrayList<>();
        mainMenu();
    }

    private static void mainMenu() {

        while (currentUser == null || !currentUser.getSignedIn()){
            System.out.println("1. SignUp");
            System.out.println("2. SignIp");
            System.out.println("3. Find username/password");
            scanner = new Scanner(System.in);
            int choice = -1;
            boolean isSuccess = false;
            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        registrationService.signUp();
                        break;
                    case 2:
                        isSuccess = registrationService.signIn();
                        break;
                    default:
                        System.out.println("Not Implemented Yet!");
                }
                currentUser.setSignedIn(isSuccess);
            }catch (Exception e){
                e.printStackTrace();
        }
            if(currentUser.getSignedIn()){
                if(currentUser.getRole().equals(Role.ADMIN)){
                    adminMenu();
                }
                else if(currentUser.getRole().equals(Role.APPLICANT)){
                    applicantMenu();
                }
            }
        }
    }



    private static void adminMenu(){
        // TODO: 12/4/21 admin panel

    }

    private static void applicantMenu(){
        // TODO: 12/4/21 applicant panel
    }

}
