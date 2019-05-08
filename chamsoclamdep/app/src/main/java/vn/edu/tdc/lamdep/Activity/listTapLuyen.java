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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.DanhSachBaiVietAdapter;
import vn.edu.tdc.lamdep.Adapter.listTapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.Model.listTapLuyenModel;
import vn.edu.tdc.lamdep.R;

public class listTapLuyen extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<listTapLuyenModel> list;
    private listTapLuyenAdapter listTapLuyenAdapter;
    private ImageView imageView;
    private Button button;
    private listTapLuyenModel listTapLuyenModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtapluyen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        button = (Button)findViewById(R.id.btnBatDau);
        setEvent();
        imageView = (ImageView)findViewById(R.id.imageBackground);
        recyclerView = (RecyclerView) findViewById(R.id.rvListTapLuyen);
        recyclerView.setLayoutManager(new LinearLayoutManager(listTapLuyen.this));

        list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("listtapluyen");
        Intent intent = getIntent();
        final String id = intent.getStringExtra("idlisttapluyen");
        Picasso.with(this).load(intent.getStringExtra("imgtapluyen")).into(this.imageView);
        getSupportActionBar().setTitle(intent.getStringExtra("nametapluyen"));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    listTapLuyenModel d = dataSnapshot1.getValue(listTapLuyenModel.class);
                    if (d.getIdtapluyen().compareTo(id) == 0) {
                        list.add(d);
                    }
                    listTapLuyenAdapter = new listTapLuyenAdapter(listTapLuyen.this,list);
                }
                recyclerView.setAdapter(listTapLuyenAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(listTapLuyen.this,"Đã xảy ra lỗi", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setEvent(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(listTapLuyen.this, BatDau.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
