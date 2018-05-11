package com.androidcai.administrator.piggymath;

public class Question {
    private int intQuestion;
    public interface OnQuestionChageListener {
        void onQuestionChangeListener(Question question);
    }
private OnQuestionChageListener onQuestionChageListener;
    public  void setOnQuestionChageListener(OnQuestionChageListener onQuestionChageListener) {
        this.onQuestionChageListener = onQuestionChageListener;
    if(this.onQuestionChageListener != null) {
        this.onQuestionChageListener.onQuestionChangeListener(this);
      }
    }

    public int getIntQuestion() {
        return intQuestion;
    } //end getter
    public void setIntQuestion(int intQuestion){
        this.intQuestion = intQuestion;
    } //end int
}
