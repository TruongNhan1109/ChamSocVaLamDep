package com.example.administrator.baitapthuchanh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Bai1Activity extends AppCompatActivity {

    // Khai bao
    TextView txtHello;
    Button btnClick, btnRandom;
    EditText edtUser;
    TextView txtRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laout_bai1);

        // ten bien Anh xa
        Anhxa();

        // Su kien
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHello.setText("Chào bạn !!! ok");

                // Tao ra bien de lay gia trị
                String noiDungUser = edtUser.getText().toString();

                // Goi ra man hinh
                Toast.makeText(Bai1Activity.this, noiDungUser, Toast.LENGTH_LONG).show();
            }
        });

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chom sô ngau nhien be hon 100
                Random random = new Random();
                int number = random.nextInt(100);
                txtRandom.setText(number + "");
            }
        });
    }

    private void Anhxa() {
        txtHello = (TextView) findViewById(R.id.txtHello);
        btnClick = (Button) findViewById(R.id.btnClick);
        edtUser = (EditText) findViewById(R.id.edtUser);
        txtRandom = (TextView) findViewById(R.id.txtRandom);
        btnRandom = (Button) findViewById(R.id.btnRandom);
    }
}
