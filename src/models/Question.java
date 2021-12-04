package models;

public class Question {
    private Long id;
    private String question;
    private String A;
    private String B;
    private String C;
    private Answer answer;
    private Integer point; // mark

    public Question(Long id, String question, String a, String b, String c, Answer answer, Integer point) {
        this.id = id;
        this.question = question;
        A = a;
        B = b;
        C = c;
        this.answer = answer;
        this.point = point;
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

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
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
}
