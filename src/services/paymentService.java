package services;

import enums.PaymentType;
import models.PaymentMethod;
import models.User;

public interface paymentService {
    PaymentType addPaymentMethod();

    boolean editPaymentMethod(PaymentType method);

    boolean deletePaymentMethod(PaymentType method);

    boolean refillBalance(Long id, Double amount, User user);
}
