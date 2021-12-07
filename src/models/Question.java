package models;

import java.util.Set;

public class Question {
    private Long id;
    private String questionStr;
    private Set<Answer> answers;
    private Integer score; // mark

    public Question(Long id, String question, Set<Answer> answers, Integer point) {
        this.id = id;
        this.questionStr = question;
        this.answers = answers;
        this.score = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
    public Answer getCorrectAnswer(){
        for (Answer answer : answers) {
            if(answer.isCorrect()){
                return answer;
            }
        }
        return null;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder answersStr = new StringBuilder();
        for (Answer answer : answers) {
            answersStr.append(String.format("%1$-20s", answer));
        }
        return String.format("%1$-5s", id) +
                String.format("%1$-20s", questionStr) +
                answersStr +
                String.format("%1$-20s", getCorrectAnswer()) +
                String.format("%1$-10s", score);
    }

    public void printQuestion() {
        StringBuilder questionStr = new StringBuilder();
         questionStr.append("Question ").append(id).append(". ").append(this.questionStr).append(" (").append(score).append(" points)\n");
        for (Answer answer : answers) {
            questionStr.append(answer.getId()).append(". ").append(answer.getAnswerStr()).append("\n");
        }
        System.out.println(questionStr);
    }

    public Answer getAnswer(int answerId) {
        for (Answer answer1 : answers) {
            if(answer1.getId().equals((long)answerId)){
                return answer1;
            }
        }
        return null;
    }
}
