package models;

import java.util.List;

public class Account {
    private Long id;
    private Integer accountNumber;
    private Integer password;
    private Double balance;
    private User user;
    private boolean isActive;

    public Account(Long id, Integer accountNumber, Integer password, Double balance, User user, boolean isActive) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.user = user;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
