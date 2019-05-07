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
import android.widget.Button;
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

public class BatDau extends AppCompatActivity {
    private DatabaseReference reference;
    private TextView batdaucount;
    private TextView batdauname;
    private ImageView imgbatdau;
    private Button btnhoanthanh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batdauscreen);
        batdaucount = (TextView) findViewById(R.id.batdaucount);
        batdauname = (TextView) findViewById(R.id.batdauname);
        imgbatdau = (ImageView)findViewById(R.id.imgbatdau);
        btnhoanthanh = (Button) findViewById(R.id.btnhoanthanh);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        Intent intent = getIntent();
//        batdauname.setText(intent.getStringExtra("batdauname"));
//        batdaucount.setText(intent.getStringExtra("batdaucount"));
//        Picasso.with(this).load(intent.getStringExtra("batdauimg")).into(this.imgbatdau);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
