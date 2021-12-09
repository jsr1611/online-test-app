package realization;

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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

        Account account2 = new Account(
                2L,
                1002_111_223344L,
                1611,
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
        User user1 = new User(101L,
                "Jumanazar",
                "Saidov",
                "js@gmail.com",
                "1611",
                Role.APPLICANT,
                account2
                );
        users = new ArrayList<>();
        Map<PaymentType, Boolean> methods = new LinkedHashMap<>();
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
        users.add(user1);
        adminPaymentMap.put(admin1, paymentMethod);



        String filesFolderPath = "resources/";
        readFiles(filesFolderPath);


        while (true){
            try {
                mainMenu();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private static void readFiles(String filesFolderPath) {
        List<String> paths = new ArrayList<>();
        // add tests from file



        try {

            // read files' paths in the given folder
            Files.walkFileTree(Paths.get("resources/"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    paths.add(file.toString());
                    System.out.println("file: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


        BufferedReader fileReader = null;
        scanner = new Scanner(System.in);
        String check = "";
        try {
        }catch (Exception ignored){}
        finally {
            List<Test> testRead = null;
            List<Question> questionsRead = null;
            int totalPoints = 0;
            for (String path : paths) {
                testRead = new ArrayList<>();
                questionsRead = new ArrayList<>();
                Subject subject = new Subject(subjects.size()+1L, "", null, 0, 10000.0);
                System.out.println(path);
                try {
                    fileReader = new BufferedReader(new FileReader(path));
                } catch (Exception ignored) {
                }
                try {
                    String subjectName = path.substring(path.lastIndexOf("/") + 1, path.indexOf(".")).trim();
                    if (subjectName.isEmpty()) {
                        System.out.print("Enter the subject: ");
                        subjectName = scanner.nextLine();
                    }
                    subject.setName(subjectName);
                    String line = fileReader.readLine();
                    Long numTests = subject.getTestList() == null ? 1L : (subject.getTestList().size()+1L);
                    Test test01 = new Test(numTests, null, 0, 0);
                    int testCounter = 1;
                    Long numQuestions = test01.getQuestions() == null? 1L : (test01.getQuestions().size()+1L);

                    Question question01 = new Question(numQuestions, "", null, 0);
                    Answer currAnswer = new Answer(1L, null, false);
                    Set<Answer> answer_list = new LinkedHashSet<>();
                    String questionStr = "", answerStr = "";
                    Long questionId = 1L, answerId = 1L;
                    int idIndex = -1, score = 0, scoreForTest = 0;
                    while (line != null) {
                        //System.out.println(line);
                        if (line.startsWith("Test")) {
                            idIndex = line.indexOf(" ") + 1;
                            Long id = Long.parseLong(line.substring(idIndex).trim());
                            if (testCounter < id) {
                                test01.setTotalPoints(scoreForTest);
                                test01.setQuestions(questionsRead);
                                test01.setUserPoints(0);
                                testRead.add(test01);
                                test01 = new Test(testRead.size() + 1L, null, 0, 0);
                                testCounter++;
                            }
                            test01.setId(id);
                        }
                        if (line.startsWith("Question")) {
                            idIndex = line.indexOf(" ");
                            questionId = Long.parseLong(line.substring(idIndex + 1, idIndex + 4));
                            questionStr = line.substring(line.indexOf(".") + 1, line.indexOf("[")).trim();
                            question01.setQuestionStr(questionStr);
                            question01.setId(questionId);
                            score = Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
                            question01.setScore(score);
                            scoreForTest += score;
                            totalPoints += score;
                        } else if (line.startsWith("Answer")) {
                            idIndex = line.indexOf(":");
                            answerId = Long.parseLong(line.substring(idIndex - 1, idIndex));
                            answerStr = line.substring(idIndex + 1).trim();
                            currAnswer.setId(answerId);
                            currAnswer.setAnswerStr(answerStr);

                        } else if (line.length() == 1) {
                            if (line.equals("y")) {
                                currAnswer.setAsCorrect(true);
                            } else {
                                currAnswer.setAsCorrect(false);
                            }
                            answer_list.add(currAnswer);
                            currAnswer = new Answer(answer_list.size() + 1L, "", false);
                            if (answer_list.size() == 3) {
                                question01.setAnswers(answer_list);
                                questionsRead.add(question01);
                                answer_list = new LinkedHashSet<>();
                                question01 = new Question(questionsRead.size() + 1L, null, null, null);
                            }
                        }

                        line = fileReader.readLine();

                    }
                    test01.setTotalPoints(scoreForTest);
                    test01.setQuestions(questionsRead);
                    test01.setUserPoints(0);

                    testRead.add(test01);
                    subject.setTestList(testRead);
                    subject.setTotalPoints(totalPoints);
                    subjects.add(subject);
                    System.out.println(testRead.size() + " test(s) in " + subjectName + " added");
                    fileReader.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
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
                String.format("%1$-15s", "Price"));
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
                            switch (innerChoice){
                                case 1:
                                    paymentService.editPaymentMethod(PaymentType.CLICK);
                                    break;
                                case 2:
                                    paymentService.editPaymentMethod(PaymentType.CASH);
                                    break;
                                case 3:
                                    paymentService.editPaymentMethod(PaymentType.PayMe);
                                    break;
                            }
                        } else {
                            switch (innerChoice){
                                case 1:
                                    paymentService.deletePaymentMethod(PaymentType.CLICK);
                                    break;
                                case 2:
                                    paymentService.deletePaymentMethod(PaymentType.CASH);
                                    break;
                                case 3:
                                    paymentService.deletePaymentMethod(PaymentType.PayMe);
                                    break;
                            }
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
            System.out.println("4. Update price");
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
                    case 4:
                        if (subjects.size() == 0) {
                            System.out.println("No subjects available yet!");
                            break;
                        }
                        displaySubjects();
                        System.out.print("Subject id: ");
                        double innerChoice = scanner.nextInt();
                        if (choice == 2) {
                            subjectService.updateSubject((long) innerChoice);
                        } else if(choice == 3) {
                            subjectService.deleteSubject((long) innerChoice);
                        }
                        else {
                            subjectService.updatePrice((long)innerChoice);
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
            Subject subject = null;
            UserTestHistory testHistory = new UserTestHistory(
                    userTestHistories.size()+1L
                    , applicant.getId()
                    , null
                    , null
                    ,0
                    ,LocalDate.now()
                    );
            boolean exitTest = false;
            try {
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        if(subjects.size() == 0){
                            System.out.println("No subjects yet!");
                            break;
                        }
                        while (!exitTest) {
                            displaySubjects();
                            subject = selectSubject();
                            if (subject != null) {
                                List<Test> tests = subject.getTestList();
                                if (tests.size() > 0) {
                                    // take test
                                    System.out.println("Start the test? Press Enter key to conitnue.");
                                    try {
                                        scanner.next();
                                    }catch (Exception ignored){}
                                    finally {
                                        testHistory.setSubject(subject.getName());
                                        int totalForAllTests = 0;
                                        for (Test test : tests) {
                                            if(testHistory != null){
                                                testHistory.setTest(test);
                                            }
                                            boolean quitTest = false;
                                            int totalForTest = 0;
                                            for (Question question : test.getQuestions()) {
                                                if(quitTest){
                                                    break;
                                                }
                                                int scoreForTest = 0;
                                                boolean notAnswered = true;
                                                while (notAnswered && !quitTest) {
                                                    question.printQuestion();
                                                    System.out.println("To finish the test, enter 0");
                                                    System.out.print("Answer: ");
                                                    scanner = new Scanner(System.in);
                                                    int answer = scanner.nextInt();
                                                    if(answer >0 && answer <= 3){
                                                        if(question.getCorrectAnswer().equals(question.getAnswer(answer))) {
                                                            scoreForTest = question.getScore();
                                                        }
                                                        System.out.println("Good! Next question...");
                                                        notAnswered = false;
                                                        totalForTest += scoreForTest;
                                                    }
                                                    else if(answer == 0){
                                                        System.out.println("Finishing the test.");
                                                        System.out.println("Total score for the test: " + totalForTest + " out of " + test.getTotalPoints());
                                                        test.setTotalPoints(totalForTest);
                                                        quitTest = true;
                                                        testHistory.setScore(totalForTest);
                                                    }
                                                    else {
                                                        System.out.println("Wrong answer id! Please, enter correct answer id!");
                                                    }
                                                }
                                                if(quitTest){
                                                    break;
                                                }
                                            }
                                            totalForAllTests += totalForTest;
                                            System.out.println("Finishing the test.");
                                            System.out.println("Total score for the test: " + totalForTest + " out of " + test.getTotalPoints());
                                            test.setTotalPoints(totalForAllTests);
                                            System.out.println("Do you want to continue to take another test? Enter 'y' to continue.");
                                            System.out.print("User input: ");
                                            testHistory.setScore(totalForTest);
                                            userTestHistories.add(testHistory);
                                            scanner = new Scanner(System.in);
                                            String userResponse = scanner.next();
                                            if(!userResponse.equals("y")){
                                                break;
                                            }
                                            testHistory = new UserTestHistory(
                                                    userTestHistories.size()+1L,
                                                    applicant.getId(),
                                                    subject.getName(),
                                                    null,
                                                    0,
                                                    LocalDate.now());
                                        }
                                    }
                                } else {
                                    System.out.println("No tests are available for " + subject.getName());
                                    exitTest = true;
                                }
                            }
                            else {
                                exitTest = true;
                            }
                        }
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
                        fillBalanceHistoryHeader();
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


    private static Subject selectSubject() {
        scanner = new Scanner(System.in);
        // TODO: 12/7/2021 Add logic for select subject and return the selected subject id
        int counter = 3;
        while (counter-- > 0) {
            System.out.print("Subject id: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                for (Subject subject : subjects) {
                    if (subject.getId().equals((long) choice)) {
                        return subject;
                    }
                }
                System.out.println("Wrong subject id! Please, try again!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong input! Please, enter only subject id!");
            }
        }
        return null;
    }

    private static void TestResults() {
        System.out.println();
        System.out.println("Test Results Of " + currentUser.getFirstName());
        System.out.println();
        userHistoryHeader();
        for (UserTestHistory userTestHistory : userTestHistories) {
            if(userTestHistory.getUserId().equals(currentUser.getId())){
                System.out.println(userTestHistory);
            }
        }

    }

    /**
     * Print this before printing the userTestHistory objects!
     * Print the first line that introduces the items of userTestHistory object
     */
    private static void userHistoryHeader() {
        System.out.println(String.format("%1$-5s", "Id") +
                String.format("%1$-15s", "Subject")+
                String.format("%1$-15s", "Score") +
                String.format("%1$-15s", "Total") +
                String.format("%1$-15s", "Date"));
    }



    /**
     * Print this before printing the fillBalanceHistory objects!
     * Print the names of fillBalanceHistory objects as the first line
     */
    private static void fillBalanceHistoryHeader() {
        System.out.println(String.format("%1$-5s", "Id") +
                String.format("%1$-15s","Date") +
                String.format("%1$-15s","Amount") +
                String.format("%1$-15s","Total Balance") +
                String.format("%1$-10s","Payment Type") +
                String.format("%1$-25s","Name (Role)"));
    }

}
