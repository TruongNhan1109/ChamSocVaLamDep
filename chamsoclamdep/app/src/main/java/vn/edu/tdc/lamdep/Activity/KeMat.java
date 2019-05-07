package vn.edu.tdc.lamdep.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.KeMat_Adapter;
import vn.edu.tdc.lamdep.Model.KeMat_Model;
import vn.edu.tdc.lamdep.R;

public class KeMat extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<KeMat_Model> listKeMat;
    private KeMat_Adapter keMatAdapter;
    private RecyclerView rcvKeMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ke_mat_layout);

        setControl();
        importData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setControl() {
        rcvKeMat = (RecyclerView) findViewById(R.id.recycle_view_KeMat);
        listKeMat = new ArrayList<>();
        keMatAdapter = new KeMat_Adapter(KeMat.this, listKeMat);
        // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KeMat.this, LinearLayoutManager.VERTICAL, false);
        // Set chiều hiển thị cho recycler view word tođay
        // Set chiều hiển thị cho recycler view word tođay
        rcvKeMat.setLayoutManager(linearLayoutManager);
        // Set adapter cho recycler view word today
        rcvKeMat.setAdapter(keMatAdapter);
    }

    public void importData() {
        listKeMat.add(new KeMat_Model(1, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(2, R.drawable.img_makeup, "Chăm sóc tóc cơ bản cho mái tóc khỏe đẹp"));
        listKeMat.add(new KeMat_Model(3, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(4, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(5, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(6, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(7, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(8, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeMat.add(new KeMat_Model(9, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));

        keMatAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
