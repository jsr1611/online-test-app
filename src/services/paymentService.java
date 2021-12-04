package services;

import enums.PaymentType;
import models.PaymentMethod;

public interface paymentService {
    PaymentMethod addPaymentMethod();

    boolean editPaymentMethod(Long id);

    boolean deletePaymentMethod(Long id);

    boolean refillBalance(Long id, Double amount);
}
