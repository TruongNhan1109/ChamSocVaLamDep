package vn.edu.tdc.tracnghiemtq.data_models;

import java.util.ArrayList;

/**
 * Created by IT on 9/27/2018.
 */

public class MultiQuestionOneChoices extends AbstractQuestion {
    protected ArrayList<String> questionChoices;


    public MultiQuestionOneChoices(){
        this.questionChoices = new ArrayList<String>();
        this.questionCorrect = new ArrayList<Integer>();
        this.questionAnswer = new ArrayList<Integer>();

    }

    public void setQuestionChoices(String... questionChoices) {
        for(String item:questionChoices){
            this.questionChoices.add(item);
        }
    }

    public ArrayList<String> getQuestionChoices() {
        return questionChoices;
    }

    @Override
    public int getPoint() {
        int poin = 0;
        //
        if(this.questionCorrect.size() == this.questionAnswer.size())
        {
            // for
            if(questionAnswer.get(0) == questionCorrect.get(0))
            {
                poin = 1;
            }
        }
        return poin;
    }
}
