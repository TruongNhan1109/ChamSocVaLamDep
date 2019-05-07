package vn.edu.tdc.tracnghiemtq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import vn.edu.tdc.tracnghiemtq.data_models.MultiQuestionMultiChoices;
import vn.edu.tdc.tracnghiemtq.data_models.Questions;

public class ActivityScreen1 extends AppCompatActivity {
Intent intent;
Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1_layout);
        setTitle("Câu số 1");
        //
        Questions.initialization();
        //
        Button btnPre = (Button) findViewById(R.id.btnPre);
        Button btnKetThuc = (Button) findViewById(R.id.btnKetThuc);
        Button btnNext  = (Button) findViewById(R.id.btnNext);
        TextView lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        final CheckBox chkA= (CheckBox) findViewById(R.id.chkA);
        final CheckBox chkB= (CheckBox) findViewById(R.id.chkB);
        final CheckBox chkC= (CheckBox) findViewById(R.id.chkC);
        final CheckBox chkD= (CheckBox) findViewById(R.id.chkD);

        //
        MultiQuestionMultiChoices question1= (MultiQuestionMultiChoices) Questions.questions.get(0);
        lblQuestion.setText(question1.getQuestionDescription());
        chkA.setText(question1.getQuestionChoices().get(0));
        chkB.setText(question1.getQuestionChoices().get(1));
        chkC.setText(question1.getQuestionChoices().get(2));
        chkD.setText(question1.getQuestionChoices().get(3));

        intent=new Intent();
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(ActivityScreen1.this,ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(chkA, chkB, chkC, chkD);
                startActivity(intent);
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen1.this,ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(chkA, chkB, chkC, chkD);
                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen1.this,ActivityScreen2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(chkA, chkB, chkC, chkD);
                startActivity(intent);
            }
        });

    }

    private void processUserActivities(CheckBox chkA, CheckBox chkB, CheckBox chkC, CheckBox chkD)
    {
        Questions.questions.get(0).getQuestionAnswer().clear();

        if(chkA.isChecked())
        {
            Questions.questions.get(0).getQuestionAnswer().add(0);
        }

        if(chkB.isChecked())
        {
            Questions.questions.get(0).getQuestionAnswer().add(1);
        }

        if(chkC.isChecked())
        {
            Questions.questions.get(0).getQuestionAnswer().add(2);
        }

        if(chkD.isChecked())
        {
            Questions.questions.get(0).getQuestionAnswer().add(3);
        }

        //Log.d("Text", Questions.questions.get(0).getQuestionAnswer().toString());
        //Log.d("Text", Questions.questions.get(0).getPoint()+"");
    }
}
