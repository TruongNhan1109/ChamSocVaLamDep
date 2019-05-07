package vn.edu.tdc.tracnghiemtq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import vn.edu.tdc.tracnghiemtq.data_models.MultiQuestionMultiChoices;
import vn.edu.tdc.tracnghiemtq.data_models.MultiQuestionOneChoices;
import vn.edu.tdc.tracnghiemtq.data_models.Questions;
import vn.edu.tdc.tracnghiemtq.view_models.MyRadiButtonGrop;

public class ActivityScreen2 extends AppCompatActivity {
Intent intent;
Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2_layout);

        Questions.initialization();
        //
        Button btnPre = (Button) findViewById(R.id.btnPre);
        Button btnKetThuc = (Button) findViewById(R.id.btnKetThuc);
        Button btnNext  = (Button) findViewById(R.id.btnNext);
        TextView lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        final RadioButton radA= (RadioButton) findViewById(R.id.radA);
        final RadioButton radB= (RadioButton) findViewById(R.id.radB);
        final RadioButton radC= (RadioButton) findViewById(R.id.radC);
        final RadioButton radD= (RadioButton) findViewById(R.id.radD);

        //
        MultiQuestionOneChoices question2= (MultiQuestionOneChoices) Questions.questions.get(1);
        lblQuestion.setText(question2.getQuestionDescription());
        radA.setText(question2.getQuestionChoices().get(0));
        radB.setText(question2.getQuestionChoices().get(1));
        radC.setText(question2.getQuestionChoices().get(2));
        radD.setText(question2.getQuestionChoices().get(3));
        MyRadiButtonGrop myRadiButtonGrop = new MyRadiButtonGrop(radA, radB, radC, radD);

        intent=new Intent();
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(ActivityScreen2.this,ActivityScreen1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen2.this,ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen2.this,ActivityScreen3.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(radA, radB, radC, radD);
                startActivity(intent);
            }
        });
    }

    private void processUserActivities(RadioButton radA, RadioButton radB, RadioButton radC, RadioButton radD)
    {
        Questions.questions.get(1).getQuestionAnswer().clear();

        if(radA.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(0);
        }

        if(radB.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(1);
        }

        if(radC.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(2);
        }

        if(radD.isChecked())
        {
            Questions.questions.get(1).getQuestionAnswer().add(3);
        }

        //Log.d("Text", Questions.questions.get(1).getQuestionAnswer().toString());
        //Log.d("Text", Questions.questions.get(1).getPoint()+"");
    }
}
