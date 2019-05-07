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
import android.widget.ImageView;
import android.widget.TextView;
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
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.R;

public class BaiViet extends AppCompatActivity {
    private DatabaseReference reference;
    private TextView tieudebaiviet;
    private TextView mota;
    private TextView chuanbi;
    private TextView thuchien;
    private ImageView imageback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_baiviet);
        tieudebaiviet = (TextView) findViewById(R.id.tieudebaivet);
        mota = (TextView) findViewById(R.id.motabaiviet);
        chuanbi = (TextView) findViewById(R.id.chuanbi);
        thuchien = (TextView) findViewById(R.id.thuchien);
        imageback = (ImageView)findViewById(R.id.imageBackground);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        tieudebaiviet.setText(intent.getStringExtra("tieude"));
        mota.setText(intent.getStringExtra("mota"));
        chuanbi.setText(intent.getStringExtra("chuanbi"));
        thuchien.setText(intent.getStringExtra("thuchien"));
        Picasso.with(this).load(intent.getStringExtra("img")).into(this.imageback);
        getSupportActionBar().setTitle(intent.getStringExtra("tieude"));


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
