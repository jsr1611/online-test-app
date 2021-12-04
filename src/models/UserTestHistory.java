package models;

import java.time.LocalDate;

public class UserTestHistory {
    private Long id;
    private String subject;
    private Test test;
    private LocalDate date;

    public UserTestHistory(Long id, String subject, Test test, LocalDate date) {
        this.id = id;
        this.subject = subject;
        this.test = test;
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
}
