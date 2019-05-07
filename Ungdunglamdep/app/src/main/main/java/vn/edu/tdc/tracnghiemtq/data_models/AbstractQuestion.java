package vn.edu.tdc.tracnghiemtq.data_models;

import java.util.ArrayList;

/**
 * Created by IT on 9/27/2018.
 */

public abstract class AbstractQuestion {

    protected String questionDescription;
    protected ArrayList<Integer> questionCorrect;
    protected ArrayList<Integer> questionAnswer;
    public abstract int getPoint ();

    public void setQuestionAnswer(Integer... questionAnswer) {
        for(Integer item: questionAnswer){
            this.questionAnswer.add(item);
        }
    }

    public ArrayList<Integer> getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public void setQuestionCorrect(Integer... questionCorrect) {
       for(Integer item: questionCorrect){
           this.questionCorrect.add(item);
       }
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public ArrayList<Integer> getQuestionCorrect() {
        return questionCorrect;
    }

}
