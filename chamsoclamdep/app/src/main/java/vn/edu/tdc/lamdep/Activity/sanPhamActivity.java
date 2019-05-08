package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import vn.edu.tdc.lamdep.Adapter.LoaispAdapter;
import vn.edu.tdc.lamdep.Adapter.SanPhamBanChayAdapter;
import vn.edu.tdc.lamdep.Adapter.SanPhamFlashSaleAdapter;
import vn.edu.tdc.lamdep.Adapter.sanPhamMoiNhatAdapter;
import vn.edu.tdc.lamdep.Model.SanPhamBanChay;
import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.Model.gioHang;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;
import vn.edu.tdc.lamdep.unitl.server;

public class sanPhamActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ViewFlipper viewFlipper;
    RecyclerView rvsanphammoinhat;
    sanPhamMoiNhatAdapter moiNhatAdapter;

    public static ArrayList<gioHang> manggiohang;

    RecyclerView rvsanphambanchay;
    SanPhamBanChayAdapter sanPhamBanChayAdapter;

    ListView listViewManHinhChinh;
    ArrayList<danhMucSanPham> mangloaisanpham;
    LoaispAdapter loaispAdapter;

    RecyclerView recyclerViewflashSaleItems;
    ImageView imageViewHotItems, imageViewHot, imageboxlike;
    ArrayList<sanPham> mangSanPhamFlashSale;
    SanPhamFlashSaleAdapter sanPhamFlashSaleAdapter;

    int ID = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);

        setControl();
        if (CheckConnect.haveNetworkConnection(sanPhamActivity.this)) {
            ViewFlipper();
            getDataDanhMuc();
            getDataSanPhamMoiNhat();
            getDuLieuSPFlashSale();
            catOnItemListView();
            getDataBanChay();
            toolBar();
        } else {
            CheckConnect.showToast_Short(getApplicationContext(), "Lỗi mạng");
            CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra kết nối");
            finish();
        }
    }


    private void getDuLieuSPFlashSale() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.duongDanSanPhamFlashSale, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0;
                    String tensanpham = "";
                    int giasanpham = 0;
                    String hinhanhsanpham = "";
                    String motasanpham = "";
                    int idsanpham = 0;
                    int yeuthich = 0;

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensanpham");
                            giasanpham = jsonObject.getInt("giasanpham");
                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            motasanpham = jsonObject.getString("motasanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            yeuthich = jsonObject.getInt("yeuthich");
                            mangSanPhamFlashSale.add(new sanPham(id, tensanpham, giasanpham, hinhanhsanpham, motasanpham, idsanpham, yeuthich));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    sanPhamFlashSaleAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    private void ViewFlipper() {
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://incucre.com/wp-content/uploads/2017/03/thiet-ke-banner-2-1.jpg");
        quangcao.add("https://znews-photo.zadn.vn/w660/Uploaded/wyhktpu/2018_11_08/image001_12.jpg");
        quangcao.add("http://img.zanado.com/media/wysiwyg/2015/BLOG/KM/KM02/Banner_30-04-2015_710xN.jpg");
        quangcao.add("http://dongahotelgroup.com/wp-content/uploads/2018/10/Happy-201-10.jpg");
        quangcao.add("https://i.ytimg.com/vi/eCiJhX4BA-M/maxresdefault.jpg");
        quangcao.add("https://salt.tikicdn.com/ts/categoryblock/35/a7/c9/8a8d8cbf3a19342aaeecef4c001a872e.jpg");
        quangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqbX-QSeeiy4Vcu8RM7hnkrWqik9jFF-tTEBxgu7m4F9oUDYr_");
        quangcao.add("http://sagishop.vn/wp-content/uploads/2017/11/deal-weekly-e1511511926979.png");
        for (int i = 0; i < quangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_out_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_in_right);
        viewFlipper.setOutAnimation(animation_out_right);

    }


    private void getDataBanChay() {
        ApiInterface apiInterface = APISERVISE.getServise();
        Call<List<SanPhamBanChay>> listCall = apiInterface.GetDataProductBanChay();
        listCall.enqueue(new Callback<List<SanPhamBanChay>>() {
            @Override
            public void onResponse(Call<List<SanPhamBanChay>> call, retrofit2.Response<List<SanPhamBanChay>> response) {
                ArrayList<SanPhamBanChay> mangsanphambanchay = (ArrayList<SanPhamBanChay>) response.body();
                sanPhamBanChayAdapter = new SanPhamBanChayAdapter(getApplicationContext(), mangsanphambanchay);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
                rvsanphambanchay.setLayoutManager(linearLayoutManager);
                rvsanphambanchay.setAdapter(sanPhamBanChayAdapter);
                rvsanphambanchay.setAdapter(new ScaleInAnimationAdapter(sanPhamBanChayAdapter));
                rvsanphambanchay.setHasFixedSize(true);
                sanPhamBanChayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SanPhamBanChay>> call, Throwable t) {
            }
        });
    }


    private void getDataSanPhamMoiNhat() {
        ApiInterface apiInterface = APISERVISE.getServise();
        Call<List<sanPham>> listCall = apiInterface.GetDataSanPhamMoiNhat();
        listCall.enqueue(new Callback<List<sanPham>>() {
            @Override
            public void onResponse(Call<List<sanPham>> call, retrofit2.Response<List<sanPham>> response) {
                ArrayList<sanPham> mangsanphammoinhat = (ArrayList<sanPham>) response.body();
                moiNhatAdapter = new sanPhamMoiNhatAdapter(getApplicationContext(), mangsanphammoinhat);
                rvsanphammoinhat.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                rvsanphammoinhat.setAdapter(moiNhatAdapter);
                rvsanphammoinhat.setAdapter(new ScaleInAnimationAdapter(moiNhatAdapter));
                rvsanphammoinhat.setHasFixedSize(true);
                moiNhatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<sanPham>> call, Throwable t) {
            }
        });
    }

    private void getDataDanhMuc() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.duongdanloaisanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisanpham");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisanpham");
                            mangloaisanpham.add(new danhMucSanPham(ID, tenloaisp, hinhanhloaisp));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    loaispAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void catOnItemListView() {
        imageboxlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sanPhamActivity.this, ProductLike.class);
                startActivity(intent);
            }
        });
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, sanPhamTapLuyen.class);
                            intent.putExtra("idLoaiSanPham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, ThoiTrangThuongHieuActivity.class);
                            intent.putExtra("idLoaiSanPham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, sucKhoeLamDepActivity.class);
                            intent.putExtra("idLoaiSanPham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, DongHoTrangSuc.class);
                            intent.putExtra("idLoaiSanPham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, ThoiTrangNam.class);
                            intent.putExtra("idLoaiSanPham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(sanPhamActivity.this, ThoiTrangNu.class);
                            intent.putExtra("idLoaiSanPham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    //Khai báo gọi lại tool Bar và NavigationView
    private void toolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sản phẩm");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_manhinhchinh);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setControl() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        rvsanphambanchay = (RecyclerView) findViewById(R.id.recyclerViewBanChay);
        listViewManHinhChinh = (ListView) findViewById(R.id.lvlmanhinhchinh);
        rvsanphammoinhat = (RecyclerView) findViewById(R.id.rvsanphammoinhat);
        recyclerViewflashSaleItems = findViewById(R.id.recyclerViewHotItems);
        mangloaisanpham = new ArrayList<>();
        mangloaisanpham.add(0, new danhMucSanPham(0, "Clothesuit", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyyPFbxER9CaziwnRh9UgBFqauaA2DOR_ZTCXFkK9iLmFdeoPE5w"));
        loaispAdapter = new LoaispAdapter(mangloaisanpham, getApplicationContext());
        listViewManHinhChinh.setAdapter(loaispAdapter);

        mangSanPhamFlashSale = new ArrayList<>();
        sanPhamFlashSaleAdapter = new SanPhamFlashSaleAdapter(getApplicationContext(), mangSanPhamFlashSale);

        recyclerViewflashSaleItems.setHasFixedSize(true);
        recyclerViewflashSaleItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewflashSaleItems.setAdapter(sanPhamFlashSaleAdapter);

        //Kiểm tra mảng
        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }

        imageViewHotItems = findViewById(R.id.imageViewHotItems);
        imageViewHotItems.setImageResource(R.drawable.flashsale);
        imageViewHotItems.setScaleType(ImageView.ScaleType.FIT_START);

        imageViewHot = findViewById(R.id.imageViewHot);
        imageViewHot.setImageResource(R.drawable.newhot);
        imageViewHot.setScaleType(ImageView.ScaleType.FIT_START);

        imageboxlike = findViewById(R.id.imageboxlike);
        imageboxlike.setImageResource(R.drawable.giftbox);
        imageboxlike.setScaleType(ImageView.ScaleType.FIT_END);

        Animation animationRotale = AnimationUtils.loadAnimation(this, R.anim.nhapnhay);
        imageViewHot.startAnimation(animationRotale);

        Animation animationRotale1 = AnimationUtils.loadAnimation(this, R.anim.nhapnhay);
        imageViewHotItems.startAnimation(animationRotale1);

        Animation animationRotale2 = AnimationUtils.loadAnimation(this, R.anim.nhapnhay);
        imageboxlike.startAnimation(animationRotale2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shopcart, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                    Intent intent = new Intent(sanPhamActivity.this, cartActivity.class);
                    startActivity(intent);
                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
                }
                break;
            case R.id.menuseachview:
                if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                    Intent intent = new Intent(sanPhamActivity.this, TimKiemSanPham.class);
                    startActivity(intent);
                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
                }
                break;
            case R.id.menuLogout:
                if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                    Intent intent = new Intent(sanPhamActivity.this, DoiMatKhau.class);
                    startActivity(intent);
                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // sự kiện kích chọn vào navigationview chuyển màn hình
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        switch (id) {
            case R.id.nav_home:
                if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                    Intent intent = new Intent(sanPhamActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
                }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
