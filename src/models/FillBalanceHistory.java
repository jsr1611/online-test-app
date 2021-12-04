package models;

import enums.PaymentType;

public class FillBalanceHistory {
    private Long id;
    private String date;
    private Double amount;
    private Double totalAmount;
    private PaymentType paymentType;

    public FillBalanceHistory(Long id, String date, Double amount, Double totalAmount, PaymentType paymentMethod) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.paymentType = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentType getPaymentMethod() {
        return paymentType;
    }

    public void setPaymentMethod(PaymentType paymentMethod) {
        this.paymentType = paymentMethod;
    }
}
