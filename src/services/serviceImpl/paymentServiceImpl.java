package services.serviceImpl;

import enums.PaymentType;
import models.Account;
import models.FillBalanceHistory;
import models.PaymentMethod;
import models.User;
import realization.main;
import services.paymentService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class paymentServiceImpl implements paymentService {
    private Scanner scanner;
    @Override
    public PaymentType addPaymentMethod() {
        PaymentType paymentType = null;
        while (true) {
            scanner = new Scanner(System.in);
            System.out.println("\nAdd Payment Method Menu");
            paymentType = null;
            int choice = -1;
            Double initialBalance = 0.0;
            try {
                System.out.print("\nChoose payment type: ");
                System.out.println("1. PayMe");
                System.out.println("2. CLICK");
                System.out.println("3. Cash");
                System.out.println("0. Cancel\n");
                System.out.print("Payment method: ");
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        paymentType = PaymentType.PayMe;
                        break;
                    case 2:
                        paymentType = PaymentType.CLICK;
                        break;
                    case 3:
                        paymentType = PaymentType.CASH;
                        break;
                }

                main.currentUser.getPaymentMethod().addMethod(paymentType);

            }catch (Exception e){
                e.printStackTrace();
            }

            if(paymentType != null){
                main.currentUser.getPaymentMethod().addMethod(paymentType);
                break;
            }
            else {
                System.out.println("Error in user input for payment method creation.");
            }

        }
        return paymentType;
    }

    @Override
    public boolean editPaymentMethod(PaymentType method) {
        scanner = new Scanner(System.in);
        System.out.println("\nEdit Payment Method Menu");
        PaymentType type = null, userSelectedMethod = null;
        PaymentMethod methodToEdit = null;

        int choice = -1;


        return false;
    }

    @Override
    public boolean deletePaymentMethod(PaymentType method) {
        for (Map.Entry<PaymentType, Boolean> methods : main.currentUser.getPaymentMethod().getMethods().entrySet()) {
            if(methods.getKey().equals(method)){
                return main.currentUser.getPaymentMethod().deactivate(method);
            }
        }
        return false;
    }

    @Override
    public boolean refillBalance(Long id, Double amount, User user) {
        double balance = 0;
        LocalDate date = LocalDate.now();

        for (User user1 : main.users) {
            if(user1.equals(user)){
                balance = user1.getAccount().refill(amount);
                System.out.println("Current balance: " + balance);
                FillBalanceHistory history = new FillBalanceHistory(main.balanceHistories.size()+1L,
                        date, amount, balance, null, main.currentUser);
                main.balanceHistories.add(history);
                return true;
            }
        }
        return false;
    }
}
