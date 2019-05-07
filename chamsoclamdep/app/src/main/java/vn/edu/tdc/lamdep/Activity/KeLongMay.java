package vn.edu.tdc.lamdep.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.KeLongMay_Adapter;
import vn.edu.tdc.lamdep.Model.KeLongMay_Model;
import vn.edu.tdc.lamdep.R;

public class KeLongMay extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<KeLongMay_Model> listKeLongMay;
    private KeLongMay_Adapter keLongMayAdapter;
    private RecyclerView rcvKeLongMay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ke_long_may_layout);
        setControl();
        importData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setControl() {
        rcvKeLongMay = (RecyclerView) findViewById(R.id.recycle_view_KeLongMay);
        listKeLongMay = new ArrayList<>();
        keLongMayAdapter = new KeLongMay_Adapter(KeLongMay.this, listKeLongMay);
        // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KeLongMay.this, LinearLayoutManager.VERTICAL, false);
        // Set chiều hiển thị cho recycler view word tođay
        // Set chiều hiển thị cho recycler view word tođay
        rcvKeLongMay.setLayoutManager(linearLayoutManager);
        // Set adapter cho recycler view word today
        rcvKeLongMay.setAdapter(keLongMayAdapter);
    }

    public void importData() {
        listKeLongMay.add(new KeLongMay_Model(1, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(2, R.drawable.img_makeup, "Chăm sóc tóc cơ bản cho mái tóc khỏe đẹp"));
        listKeLongMay.add(new KeLongMay_Model(3, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(4, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(5, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(6, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(7, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(8, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKeLongMay.add(new KeLongMay_Model(9, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));

        keLongMayAdapter.notifyDataSetChanged();
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
