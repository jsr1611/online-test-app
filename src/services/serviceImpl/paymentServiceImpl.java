package services.serviceImpl;

import models.PaymentMethod;
import services.paymentService;

public class paymentServiceImpl implements paymentService {
    @Override
    public PaymentMethod addPaymentMethod() {
        return null;
    }

    @Override
    public boolean editPaymentMethod(Long id) {
        return false;
    }

    @Override
    public boolean deletePaymentMethod(Long id) {
        return false;
    }

    @Override
    public boolean refillBalance(Long id, Double amount) {
        return false;
    }
}
