package com.doctork.doctorkonlinecounseling.domain.SpecificModels;

public class FAQ {

    private String groupName ;
    private String question ;
    private String answer ;

    private Integer orderNumber;

    public FAQ(String groupName, String question, String answer, Integer orderNumber) {
        this.groupName = groupName;
        this.question = question;
        this.answer = answer;
        this.orderNumber = orderNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
