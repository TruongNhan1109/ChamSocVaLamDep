package vn.edu.tdc.lamdep.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.DanhSachBaiVietAdapter;
import vn.edu.tdc.lamdep.Adapter.ListBaiVietAdapter;
import vn.edu.tdc.lamdep.Adapter.sanPhamMoiNhatAdapter;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class listBaiViet extends AppCompatActivity {
    ArrayList<DanhSachBaiViet_Model> mangbaiviet;
    RecyclerView rvdanhsachbaiviet;
    ListBaiVietAdapter baiVietAdapter;
    private ArrayList<DanhSachBaiViet_Model> list;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_viet);
        setControl();
        toolBar();
    }

    private void setControl() {
        rvdanhsachbaiviet = (RecyclerView) findViewById(R.id.rvdanhsachbaiviet);
        rvdanhsachbaiviet.setHasFixedSize(true);
        rvdanhsachbaiviet.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("baiviet");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    DanhSachBaiViet_Model d = dataSnapshot1.getValue(DanhSachBaiViet_Model.class);

                        list.add(d);

                    baiVietAdapter = new ListBaiVietAdapter( listBaiViet.this,list);
                }
                rvdanhsachbaiviet.setAdapter(baiVietAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(listBaiViet.this,"Đã xảy ra lỗi", Toast.LENGTH_LONG).show();
            }
        });


        rvdanhsachbaiviet.setAdapter(baiVietAdapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cart, menu);

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

            case R.id.add:
                intent = new Intent(getApplicationContext(), ThemBaiViet.class);
                startActivity(intent);
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
