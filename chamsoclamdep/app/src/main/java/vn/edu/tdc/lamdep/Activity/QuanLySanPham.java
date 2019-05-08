package vn.edu.tdc.lamdep.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.LoaispAdapter;
import vn.edu.tdc.lamdep.Adapter.QuanTriVienAdapter;
import vn.edu.tdc.lamdep.Adapter.SanPhamFlashSaleAdapter;
import vn.edu.tdc.lamdep.Adapter.sanPhamMoiNhatAdapter;
import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class QuanLySanPham extends AppCompatActivity {

    //Khai báo thuộc tính
    ListView listViewquanlysanpham;
    ArrayList<danhMucSanPham> mangquanlysanpham;
    QuanTriVienAdapter quanTriVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        toolBar();
        setControl();
        setInvent();

    }

    private void setInvent() {
        // Sự kiện chọn vào listview item di chuyển đến màn hình khác
        listViewquanlysanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        break;
                    case 1:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            intent = new Intent(getApplicationContext(), DanhSachSanPham.class);
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        break;
                    case 2:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            intent = new Intent(getApplicationContext(), QuanLyDonHang.class);
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        break;
                    case 3:
                        if (CheckConnect.haveNetworkConnection(getApplicationContext())) {
                            intent = new Intent(getApplicationContext(), listBaiViet.class);
                            startActivity(intent);
                        } else {
                            CheckConnect.showToast_Short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        break;
                }
            }
        });
    }

    //Khai báo gọi lại tool Bar và NavigationView
    private void toolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý sản phẩm");
        setSupportActionBar(toolbar);
        // Loại bỏ tiêu đề tool bar
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Hiển thị nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setControl() {
        listViewquanlysanpham = (ListView) findViewById(R.id.lvlquanlysanpham);
        mangquanlysanpham = new ArrayList<>();
        mangquanlysanpham.add(0, new danhMucSanPham(0, "Quản lý người dùng", "http://hvl.com.vn/UploadFile/images/iSM%20BASIC/quan-ly-nguoi-dung.png"));
        mangquanlysanpham.add(1, new danhMucSanPham(1, "Quản lý sản phẩm", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTyr6QDWOIanYyLDiFgSiliaNQXET8dZiJcJzMiggSplbwIYxF"));
        mangquanlysanpham.add(2, new danhMucSanPham(2, "Quản lý đơn hàng", "https://vdo.vn/wp-content/uploads/2015/08/quy-trinh-mua-hang.png"));
        mangquanlysanpham.add(3, new danhMucSanPham(3, "Quản lý bài viết", "https://websitechuan.com/public/manipulate/1200x630/article-images/1473223738-hinh-dai-dien-8-1.jpg"));
        quanTriVienAdapter = new QuanTriVienAdapter(mangquanlysanpham,getApplicationContext());
        listViewquanlysanpham.setAdapter(quanTriVienAdapter);
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
