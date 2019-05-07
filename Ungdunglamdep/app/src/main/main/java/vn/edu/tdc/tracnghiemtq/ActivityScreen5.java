package vn.edu.tdc.tracnghiemtq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import vn.edu.tdc.tracnghiemtq.data_models.Questions;
import vn.edu.tdc.tracnghiemtq.data_models.TrueFalseQuestion;

public class ActivityScreen5 extends AppCompatActivity {
Intent intent;
Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen5_layout);
        //
        Questions.initialization();
        //
        Button btnPre = (Button) findViewById(R.id.btnPre);
        Button btnKetThuc = (Button) findViewById(R.id.btnKetThuc);
        Button btnNext  = (Button) findViewById(R.id.btnNext);
        TextView lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        EditText edtA= (EditText) findViewById(R.id.edtA);
        EditText edtB= (EditText) findViewById(R.id.edtB);
        EditText edtC= (EditText) findViewById(R.id.edtC);
        Switch swtA =(Switch) findViewById(R.id.swtA);
        Switch swtB =(Switch) findViewById(R.id.swtB);
        Switch swtC =(Switch) findViewById(R.id.swtC);
        //
        TrueFalseQuestion question5= (TrueFalseQuestion) Questions.questions.get(4);
        lblQuestion.setText(question5.getQuestionDescription());
        edtA.setText(question5.getQuestionChoices().get(0));
        edtB.setText(question5.getQuestionChoices().get(1));
        edtC.setText(question5.getQuestionChoices().get(2));

        intent=new Intent();
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(ActivityScreen5.this,ActivityScreen4.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen5.this,ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen5.this,ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
    }

    private void processUserActivities(Switch swichA, Switch swichB, Switch swichC) {
        Questions.questions.get(1).getQuestionAnswer().clear();

        if (swichA.isChecked()) {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        } else {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }

        if (swichB.isChecked()) {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        } else {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }

        if (swichC.isChecked()) {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        } else {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }
    }
}
