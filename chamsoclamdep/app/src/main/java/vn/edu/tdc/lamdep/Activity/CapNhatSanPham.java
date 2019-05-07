package vn.edu.tdc.lamdep.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class CapNhatSanPham extends AppCompatActivity {
    EditText txttensanpham, txtgiasanpham, txtmota, txtloai, txtluotthich;
    ImageView imgchonanh;
    Button btnthem, btnhuy;

    int id = 0;
    String hinhAnh = "";
    String tenSanPham = "";
    int giaTien = 0;
    String moTa = "";
    int idSanPham = 0;
    int yeuthich = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_san_pham);
        setControl();
        toolBar();
        GetInformation();
        updateproduct();
    }


    private void updateproduct() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tensp = txttensanpham.getText().toString();
                final int loaisp = Integer.parseInt(txtloai.getText().toString());
                final int giasp = Integer.parseInt(txtgiasanpham.getText().toString());
                final String mota = txtmota.getText().toString();
                final int yeuthichsp = Integer.parseInt(String.valueOf(txtluotthich.getText().toString()));

                if (tensp.length() > 0 && txtloai.getText().toString().length() > 0 && txtgiasanpham.getText().toString().length() > 0 && mota.length() > 0 && txtluotthich.getText().toString().length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server.updateproduct, new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {
                                Toast.makeText(CapNhatSanPham.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), DanhSachSanPham.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CapNhatSanPham.this, "Lỗi sản phẩm", Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            CheckConnect.showToast_Short(getApplicationContext(), "Lỗi");
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("id", String.valueOf(id));
                            hashMap.put("tensanpham", tensp);
                            hashMap.put("giasanpham", String.valueOf(giasp));
                            hashMap.put("hinhanhsanpham", hinhAnh);
                            hashMap.put("motasanpham", mota);
                            hashMap.put("idsanpham", String.valueOf(loaisp));
                            hashMap.put("yeuthich", String.valueOf(yeuthichsp));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin");
                }
            }
        });
    }

    //Gọi dữ liệu lại khi truyền tham số sang màn hình chi tiết sản phẩm
    private void GetInformation() {
        sanPham sp = (sanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sp.getId();
        tenSanPham = sp.getTensanpham();
        giaTien = sp.getGiasanpham();
        hinhAnh = sp.getHinhanhsanpham();
        moTa = sp.getMotasanpham();
        idSanPham = sp.getIdsanpham();
        yeuthich = sp.getYeuthich();

        txtloai.requestFocus();
        txtloai.setText(String.valueOf(idSanPham));
        txttensanpham.setText(tenSanPham);
        txtgiasanpham.setText(String.valueOf(giaTien));
        txtmota.setText(moTa);
        txtluotthich.setText(String.valueOf(yeuthich));
        Picasso.with(getApplicationContext()).load(hinhAnh)
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(imgchonanh);
        txtluotthich.setText(String.valueOf(yeuthich));
    }

    private void setControl() {
        txtloai = (EditText) findViewById(R.id.editloaisanpham);
        txtluotthich = (EditText) findViewById(R.id.editluotthich);
        txttensanpham = (EditText) findViewById(R.id.edittensanpham);
        txtgiasanpham = (EditText) findViewById(R.id.editgiasanpham);
        txtmota = (EditText) findViewById(R.id.editmotasanpham);
        imgchonanh = (ImageView) findViewById(R.id.imgchonanh);
        btnthem = (Button) findViewById(R.id.btnthem);
        btnhuy = (Button) findViewById(R.id.btnhuy);
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
