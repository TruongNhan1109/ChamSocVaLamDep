package vn.edu.tdc.lamdep.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Fragment.Home;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class DoiMatKhau extends AppCompatActivity {

    UserLocalStore userLocalStore;

    Button btncapnhat, btnhuy;
    EditText txtmatkhaumoi, txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        userLocalStore = new UserLocalStore(this);
        setInvent();
    }

    private void setInvent() {
        txtmatkhaumoi = (EditText) findViewById(R.id.txtmatkhaumoi);
        txtemail = (EditText) findViewById(R.id.txtemail);
        btncapnhat = (Button) findViewById(R.id.btnCapnhat);
        btnhuy = (Button) findViewById(R.id.btnHuy);

        User user = userLocalStore.getLoggedInUser();
        //nhận dữ liệu email
        final String email = user.email;
        txtemail.setText(email);

        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String matkhaumoi = txtmatkhaumoi.getText().toString();
                if (txtmatkhaumoi.getText().toString().trim().length() > 0) {
                    final ApiInterface apiInterface = APISERVISE.getServise();
                    Call<String> listCall = apiInterface.UpdatePassword(email, matkhaumoi);
                    listCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("success")) {
                                Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), sanPhamActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Không trùng khớp", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                CheckConnect.showToast_Short(getApplicationContext(), "Cancel");
            }
        });
    }
}
