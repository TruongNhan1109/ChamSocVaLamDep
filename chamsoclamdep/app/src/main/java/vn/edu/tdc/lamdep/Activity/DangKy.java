package vn.edu.tdc.lamdep.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import vn.edu.tdc.lamdep.R;

public class DangKy extends AppCompatActivity {

//    private EditText inputEmail, inputPassword;
//    private Button btnDangNhap, btnDangKy, btnDoiMatKhau;
//    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Get view from layout
//        final EditText lblEmail = (EditText) findViewById(R.id.lblEmail);
//        final EditText lblPassword = (EditText) findViewById(R.id.lblPassword);
//        final Button btnDangKy = (Button) findViewById(R.id.btn_sign_up);
//        final Button btnDoiMatKhau = (Button) findViewById(R.id.btn_reset_password);
//        final Button btnDangNhap = (Button) findViewById(R.id.btn_sign_in);
//        final EditText inputEmail = (EditText) findViewById(R.id.lblEmail);
//        final EditText inputPassword = (EditText) findViewById(R.id.lblPassword);
//        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DangKy.this, DangNhap.class));
//            }
//        });
//
//        btnDangNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DangKy.this, DangNhap.class);
//                startActivity(intent);
//            }
//        });

//        btnDangKy.setOnClickListener(new View.OnClickListener() {
//
//            String email = inputEmail.getText().toString().trim();
//            String password = inputPassword.getText().toString().trim();
//
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplicationContext(), "Nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(), "Nhập mật khẩu!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (password.length() < 6) {
//                    Toast.makeText(getApplicationContext(), "Mật khẩu không hợp lệ!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        progressBar.setVisibility(View.GONE);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
