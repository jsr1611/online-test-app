package realization;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import enums.Role;
import models.*;
import services.serviceImpl.registrationImpl;
import services.serviceImpl.subjectServiceImpl;
import services.serviceImpl.testServiceImpl;
import services.subjectService;
import services.testService;

import java.util.*;

public class main {
    private static Scanner scanner;
    public static List<User> users;
    public static User currentUser;
    public static List<Subject> subjects = new ArrayList<>();
    public static List<FillBalanceHistory> balanceHistories = new ArrayList<>();
    public static List<UserTestHistory> userTestHistories = new ArrayList<>();
    public static Map<User, PaymentMethod> paymentMethods = new HashMap<>();

    public static final registrationImpl registrationService = new registrationImpl();
    public static final subjectService subjectService = new subjectServiceImpl();
    public static final testService testService = new testServiceImpl();


    public static void main(String[] args) {
        System.out.println("Online Test");
        users = new ArrayList<>();
        while (true){
            mainMenu();
        }
    }

    private static void mainMenu() {

        while (currentUser == null || !currentUser.getSignedIn()) {
            System.out.println("1. SignUp");
            System.out.println("2. SignIn");
            System.out.println("3. Find username/password");
            System.out.print("Menu: ");
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
                        if (isSuccess)
                            currentUser.setSignedIn(true);
                        break;
                    default:
                        System.out.println("Not Implemented Yet!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(currentUser.getSignedIn()){
            if(currentUser.getRole().equals(Role.ADMIN)){
                adminMenu();
            }
            else if(currentUser.getRole().equals(Role.APPLICANT)){
                applicantMenu();
            }
            currentUser.setSignedIn(false);
            System.out.println("Bye, " + currentUser.getFirstName() + "!");
        }
    }



    private static void adminMenu(){
        // TODO: 12/4/21 admin panel
        System.out.println("Not implemented yet - admin");

    }

    private static void applicantMenu(){
        // TODO: 12/4/21 applicant panel
        System.out.println("Not implemented yet - applicant");
    }

}
