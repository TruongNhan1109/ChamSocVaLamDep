package vn.edu.tdc.tracnghiemtq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import vn.edu.tdc.tracnghiemtq.data_models.MatchingQuestion;
import vn.edu.tdc.tracnghiemtq.data_models.Questions;
import vn.edu.tdc.tracnghiemtq.data_models.TrueFalseQuestion;

public class ActivityScreen4 extends AppCompatActivity {
Intent intent;
Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4_layout);
        //
        Questions.initialization();
        //
        intent=new Intent();
        Button btnPre = (Button) findViewById(R.id.btnPre);
        Button btnKetThuc = (Button) findViewById(R.id.btnKetThuc);
        Button btnNext  = (Button) findViewById(R.id.btnNext);
        TextView lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        EditText edtA= (EditText) findViewById(R.id.edtA);
        EditText edtB= (EditText) findViewById(R.id.edtB);
        EditText edtC= (EditText) findViewById(R.id.edtC);
        final ToggleButton tBtnA =(ToggleButton) findViewById(R.id.tBtnA);
        final ToggleButton tBtnB =(ToggleButton) findViewById(R.id.tBtnB);
        final ToggleButton tBtnC =(ToggleButton) findViewById(R.id.tBtnC);
        //
        TrueFalseQuestion question4= (TrueFalseQuestion) Questions.questions.get(3);
        lblQuestion.setText(question4.getQuestionDescription());
        edtA.setText(question4.getQuestionChoices().get(0));
        edtB.setText(question4.getQuestionChoices().get(1));
        edtC.setText(question4.getQuestionChoices().get(2));


        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(ActivityScreen4.this,ActivityScreen3.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(tBtnA, tBtnB, tBtnC);
                startActivity(intent);
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen4.this,ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(tBtnA, tBtnB, tBtnC);
                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen4.this,ActivityScreen5.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(tBtnA, tBtnB, tBtnC);
                startActivity(intent);
            }
        });
    }
    private void processUserActivities(ToggleButton tBtnA, ToggleButton tBtnB, ToggleButton tBtnC)
    {
        Questions.questions.get(1).getQuestionAnswer().clear();

        if(tBtnA.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        }
        else
        {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }

        if(tBtnB.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        }
        else
        {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }

        if(tBtnC.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        }
        else
        {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }

        //Log.d("Text", Questions.questions.get(1).getQuestionAnswer().toString());
        //Log.d("Text", Questions.questions.get(1).getPoint()+"");
    }
}
