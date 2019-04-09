package vn.edu.tdc.lamdep.View.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.tdc.lamdep.Activity.Home;
import vn.edu.tdc.lamdep.Activity.MainActivity;
import vn.edu.tdc.lamdep.R;

public class manHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(manHinhChao.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();

    }
}
