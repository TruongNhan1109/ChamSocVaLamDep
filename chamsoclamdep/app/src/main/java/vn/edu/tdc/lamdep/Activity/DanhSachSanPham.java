package vn.edu.tdc.lamdep.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vn.edu.tdc.lamdep.Adapter.SanPhamAdapter;
import vn.edu.tdc.lamdep.Adapter.sucKhoeLamDepAdapter;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class DanhSachSanPham extends AppCompatActivity {

    UserLocalStore userLocalStore;

    ListView lvlsanpham;
    static SanPhamAdapter sanPhamAdapter;
    public static ArrayList<sanPham> mangsanpham;
    int position;

    int page = 1;
    View footerview;
    boolean limitdata = false;
    boolean Loading = false;
    DanhSachSanPham.mHandler mHandler;

    Button btnclose, btndongy;
    ImageView imgShow;
    TextView showTenSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);

        userLocalStore = new UserLocalStore(this);

        setControl();
        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
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

    public static void capnhatdulieu() {
        sanPhamAdapter.notifyDataSetChanged();
    }

    public void deleteproduct(final int idsanpham) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.deleteproduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(DanhSachSanPham.this, "Xóa thành công sản phẩm " + mangsanpham.get(position).getTensanpham(), Toast.LENGTH_LONG).show();
                    mangsanpham.remove(position);
                    getData(page);
                } else {
                    Toast.makeText(DanhSachSanPham.this, "Lỗi sản phẩm", Toast.LENGTH_LONG).show();
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
                hashMap.put("id", String.valueOf(idsanpham));
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getData(int Page) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = server.getAllProduct + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tensanpham = "";
                int giasanpham = 0;
                String hinhanhsanpham = "";
                String motasanpham = "";
                int idsanpham = 0;
                int yeuthich = 0;
                if (response != null && response.length() != 2) {
                    lvlsanpham.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensanpham");
                            giasanpham = jsonObject.getInt("giasanpham");
                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            motasanpham = jsonObject.getString("motasanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            yeuthich = jsonObject.getInt("yeuthich");
                            mangsanpham.add(new sanPham(id, tensanpham, giasanpham, hinhanhsanpham, motasanpham, idsanpham, yeuthich));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                } else {
                    limitdata = true;
                    lvlsanpham.removeFooterView(footerview);
                    CheckConnect.showToast_Short(getApplicationContext(), "\n" +
                            "The data has run out");
                }
                sanPhamAdapter.notifyDataSetChanged();
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

    private void LoadMoreData() {
        lvlsanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chiTietSanPham.class);
                intent.putExtra("thongtinsanpham", mangsanpham.get(position));
                startActivity(intent);
            }
        });
        lvlsanpham.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && Loading == false && limitdata == false) {
                    Loading = true;
                    DanhSachSanPham.ThreadData threadData = new DanhSachSanPham.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void setControl() {
        lvlsanpham = findViewById(R.id.listviewsanpham);
        mangsanpham = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(DanhSachSanPham.this, mangsanpham);
        lvlsanpham.setAdapter(sanPhamAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new DanhSachSanPham.mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lvlsanpham.addFooterView(footerview);
                    break;
                case 1:
                    getData(++page);
                    Loading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public void showdialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CheckConnect.showToast_Short(getApplicationContext(), "Mời bạn tiếp tục chương trình");
            }
        });
        builder.setNegativeButton("Đông ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteproduct(mangsanpham.get(position).getId());
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
        getMenuInflater().inflate(R.menu.add_cart, menu);

        return true;
    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        User user = userLocalStore.getLoggedInUser();
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:

                // Hủy màn hình
                finish();
                onBackPressed();
                return true;

            case R.id.add:
                intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);
                break;

            case R.id.menuseachview:
                if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                    intent = new Intent(DanhSachSanPham.this, TimKiemSanPham.class);
                    startActivity(intent);
                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
                }
                break;

            case R.id.menuLogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logout");
                builder.setMessage("Username : " + user.username + "\nEmail :" + user.email);
                builder.setCancelable(false);
                builder.setPositiveButton("Trở về", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DanhSachSanPham.this, "Mời bạn tiếp tục mua sắm", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Code Logout
                        userLocalStore.clearUserData();
                        userLocalStore.setUserLoggedIn(false);
                        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(loginIntent);
                        Toast.makeText(DanhSachSanPham.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
//            userLocalStore.clearUserData();
//            userLocalStore.setUserLoggedIn(false);
//            Intent loginIntent = new Intent(this, LoginActivity.class);
//            startActivity(loginIntent);
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        Toast.makeText(getApplicationContext(),
                user.username,
                Toast.LENGTH_SHORT).show();

    }
}
