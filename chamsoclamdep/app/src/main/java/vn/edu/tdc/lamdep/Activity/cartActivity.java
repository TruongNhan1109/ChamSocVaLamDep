package vn.edu.tdc.lamdep.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.tdc.lamdep.Adapter.gioHangAdapter;
import vn.edu.tdc.lamdep.Model.gioHang;
import vn.edu.tdc.lamdep.R;

public class cartActivity extends AppCompatActivity {

    // Khai báo các thuộc tính
    static ListView lvlgiohang;
    static gioHangAdapter hangAdapter;
    ArrayList<gioHang> manggiohang = new ArrayList<>();

    static CircleImageView imgthongbao;
    static TextView tvthongbao, tonggiatien;
    static TextView tvtongtien;
    static Button btnthemhang, btntieptucmua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setControl();
        checkData();
        setInvent();
        checkTongTien();
        notifileDatachange();
        toolBar();
    }

    public static void notifileDatachange() {
        hangAdapter.notifyDataSetChanged();
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

    //Kiểm tra nếu giỏ hàng rỗng thì thông báo giỏ hàng đang trống
    // Ngược lại hiển thị thông tin sản phẩm
    public static void checkData() {
        if (sanPhamActivity.manggiohang.size() <= 0) {
            hangAdapter.notifyDataSetChanged();
            imgthongbao.setVisibility(View.VISIBLE);
            tvtongtien.setVisibility(View.INVISIBLE);
            tonggiatien.setVisibility(View.INVISIBLE);
            tvthongbao.setVisibility(View.VISIBLE);
            lvlgiohang.setVisibility(View.INVISIBLE);
            btntieptucmua.setVisibility(View.INVISIBLE);
            btnthemhang.setVisibility(View.INVISIBLE);
        } else {
            hangAdapter.notifyDataSetChanged();
            tvtongtien.setVisibility(View.VISIBLE);
            imgthongbao.setVisibility(View.INVISIBLE);
            tvthongbao.setVisibility(View.INVISIBLE);
            tonggiatien.setVisibility(View.VISIBLE);
            lvlgiohang.setVisibility(View.VISIBLE);
            btntieptucmua.setVisibility(View.VISIBLE);
            btnthemhang.setVisibility(View.VISIBLE);
        }
    }


    // Check lại giá tiền
    private void setInvent() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cartActivity.this, sanPhamActivity.class);
                startActivity(intent);
            }
        });
        btnthemhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sanPhamActivity.manggiohang.size() > 0)
                {
                    Intent intent = new Intent(cartActivity.this, ThongTinKhachHang.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Giỏ hàng của bạn không có sản phẩm", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Set lại tổng tiền
    public static void checkTongTien() {
        long tongtien = 0;
        for (int i = 0; i < sanPhamActivity.manggiohang.size(); i++) {
            tongtien += sanPhamActivity.manggiohang.get(i).getGia();
        }
        //Dịnh dạng trả về giá tiền dưới dạng Đ
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvtongtien.setText(decimalFormat.format(tongtien) + ". VNĐ");
    }

    //Ánh xạ các thuộc tính để sử dụng bên xml
    private void setControl() {
        imgthongbao = (CircleImageView) findViewById(R.id.imgthongbao);
        tvthongbao = (TextView) findViewById(R.id.tvthongbao);
        tonggiatien = (TextView) findViewById(R.id.tonggiatien);
        tvtongtien = (TextView) findViewById(R.id.tvtongtien);
        btnthemhang = (Button) findViewById(R.id.btnthemhang);
        btntieptucmua = (Button) findViewById(R.id.btntieptucmua);
        lvlgiohang = (ListView) findViewById(R.id.lvlgiohang);
        manggiohang = sanPhamActivity.manggiohang;
        hangAdapter = new gioHangAdapter(cartActivity.this, sanPhamActivity.manggiohang);
        lvlgiohang.setAdapter(hangAdapter);

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
