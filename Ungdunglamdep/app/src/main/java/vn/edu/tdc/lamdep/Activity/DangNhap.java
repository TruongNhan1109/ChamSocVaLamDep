package vn.edu.tdc.lamdep.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.edu.tdc.lamdep.R;

public class DangNhap extends AppCompatActivity {

    LoginButton loginButton;
    //TextView mTvInfo;
    LoginButton mBtnLoginFacebook;
    CallbackManager callbackManager;
    Button btnDK, btnThoat, btnDN;
    EditText edtTK, edtMK;
    // nut dk
    String ten, mk;
    DatabaseReference daTa;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Anhxa();

        // Bac su kien cho cac nut.
        event();

        setLogin_Facebook();


    }

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


    private void event() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có muốn quay lại không ?");
                builder.setMessage("Mời bạn chọn");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DangNhap.this);
                dialog.setTitle("Tien hanh dang ky");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layout_dk);
                final EditText edtTHTK = (EditText) dialog.findViewById(R.id.edtTHTK);
                final EditText edtTHMK = (EditText) dialog.findViewById(R.id.edtTHMK);

                Button btnTHDK = (Button) dialog.findViewById(R.id.btnTHDK);
                Button btnTHHUY = (Button) dialog.findViewById(R.id.btnTHHUY);

                btnTHDK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten = edtTHTK.getText().toString().trim();
                        mk = edtTHMK.getText().toString().trim();

                        edtTK.setText(ten);
                        edtMK.setText(mk);
                        dialog.cancel();
                    }

                });

                btnTHHUY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
//        btnDK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DanKy();
//            }
//        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTK.getText().length() != 0 && edtMK.getText().length() != 0)
                {
                    if(edtTK.getText().toString().equals(ten) && edtMK.getText().toString().equals(mk))
                    {
                        Toast.makeText(DangNhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    }
                    else if (edtTK.getText().toString().equals("quoc") && edtMK.getText().toString().equals("20081996"))
                    {
                        Toast.makeText(DangNhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhap.this, DangKy.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(DangNhap.this, "Dang nhap that bai", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(DangNhap.this, "Moi ban nhap du thong  tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void DanKy() {
////        Intent intent = new Intent(DangNhap.this, DangKy.class);
////        Bundle bundle = new Bundle();
////        bundle.putString(;
//        String tenTK = edtTK.getText().toString();
//        String password = edtMK.getText().toString();
//        mAuth.createUserWithEmailAndPassword(tenTK, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(DangNhap.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
//                        }
//                        else {
//                            Toast.makeText(DangNhap.this, "Đăng ký thất bại", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
    private void setLogin_Facebook() {
        mBtnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //mTvInfo.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Au" + loginResult.getAccessToken().getToken());

                // Khi dang nhap thanh cong
                Intent intent = new Intent(DangNhap.this, Home.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(DangNhap.this, "Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(DangNhap.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Anhxa() {
        callbackManager = CallbackManager.Factory.create();
        //mTvInfo = (TextView) findViewById(R.id.txtCode);
        mBtnLoginFacebook = (LoginButton) findViewById(R.id.login_button);
        btnDN = (Button) findViewById(R.id.btnDN);
        btnDK = (Button) findViewById(R.id.btnDK);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        edtTK = (EditText) findViewById(R.id.edtTK);
        edtMK = (EditText) findViewById(R.id.edtMK);
        daTa = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

