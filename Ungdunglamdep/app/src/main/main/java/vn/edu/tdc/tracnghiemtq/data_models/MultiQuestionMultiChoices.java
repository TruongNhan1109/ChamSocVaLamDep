package vn.edu.tdc.tracnghiemtq.data_models;

import java.util.ArrayList;

/**
 * Created by IT on 9/27/2018.
 */

public class MultiQuestionMultiChoices extends AbstractQuestion {
    protected ArrayList<String> questionChoices;

    public MultiQuestionMultiChoices(){
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
            int i;
            for(i = 0; i < this.questionCorrect.size(); i ++)
            {
                if(questionAnswer.get(i) != questionCorrect.get(i))
                {
                    break;
                }
            }
            if(i == questionCorrect.size())
            {
                poin = 1;
            }
        }
        return poin;
    }
}
