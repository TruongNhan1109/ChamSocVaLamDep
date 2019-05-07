package vn.edu.tdc.tracnghiemtq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityScreenKetThuc extends AppCompatActivity {
Intent intent;
Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenketthuc_layout);
        //
        Button btnPre = (Button) findViewById(R.id.btnPre);
        Button btnKetThuc = (Button) findViewById(R.id.btnKetThuc);
        Button btnNext  = (Button) findViewById(R.id.btnNext);
        //
        intent=new Intent();
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(ActivityScreenKetThuc.this,ActivityScreen5.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityScreenKetThuc.this,ActivityScreen1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
    }
}
