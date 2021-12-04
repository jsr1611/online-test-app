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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentType getName() {
        return name;
    }

    public void setName(PaymentType name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
