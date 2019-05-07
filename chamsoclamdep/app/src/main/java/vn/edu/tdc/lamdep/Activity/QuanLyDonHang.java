package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vn.edu.tdc.lamdep.Adapter.QuanLyDonHangAdapter;
import vn.edu.tdc.lamdep.Adapter.QuanTriVienAdapter;
import vn.edu.tdc.lamdep.Adapter.sanPhamTapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.DonHang;
import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class QuanLyDonHang extends AppCompatActivity {

    ListView listViewquanlydonhang;
    ArrayList<DonHang> mangdonhang;
    QuanLyDonHangAdapter quanLyDonHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);
        setControl();
        toolBar();
        getData();
    }

    private void setControl() {
        listViewquanlydonhang = findViewById(R.id.lvlquanlydonhang);
        mangdonhang = new ArrayList<>();
        quanLyDonHangAdapter = new QuanLyDonHangAdapter(mangdonhang, QuanLyDonHang.this);
        listViewquanlydonhang.setAdapter(quanLyDonHangAdapter);
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.getDonHang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tenkhachhang = "";
                String sodienthoai = "";
                String email = "";
                String diachi = "";
                if (response != null && response.length() != 2) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenkhachhang = jsonObject.getString("tenkhachhang");
                            sodienthoai = jsonObject.getString("sodienthoai");
                            email = jsonObject.getString("email");
                            diachi = jsonObject.getString("diachi");
                            mangdonhang.add(new DonHang(id, tenkhachhang, sodienthoai, email, diachi));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "\n" +
                            "The data has run out");
                }
                quanLyDonHangAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        requestQueue.add(stringRequest);
    }


    //Khai báo gọi lại tool Bar và NavigationView
    private void toolBar() {
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

        Intent intent;
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
