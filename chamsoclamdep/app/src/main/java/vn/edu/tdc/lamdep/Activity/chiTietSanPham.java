package vn.edu.tdc.lamdep.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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


public class chiTietSanPham extends AppCompatActivity {
    //Khai báo thuộc tính
    ImageView imgicon, imgShow;
    TextView tvtensanpham, tvgiasanpham, tvmota, tvshowTenSP, txtluotthich;
    Spinner spinner, spinnerkichthuoc;
    Button btnthemhang, clickchonhinh, btnclose;
    //Khi báo hàm ScaleGestureDetector
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
        // khởi tạo
        scaleGestureDetector = new ScaleGestureDetector(this, new MyGesture());

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

    //Gọi class scale
    class MyGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        // scale = 1.0F tỉ lệ ban đầu của image
        float scale = 1.0F, onScaleStart = 0, onScaleEnd = 0;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            // phóng theo chiều ngang
            imgShow.setScaleX(scale);
            // phóng theo chiều cao
            imgShow.setScaleY(scale);
            return super.onScale(detector);
        }

        // khi bắt đầu scale
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Toast.makeText(getApplicationContext(), "onScale start", Toast.LENGTH_SHORT).show();
            Log.d("scalexxxx", "Giá trị trước khi bắt đầu scale: " + onScaleStart);
            onScaleStart = scale;
            return super.onScaleBegin(detector);
        }

        // khi kết thúc scale
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            Toast.makeText(getApplicationContext(), "onScale End", Toast.LENGTH_SHORT).show();
            Log.d("scalexxxx", "Giá trị sau khi được scale: " + onScaleEnd);
            onScaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }

    private void setInvent() {
        // kích chọn vào button hiển thị một thông báo
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


                // get liệu từ các màn hình
                sanPham sp = (sanPham) getIntent().getSerializableExtra("thongtinsanpham");
                Picasso.with(getApplicationContext()).load(sp.getHinhanhsanpham())
                        .placeholder(R.drawable.noiimage)
                        .error(R.drawable.error)
                        .into(imgShow);
                tvshowTenSP.setText(sp.getTensanpham());

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

    // Khai báo các thuộc tính
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
        // định dạng giá tiền theo kiểu VNĐ
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvgiasanpham.setText("Giá: " + decimalFormat.format(giaTien) + "VNĐ");
        tvmota.setText(moTa);
        txtluotthich.setText(String.valueOf(yeuthich));
        //Sử dụng thư viện picasso để get dữ liệu về
        Picasso.with(getApplicationContext()).load(hinhAnh)
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(imgicon);
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add:
                // kiểm tra nếu mảng > 0 thì kiểm tra update số lượng sản phẩm
                if (sanPhamActivity.manggiohang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    String kt = spinnerkichthuoc.getSelectedItem().toString();
                    boolean ex = false;// kiểm tra ex == false
                    for (int i = 0; i < sanPhamActivity.manggiohang.size(); i++) {
                        // Kiểm tra nếu như id sản phẩm == id thì cập nhật lại số lượng sp
                        if (sanPhamActivity.manggiohang.get(i).getIdsp() == id) {
                            // Lấy số lượng cũ cộng dồn vào số lượng mới
                            sanPhamActivity.manggiohang.get(i).setSoLuong(sanPhamActivity.manggiohang.get(i).getSoLuong() + sl);
                            sanPhamActivity.manggiohang.get(i).setKichthuoc(sanPhamActivity.manggiohang.get(i).getKichthuoc().toString());
                            // Kiểm tra số lượng > 10
                            if (sanPhamActivity.manggiohang.get(i).getSoLuong() >= 10) {
                                // set số lượng = 10
                                sanPhamActivity.manggiohang.get(i).setSoLuong(10);
                            }
                            // Thay đổi giá tiền
                            sanPhamActivity.manggiohang.get(i).setGia(giaTien * sanPhamActivity.manggiohang.get(i).getSoLuong());
                            sanPhamActivity.manggiohang.get(i).setKichthuoc(sanPhamActivity.manggiohang.get(i).getKichthuoc());
                            ex = true;//nếu dk đúng thì ex = true
                        }
                    }
                    if (ex == false) { // nêu ex = false không tìm thấy id trùng thêm sản phẩm mới vào giỏ hàng
                        //L?y s? lu?ng c?a Spinner
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int Giamoi = soluong * giaTien;
                        sanPhamActivity.manggiohang.add(new gioHang(id, tenSanPham, Giamoi, hinhAnh, moTa, soluong, kt));
                    }

                    // nếu mảng nhỏ hơn 0 thì add sản phẩm mới vào giỏ hàng
                } else {
                    //L?y s? lu?ng c?a Spinner
                    String kt = spinnerkichthuoc.getSelectedItem().toString();
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * giaTien;
                    sanPhamActivity.manggiohang.add(new gioHang(id, tenSanPham, Giamoi, hinhAnh, moTa, soluong, kt));
                }
                intent = new Intent(chiTietSanPham.this, cartActivity.class);
                startActivity(intent);

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
