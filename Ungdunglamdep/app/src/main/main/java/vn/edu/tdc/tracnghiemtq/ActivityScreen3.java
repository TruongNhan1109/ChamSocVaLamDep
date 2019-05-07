package vn.edu.tdc.tracnghiemtq;

import android.content.Intent;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import vn.edu.tdc.tracnghiemtq.data_models.MatchingQuestion;
import vn.edu.tdc.tracnghiemtq.data_models.Questions;

public class ActivityScreen3 extends AppCompatActivity {
Intent intent;
Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3_layout);

        Questions.initialization();
        //
        Button btnPre = (Button) findViewById(R.id.btnPre);
        Button btnKetThuc = (Button) findViewById(R.id.btnKetThuc);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        TextView lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        EditText edtA = (EditText) findViewById(R.id.edtA);
        EditText edtB = (EditText) findViewById(R.id.edtB);
        EditText edtC = (EditText) findViewById(R.id.edtC);
        final Spinner spinA = (Spinner) findViewById(R.id.spinA);
        final Spinner spinB = (Spinner) findViewById(R.id.spinB);
        final Spinner spinC = (Spinner) findViewById(R.id.spinC);

        //
        MatchingQuestion question3 = (MatchingQuestion) Questions.questions.get(2);
        lblQuestion.setText(question3.getQuestionDescription());
        edtA.setText(question3.getQuestionChoicesA().get(0));
        edtB.setText(question3.getQuestionChoicesA().get(1));
        edtC.setText(question3.getQuestionChoicesA().get(2));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, question3.getQuestionChoicesB());
        spinA.setAdapter(spinnerAdapter);
        spinB.setAdapter(spinnerAdapter);
        spinC.setAdapter(spinnerAdapter);

        intent = new Intent();
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(ActivityScreen3.this, ActivityScreen2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen3.this, ActivityScreenKetThuc.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreen3.this, ActivityScreen4.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(spinA, spinB, spinC);
                startActivity(intent);
            }
        });
    }
    private void processUserActivities(Spinner spinA, Spinner spinB, Spinner spinC)
    {
        Questions.questions.get(2).getQuestionAnswer().clear();

        Questions.questions.get(2).getQuestionAnswer().add(spinA.getSelectedItemPosition());
        Questions.questions.get(2).getQuestionAnswer().add(spinB.getSelectedItemPosition());
        Questions.questions.get(2).getQuestionAnswer().add(spinC.getSelectedItemPosition());

        Log.d("Text", Questions.questions.get(2).getQuestionAnswer().toString());
        Log.d("Text", Questions.questions.get(2).getPoint()+"");
    }
}
