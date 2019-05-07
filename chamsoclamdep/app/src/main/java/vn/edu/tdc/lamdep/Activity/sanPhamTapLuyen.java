package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
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

import vn.edu.tdc.lamdep.Adapter.sanPhamTapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class sanPhamTapLuyen extends AppCompatActivity {

    static ListView lvtapluyen;
    static sanPhamTapLuyenAdapter tapLuyenAdapter;
    static ArrayList<sanPham> mangtapluyen;

    static SwipeRefreshLayout mSwipeRefreshLayout;

    int idtapluyen = 0;
    int page = 1;
    View footerview;
    boolean limitdata = false;
    boolean Loading = false;
    sanPhamTapLuyen.mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_tap_luyen);
        setControl();
        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
            GetIdloaisp();
            LoadMoreData();
            getData(page);
            refresh();
        } else {
            CheckConnect.showToast_Short(getApplicationContext(), "Lỗi mạng");
            CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra kết nối");
            finish();
        }

        toolBar();
    }

    //Cập nhật dữ liệu
    public void refresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadMoreData();
                getData(page);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getData(int Page) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = server.duongdanloaigetsanpham + String.valueOf(Page);
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
                    lvtapluyen.removeFooterView(footerview);
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
                            mangtapluyen.add(new sanPham(id, tentapluyen, giatapluyen, hinhanhtapluyen, motatapluyen, idsptapluyen,yeuthich));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                } else {
                    limitdata = true;
                    lvtapluyen.removeFooterView(footerview);
                    CheckConnect.showToast_Short(getApplicationContext(), "\n" +
                            "The data has run out");
                }
                tapLuyenAdapter.notifyDataSetChanged();
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
                param.put("idsanpham", String.valueOf(idtapluyen));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void LoadMoreData() {
        lvtapluyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chiTietSanPham.class);
                intent.putExtra("thongtinsanpham", mangtapluyen.get(position));
                startActivity(intent);
            }
        });
        lvtapluyen.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && Loading == false && limitdata == false) {
                    Loading = true;
                    sanPhamTapLuyen.ThreadData threadData = new sanPhamTapLuyen.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetIdloaisp() {
        idtapluyen = getIntent().getIntExtra("idLoaisanpham", -1);
        Log.d("giatriloaisanpham", idtapluyen + "");
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

    private void setControl() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        lvtapluyen = findViewById(R.id.listviewtapluyen);
        mangtapluyen = new ArrayList<>();
        tapLuyenAdapter = new sanPhamTapLuyenAdapter(sanPhamTapLuyen.this, mangtapluyen);
        lvtapluyen.setAdapter(tapLuyenAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lvtapluyen.addFooterView(footerview);
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


            //Khi người dùng chọn vào menu giỏ hàng di chuyển đến màn hình giỏ hàng
            case R.id.menugiohang:
                intent = new Intent(sanPhamTapLuyen.this, cartActivity.class);
                startActivity(intent);
                break;
                //Khi người dùng chọn vào menu giỏ hàng di chuyển đến màn hình giỏ hàng
            case R.id.menuseachview:
                intent = new Intent(sanPhamTapLuyen.this, TimKiemSanPham.class);
                startActivity(intent);
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
