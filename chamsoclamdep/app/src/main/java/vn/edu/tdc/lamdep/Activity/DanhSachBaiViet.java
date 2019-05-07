package vn.edu.tdc.lamdep.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.DanhSachBaiVietAdapter;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.R;

public class DanhSachBaiViet extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<DanhSachBaiViet_Model> list;
    private DanhSachBaiVietAdapter danhSachBaiVietAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaiviet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        recyclerView = (RecyclerView) findViewById(R.id.rvDsBaiViet);
        recyclerView.setLayoutManager(new GridLayoutManager(DanhSachBaiViet.this,2));
        list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("baiviet");
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    DanhSachBaiViet_Model d = dataSnapshot1.getValue(DanhSachBaiViet_Model.class);

                    if (d.getIdbaiviet().compareTo(id) == 0) {
                        list.add(d);
                    }


                }
                danhSachBaiVietAdapter = new DanhSachBaiVietAdapter(DanhSachBaiViet.this,list);
                recyclerView.setAdapter(danhSachBaiVietAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DanhSachBaiViet.this,"Đã xảy ra lỗi", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
