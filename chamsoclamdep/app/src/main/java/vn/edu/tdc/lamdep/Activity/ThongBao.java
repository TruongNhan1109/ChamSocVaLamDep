package vn.edu.tdc.lamdep.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import vn.edu.tdc.lamdep.R;

public class ThongBao extends AppCompatActivity {
    Button btntieptucmua;
    ImageView imggiaohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        toolBar();
        setControl();
        setInvent();
        TranslateAnimation translateXAnimation = new TranslateAnimation(0f, 1000f, 0f, 0f);
        translateXAnimation.setDuration(20000);
        translateXAnimation.setRepeatMode(Animation.RESTART);
        translateXAnimation.setRepeatCount(Animation.INFINITE);
        imggiaohang.startAnimation(translateXAnimation);
    }


    private void toolBar() {
        // Khởi tạo tool bar và thay thế action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Loại bỏ tiêu đề tool bar
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Hiển thị nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setInvent() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongBao.this, sanPhamActivity.class);
                startActivity(intent);

            }
        });
    }

    private void setControl() {
        imggiaohang = (ImageView) findViewById(R.id.imggiaohang);
        btntieptucmua = (Button) findViewById(R.id.btntieptucmua);
    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                // Hủy màn hình
                finish();
                onBackPressed();
                return true;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
