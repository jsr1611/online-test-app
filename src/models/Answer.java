package models;

public class Answer {
    private Long id;
    private String answer;
    private Boolean correct;

    public Answer(Long id, String answer, Boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public void setAsCorrect(Boolean correct) {
        this.correct = correct;
    }
}
