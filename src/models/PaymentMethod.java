package models;

import enums.PaymentType;

public class PaymentMethod {
    private Long id;
    private PaymentType name;
    private Account account;
    private String dateAdded;

    public PaymentMethod(Long id, PaymentType name, Account account, String dateAdded) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.dateAdded = dateAdded;
    }
}
