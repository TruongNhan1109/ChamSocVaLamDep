package vn.edu.tdc.lamdep.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.HashMap;
import java.util.Map;

import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class ThongTinKhachHang extends AppCompatActivity {
    EditText edtenkhachhang, edsdt, edemail, eddiachi;
    Button btxacnhan, bttrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        anhxa();
        bttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
            eventButton();
        } else {
            CheckConnect.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void eventButton() {
        btxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = edtenkhachhang.getText().toString().trim();
                final String Phone = edsdt.getText().toString().trim();
                final String email = edemail.getText().toString().trim();
                final String Address = eddiachi.getText().toString().trim();
                if (Name.length() > 0 && Phone.length() > 0 && email.length() > 0 && Address.length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server.duongDanThongTinKhachHang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("Madonhang", madonhang);
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest request = new StringRequest(Request.Method.POST, server.duongDanChiTietDonHang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("test", response);
                                    if (response != null) {
                                        sanPhamActivity.manggiohang.clear();
                                        Intent intent = new Intent(getApplicationContext(), sanPhamActivity.class);
                                        CheckConnect.showToast_Short(getApplicationContext(), "You have successfully added data to your cart");
                                        startActivity(intent);
                                        CheckConnect.showToast_Short(getApplicationContext(), "\n" +
                                                "Please continue shopping");
                                    } else {
                                        CheckConnect.showToast_Short(getApplicationContext(), "Cart data has failed");
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    JSONArray jsonArray = new JSONArray();
                                    for (int i = 0; i < sanPhamActivity.manggiohang.size(); i++) {
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("madonhang",madonhang);
                                            jsonObject.put("masanpham", sanPhamActivity.manggiohang.get(i).getIdsp());
                                            jsonObject.put("tensanpham", sanPhamActivity.manggiohang.get(i).getTensp());
                                            jsonObject.put("giasanpham", sanPhamActivity.manggiohang.get(i).getGia());
                                            jsonObject.put("hinhanhsanpham", sanPhamActivity.manggiohang.get(i).getHinhAnh());
                                            jsonObject.put("motasanpham", sanPhamActivity.manggiohang.get(i).getMota());
                                            jsonObject.put("soluongsanpham", sanPhamActivity.manggiohang.get(i).getSoLuong());
                                            jsonObject.put("kichthuoc", sanPhamActivity.manggiohang.get(i).getKichthuoc());

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                    }
                                    HashMap<String, String> hashMap = new HashMap<String, String>();
                                    hashMap.put("json", jsonArray.toString());
                                    return hashMap;
                                }
                            };
                            queue.add(request);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang", Name);
                            hashMap.put("sodienthoai", Phone);
                            hashMap.put("email", email);
                            hashMap.put("diachi", Address);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Kiem Tra Lai Du Lieu");
                }
            }
        });
    }

    private void anhxa() {
        edtenkhachhang = findViewById(R.id.txthotenkhachhang);
        edsdt = findViewById(R.id.txtsodienthoaikhachhang);
        edemail = findViewById(R.id.txtemailkhachhang);
        eddiachi = findViewById(R.id.txtdiachikhachhang);
        btxacnhan = findViewById(R.id.btnxacnhanthongtin);
        bttrove = findViewById(R.id.btnhuy);
    }
}
