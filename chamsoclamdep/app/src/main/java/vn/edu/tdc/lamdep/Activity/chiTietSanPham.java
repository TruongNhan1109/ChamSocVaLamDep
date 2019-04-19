package vn.edu.tdc.lamdep.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import vn.edu.tdc.lamdep.Model.gioHang;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;


public class chiTietSanPham extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imgicon, imgShow;
    TextView tvtensanpham, tvgiasanpham, tvmota, tvshowTenSP, txtluotthich;
    Spinner spinner, spinnerkichthuoc;
    Button btnthemhang, clickchonhinh, btnclose;
    ScaleGestureDetector scaleGestureDetector;
    int id = 0;
    String hinhAnh = "";
    String tenSanPham = "";
    int giaTien = 0;
    String moTa = "";
    int idSanPham = 0;
    int yeuthich = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        setControl();
        setInvent();
        GetInformation();
        CashEventSpinner();

        //Bắt sự kiện phóng to thu nhỏ imageview
        scaleGestureDetector = new ScaleGestureDetector(this, new MyGesture());

        toolBar();
    }

    //Khai báo gọi lại tool Bar và NavigationView
    private void toolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Gọi class scale
    class MyGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float scale = 1.0F, onScaleStart = 0, onScaleEnd = 0;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imgShow.setScaleX(scale);
            imgShow.setScaleY(scale);
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Toast.makeText(getApplicationContext(), "onScale start", Toast.LENGTH_SHORT).show();
            Log.d("scalexxxx", "Giá trị trước khi bắt đầu scale: " + onScaleStart);
            onScaleStart = scale;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            Toast.makeText(getApplicationContext(), "onScale End", Toast.LENGTH_SHORT).show();
            Log.d("scalexxxx", "Giá trị sau khi được scale: " + onScaleEnd);
            onScaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }

    private void setInvent() {
        clickchonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(chiTietSanPham.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_show_images_product);
                btnclose = (Button) dialog.findViewById(R.id.btnclose);
                imgShow = (ImageView) dialog.findViewById(R.id.imgShow);
                tvshowTenSP = (TextView) dialog.findViewById(R.id.showTenSP);
                //Bắt dự kiện chạm vào imageview;
                imgShow.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        scaleGestureDetector.onTouchEvent(motionEvent);
                        return true;
                    }
                });


                sanPham sp = (sanPham) getIntent().getSerializableExtra("thongtinsanpham");
                Picasso.with(getApplicationContext()).load(sp.getHinhanhsanpham())
                        .placeholder(R.drawable.noiimage)
                        .error(R.drawable.error)
                        .into(imgShow);
                tvshowTenSP.setText(sp.getTensanpham());


                //Tạo hiệu ứng phóng to và thu nhỏ hình ảnh sản phẩm;


                //Đóng dialog
                btnclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }


    //Tạo mảng cho spinner
    private void CashEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);

        String[] kichthuoc = new String[]{"S", "M", "L"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, kichthuoc);
        spinnerkichthuoc.setAdapter(stringArrayAdapter);
    }

    private void setControl() {
        imgicon = (ImageView) findViewById(R.id.imgicon);
        tvtensanpham = (TextView) findViewById(R.id.tensanpham);
        tvgiasanpham = (TextView) findViewById(R.id.giasanpham);
        tvmota = (TextView) findViewById(R.id.mota);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerkichthuoc = (Spinner) findViewById(R.id.spinnerkichthuoc);
        clickchonhinh = (Button) findViewById(R.id.clickchonhinh);
        txtluotthich = (TextView) findViewById(R.id.txtluotthich);
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

        tvtensanpham.setText(tenSanPham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvgiasanpham.setText("Giá: " + decimalFormat.format(giaTien) + "VNĐ");
        tvmota.setText(moTa);
        txtluotthich.setText(String.valueOf(yeuthich));
        Picasso.with(getApplicationContext()).load(hinhAnh)
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(imgicon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add:
                if (sanPhamActivity.manggiohang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    String kt = spinnerkichthuoc.getSelectedItem().toString();
                    boolean ex = false;
                    for (int i = 0; i < sanPhamActivity.manggiohang.size(); i++) {
                        if (sanPhamActivity.manggiohang.get(i).getIdsp() == id) {
                            sanPhamActivity.manggiohang.get(i).setSoLuong(sanPhamActivity.manggiohang.get(i).getSoLuong() + sl);
                            sanPhamActivity.manggiohang.get(i).setKichthuoc(sanPhamActivity.manggiohang.get(i).getKichthuoc().toString());
                            if (sanPhamActivity.manggiohang.get(i).getSoLuong() >= 10) {
                                sanPhamActivity.manggiohang.get(i).setSoLuong(10);
                            }
                            sanPhamActivity.manggiohang.get(i).setGia(giaTien * sanPhamActivity.manggiohang.get(i).getSoLuong());
                            sanPhamActivity.manggiohang.get(i).setKichthuoc(sanPhamActivity.manggiohang.get(i).getKichthuoc());
                            ex = true;
                        }
                    }
                    if (ex == false) {
                        //L?y s? lu?ng c?a Spinner
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int Giamoi = soluong * giaTien;
                        sanPhamActivity.manggiohang.add(new gioHang(id, tenSanPham, Giamoi, hinhAnh,moTa ,soluong, kt));
                    }

                } else {
                    //L?y s? lu?ng c?a Spinner
                    String kt = spinnerkichthuoc.getSelectedItem().toString();
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * giaTien;
                    sanPhamActivity.manggiohang.add(new gioHang(id, tenSanPham, Giamoi, hinhAnh, moTa,soluong, kt));
                }
                intent = new Intent(chiTietSanPham.this, cartActivity.class);
                startActivity(intent);

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
        if (id == R.id.nav_home) {
            if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                Intent intent = new Intent(chiTietSanPham.this, MainActivity.class);
                startActivity(intent);
            } else {
                CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
            }
        }
        if (id == R.id.nav_sanpham) {
            if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                Intent intent = new Intent(chiTietSanPham.this, sanPhamActivity.class);
                startActivity(intent);

            } else {
                CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra lại kết nối mạng");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
