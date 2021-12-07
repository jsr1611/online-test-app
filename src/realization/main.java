package realization;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import enums.PaymentType;
import enums.Role;
import models.*;
import services.paymentService;
import services.serviceImpl.paymentServiceImpl;
import services.serviceImpl.registrationImpl;
import services.serviceImpl.subjectServiceImpl;
import services.serviceImpl.testServiceImpl;
import services.subjectService;
import services.testService;
import sun.management.counter.AbstractCounter;

import javax.swing.plaf.IconUIResource;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class main {
    private static Scanner scanner;
    public static List<User> users;
    public static User currentUser;
    public static List<Subject> subjects = new ArrayList<>();
    public static List<FillBalanceHistory> balanceHistories = new ArrayList<>();
    public static List<UserTestHistory> userTestHistories = new ArrayList<>();
    public static Map<User, PaymentMethod> adminPaymentMap = new HashMap<>();

    public static final registrationImpl registrationService = new registrationImpl();
    public static final subjectService subjectService = new subjectServiceImpl();
    public static final testService testService = new testServiceImpl();
    public static final paymentService paymentService = new paymentServiceImpl();


    public static void main(String[] args) {
        System.out.println("Online Test");
        Account account1 = new Account(
                1L,
                1002_200_362211L,
                1234,
                0.0,
                true);
        User admin1 = new User(
                100L,
                "Admin",
                "Super",
                "admin@success.edu",
                "root",
                Role.ADMIN,
                account1);
        users = new ArrayList<>();
        Map<PaymentType, Boolean> methods = new HashMap<>();
        methods.put(PaymentType.CLICK, true);
        methods.put(PaymentType.CASH, true);
        methods.put(PaymentType.PayMe, false);
        PaymentMethod paymentMethod = new PaymentMethod(
                1000L,
                methods,
                account1,
                LocalDate.now());
        admin1.setPaymentMethod(paymentMethod);
        users.add(admin1);
        adminPaymentMap.put(admin1, paymentMethod);

        while (true){
            try {
                mainMenu();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * MAIN MENU: Registration, SignIn
     */
    private static void mainMenu() {

        while (currentUser == null || !currentUser.getSignedIn()) {
            System.out.println("\n1. SignUp");
            System.out.println("2. SignIn");
            System.out.println("3. Find username/password\n");
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


    /**
     * Admin panel
     */
    private static void adminMenu(){
        // TODO: 12/4/21 admin panel
        scanner = new Scanner(System.in);
        int choice = -1;
        while (currentUser.getSignedIn()){
            System.out.println("\nADMIN PANEL");
            System.out.println("1. Display subjects");
            System.out.println("2. Subject Services");
            System.out.println("3. Payment Services");
            System.out.println("4. User Payments History");
            System.out.println("0. SignOut\n");
            System.out.print("Menu: ");
            try {
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        if(subjects.size() == 0){
                            System.out.println("No subjects yet!");
                            break;
                        }
                        displaySubjects();
                        break;
                    case 2:
                        showSubjectsMenu();
                        break;
                    case 3:
                        showPaymentsMenu();
                        break;
                    case 4:
                        System.out.println("User Balance Fill History");
                        for (FillBalanceHistory balanceHistory : main.balanceHistories) {
                            System.out.println(balanceHistory);
                        }
                    case 0:
                        currentUser.setSignedIn(false);
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Display subject id, title, number of tests, total points, and user points for all subjects
     */
    private static void displaySubjects() {
        System.out.println("All Subjects");
        System.out.println(String.format("%1$-5s", "Id") +
                String.format("%1$-15s", "Subject") +
                String.format("%1$-15s", "Tests") +
                String.format("%1$-15s", "Total Points") +
                String.format("%1$-15s", "User Points"));
        for (Subject subject : main.subjects) {
            System.out.println(subject);
        }
    }


    /**
     * Payment related services implementation
     */
    private static void showPaymentsMenu() {
        scanner = new Scanner(System.in);

        int choice = -1;
        boolean stayHere = true;
        while (stayHere) {
            System.out.println("\nPayment Service Menu");
            System.out.println("1. Add payment method");
            System.out.println("2. Edit payment method");
            System.out.println("3. Delete payment method");
            System.out.println("0. Return back\n");
            System.out.print("Menu: ");
            try {
                PaymentType userSelectedMethod = null;
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        paymentService.addPaymentMethod();
                        break;
                    case 2:
                    case 3:
                        if (currentUser.getPaymentMethod() == null || currentUser.getPaymentMethod().getMethods().size() == 0) {
                            System.out.println("No payment methods available yet!");
                            break;
                        }
                        System.out.println("All Payment Methods");
                        int counter = 1;
                        for (Map.Entry<PaymentType, Boolean> methods : currentUser.getPaymentMethod().getMethods().entrySet()) {
                            String active = methods.getValue() ? "OK":"X";
                            System.out.println(counter++ + ". " + methods.getKey() + " (" + active + ")");
                        }
                        System.out.print("Payment method: ");
                        int innerChoice = scanner.nextInt();
                        if (choice == 2) {
                            //paymentService.editPaymentMethod();
                        } else {
                            //paymentService.deletePaymentMethod();
                        }
                        break;
                    case 0:
                        stayHere = false;

                    default:
                        System.out.println("There was error in user input!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Subjects related services implementation
     */
    private static void showSubjectsMenu() {
        scanner = new Scanner(System.in);
        int choice = -1;
        boolean stayHere = true;
        while (stayHere) {
            System.out.println("\nSubject Service Menu");
            System.out.println("1. Add subject");
            System.out.println("2. Edit subject");
            System.out.println("3. Delete subject");
            System.out.println("0. Return back\n");
            System.out.print("Menu: ");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        subjectService.addSubject();
                        break;
                    case 2:
                    case 3:
                        if (subjects.size() == 0) {
                            System.out.println("No subjects available yet!");
                            break;
                        }
                        displaySubjects();
                        System.out.print("Subject id: ");
                        int innerChoice = scanner.nextInt();
                        if (choice == 2) {
                            subjectService.updateSubject((long) innerChoice);
                        } else {
                            subjectService.deleteSubject((long) innerChoice);
                        }
                        break;
                    case 0:
                        stayHere = false;
                    default:
                        System.out.println("There was error in user input!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Applicant panel
     */
    private static void applicantMenu(){
        User applicant = main.currentUser;
        // TODO: 12/4/21 applicant panel
        scanner = new Scanner(System.in);
        int choice = -1;
        while (applicant.getSignedIn()){
            System.out.println("\nAPPLICANT PANEL");
            System.out.println("1. Select subject");
            System.out.println("2. Test Results");
            System.out.println("3. Check Balance");
            System.out.println("4. User Payments History");
            System.out.println("0. SignOut\n");
            System.out.print("Menu: ");
            try {
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        if(subjects.size() == 0){
                            System.out.println("No subjects yet!");
                            break;
                        }
                        displaySubjects();
                        Long subjectId = selectSubject();
                        break;
                    case 2:
                        TestResults();
                        break;
                    case 3:
                        System.out.println("Your account balance: " + applicant.getAccount().getBalance() + "(" + applicant.getAccount().isActive()+ ")");
                        System.out.print("Do you want to refill your balance? 'y' for yes:");
                        if(!applicant.getAccount().isActive()){
                            System.out.println("Your account is blocked. Please, visit a bank to unblock it, and then try to refill again.");
                            break;
                        }
                        String userResponse = scanner.next();
                        if(userResponse.equals("y") || userResponse.equals("Y")){
                            System.out.print("Enter amount: ");
                            Double amountToRefill = scanner.nextDouble();
                            Double balance = applicant.getAccount().refill(amountToRefill);
                            FillBalanceHistory history = new FillBalanceHistory(
                                    balanceHistories.size()+1L,
                                    LocalDate.now(),
                                    amountToRefill,
                                    balance, null,
                                    applicant);
                            balanceHistories.add(history);
                        }

                        break;
                    case 4:
                        System.out.println("User Balance Fill History");
                        for (FillBalanceHistory balanceHistory : main.balanceHistories) {
                            System.out.println(balanceHistory);
                        }
                    case 0:
                        currentUser.setSignedIn(false);
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private static Long selectSubject() {
        scanner = new Scanner(System.in);
        displaySubjects();
        // TODO: 12/7/2021 Add logic for select subject and return the selected subject id
        while (true) {
            System.out.print("Subject id: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();

                for (Subject subject : subjects) {
                    if (subject.getId().equals((long) choice)) {
                        return subject.getId();
                    }
                }
                System.out.println("Wrong subject id! Please, try again!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong input! Please, enter only subject id!");
            }
        }
    }

    private static void TestResults() {
        // TODO: 12/6/21 write the logic
    }

}
