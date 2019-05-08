package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
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

import vn.edu.tdc.lamdep.Adapter.sucKhoeLamDepAdapter;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class sucKhoeLamDepActivity extends AppCompatActivity {

    ListView lvsuckhoe;
    sucKhoeLamDepAdapter khoeLamDepAdapter;
    ArrayList<sanPham> mangsuckhoe;

    int idtsuckhoe = 0;
    int page = 1;
    View footerview;
    boolean limitdata = false;
    boolean Loading = false;
    sucKhoeLamDepActivity.mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suc_khoe_lam_dep);

        setControl();
        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
            GetIdloaisp();
            LoadMoreData();
            getData(page);
        } else {
            CheckConnect.showToast_Short(getApplicationContext(), "Lỗi mạng");
            CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra kết nối");
            finish();
        }
        toolBar();
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


    private void getData(int Page) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        String duongdan = server.duongdanloaigetsanpham2 + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tentapluyen = "";
                int giatapluyen = 0;
                String hinhanhtapluyen = "";
                String motatapluyen = "";
                int idsptapluyen = 0;
                int yeuthich = 0;
                if (response != null && response.length() != 2) {
                    lvsuckhoe.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tentapluyen = jsonObject.getString("tensanpham");
                            giatapluyen = jsonObject.getInt("giasanpham");
                            hinhanhtapluyen = jsonObject.getString("hinhanhsanpham");
                            motatapluyen = jsonObject.getString("motasanpham");
                            idsptapluyen = jsonObject.getInt("idsanpham");
                            yeuthich = jsonObject.getInt("yeuthich");
                            mangsuckhoe.add(new sanPham(id, tentapluyen, giatapluyen, hinhanhtapluyen, motatapluyen, idsptapluyen,yeuthich));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                else {
                    limitdata = true;
                    lvsuckhoe.removeFooterView(footerview);
                    CheckConnect.showToast_Short(getApplicationContext(), "\n" +
                            "The data has run out");
                }
                khoeLamDepAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(idtsuckhoe));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void LoadMoreData() {
        lvsuckhoe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chiTietSanPham.class);
                intent.putExtra("thongtinsanpham", mangsuckhoe.get(position));
                startActivity(intent);
            }
        });
        lvsuckhoe.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && Loading == false && limitdata == false) {
                    Loading = true;
                    sucKhoeLamDepActivity.ThreadData threadData = new sucKhoeLamDepActivity.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetIdloaisp() {
        idtsuckhoe = getIntent().getIntExtra("idLoaisanpham", -1);
        Log.d("giatriloaisanpham", idtsuckhoe + "");
    }

    private void setControl() {
        lvsuckhoe = findViewById(R.id.listviewsuckhoe);
        mangsuckhoe = new ArrayList<>();
        khoeLamDepAdapter = new sucKhoeLamDepAdapter(getApplicationContext(), mangsuckhoe);
        lvsuckhoe.setAdapter(khoeLamDepAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new sucKhoeLamDepActivity.mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lvsuckhoe.addFooterView(footerview);
                    break;
                case 1:
                    getData(++page);
                    Loading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shopcart, menu);
        return true;
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
