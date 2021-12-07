package models;

import java.util.List;

public class Subject {
    private Long id;
    private String name;
    private List<Test> testList;
    private Integer totalPoints; // scores

    public Subject(Long id, String name, List<Test> testList, Integer totalPoints) {
        this.id = id;
        this.name = name;
        this.testList = testList;
        this.totalPoints = totalPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return String.format("%1$-5s", id) +
                String.format("%1$-15s", name) +
                String.format("%1$-15s", testList.size()) +
                String.format("%1$-15s", totalPoints);
    }
}
