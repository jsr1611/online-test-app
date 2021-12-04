package models;

public class FillBalanceHistory {
    private Long id;
    private String date;
    private Double amount;
    private Double totalAmount;

    public FillBalanceHistory(Long id, String date, Double amount, Double totalAmount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.totalAmount = totalAmount;
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
}
