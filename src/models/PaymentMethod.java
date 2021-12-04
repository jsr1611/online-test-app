package models;

import enums.PaymentType;

import java.time.LocalDate;

public class PaymentMethod {
    private Long id;
    private PaymentType name;
    private Account account;
    private LocalDate dateAdded;

    public PaymentMethod(Long id, PaymentType name, Account account, LocalDate dateAdded) {
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

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
