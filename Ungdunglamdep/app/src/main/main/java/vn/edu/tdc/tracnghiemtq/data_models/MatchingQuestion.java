package vn.edu.tdc.tracnghiemtq.data_models;

import java.util.ArrayList;

/**
 * Created by IT on 9/27/2018.
 */

public class MatchingQuestion extends AbstractQuestion {
    protected ArrayList<String> questionChoicesA;
    protected ArrayList<String> questionChoicesB;
    protected ArrayList<Integer> questionAnswer;

    public MatchingQuestion(){
        this.questionChoicesA = new ArrayList<String>();
        this.questionChoicesB = new ArrayList<String>();
        this.questionCorrect = new ArrayList<Integer>();

    }

    public void setQuestionChoicesA(String... questionChoicesA) {
        for(String item: questionChoicesA){
            this.questionChoicesA.add(item);
        }
    }

    public void setQuestionChoicesB(String... questionChoicesB) {
        for(String item: questionChoicesB){
            this.questionChoicesB.add(item);
        }
    }

    public ArrayList<String> getQuestionChoicesA() {
        return questionChoicesA;
    }

    public ArrayList<String> getQuestionChoicesB() {
        return questionChoicesB;
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
