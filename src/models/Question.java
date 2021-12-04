package models;

public class Question {
    private Long id;
    private String question;
    private Answer A;
    private Answer B;
    private Answer C;
    private Answer answer;
    private Integer point; // mark

    public Question(Long id, String question, Answer a, Answer b, Answer c, Integer point) {
        this.id = id;
        this.question = question;
        A = a;
        B = b;
        C = c;
        this.point = point;
        if(A.isCorrect()){
            answer = A;
        }
        else if(B.isCorrect()){
            answer = B;
        }
        else {
            answer = C;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getA() {
        return A;
    }

    public void setA(Answer a) {
        A = a;
    }

    public Answer getB() {
        return B;
    }

    public void setB(Answer b) {
        B = b;
    }

    public Answer getC() {
        return C;
    }

    public void setC(Answer c) {
        C = c;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return String.format("%1$-5s", id) +
                String.format("%1$-20s",question) +
                String.format("%1$-20s", A) +
                String.format("%1$-20s", B) +
                String.format("%1$-20s", C) +
                String.format("%1$-20s", answer) +
                String.format("%1$-10s", point);
    }
}
