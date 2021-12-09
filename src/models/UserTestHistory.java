package models;

import java.time.LocalDate;

public class UserTestHistory {
    private Long id;
    private Long userId;
    private String subject;
    private Test test;
    private Integer score;
    private LocalDate date;


    public UserTestHistory(Long id, Long userId, String subject, Test test, Integer score, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.test = test;
        this.score = score;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("%1$-5s", id) +
                String.format("%1$-15s", subject)+
                String.format("%1$-15s", score) +
                String.format("%1$-15s",test.getTotalPoints()) +
                String.format("%1$-15s", date);
    }
}
