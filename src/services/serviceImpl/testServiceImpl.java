package services.serviceImpl;

import models.Answer;
import models.Question;
import models.Test;
import services.testService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testServiceImpl implements testService {
    private Long testID = 1L;
    private Scanner scanner;

    @Override
    public Test createTest() {

        boolean continueTestCreation = true;
        List<Question> questionList = new ArrayList<>();
        Answer answer1 = null;
        Answer answer2 = null;
        Answer answer3 = null;
        int qCounter = 1;
        int aCounter = 1;
        while (continueTestCreation) {
            scanner = new Scanner(System.in);
            System.out.println("Question " + qCounter++);
            try {
                answer1 = new Answer(1L, "", false);
                answer2 = new Answer(2L, "", false);
                answer3 = new Answer(3L, "", true);
                String questionText = "";
                System.out.println("Question text: ");
                questionText = scanner.nextLine();
                answer1 = addAnswer(answer1.getId());
                answer2 = addAnswer(answer2.getId());
                answer3 = addAnswer(answer3.getId());

                Question question = new Question(1L,
                        questionText,
                        answer1,
                        answer2,
                        answer3,
                        5);

                questionList.add(question);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.print("Add more question? 'y' to continue: ");
            String stopHere = scanner.next();
            if(!stopHere.equals("y")){
                continueTestCreation = false;
            }
        }
        Test test = new Test(testID,
                questionList,
                100 * questionList.size(), 0);

        return test;
    }

    private Answer addAnswer(Long id) {
        scanner = new Scanner(System.in);
        System.out.print("Answer " + id + ": ");
        String answer = scanner.nextLine();
        System.out.println("Is it correct answer? 'y' for yes, 'n' for no.");
        String isTrue = scanner.next();
        boolean isCorrect = isTrue.equals("y");
        return new Answer(id, answer, isCorrect);
    }

    @Override
    public boolean updateTest(Long id) {
        return false;
    }

    @Override
    public boolean deleteTest(Long id) {
        return false;
    }
}
