package vn.edu.tdc.lamdep.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.KieuToc_Adapter;
import vn.edu.tdc.lamdep.Model.KieuToc_Model;
import vn.edu.tdc.lamdep.R;

public class KieuToc extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<KieuToc_Model> listKieuToc;
    private KieuToc_Adapter kieutocAdapter;
    private RecyclerView rcvKieuToc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kieu_toc_layout);

        setControl();
        importData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Get view from layout
        Button btnAll = (Button) findViewById(R.id.btnAll);
        Button btnTocDai = (Button) findViewById(R.id.btnTocDai2);
        Button btnTocNgan = (Button) findViewById(R.id.btnTocNgan);
        Button btnSPLQ = (Button) findViewById(R.id.btnSPLQ);
    }

    private void setControl() {
        rcvKieuToc = (RecyclerView) findViewById(R.id.recycle_view_KieuToc);
        listKieuToc = new ArrayList<>();
        kieutocAdapter = new KieuToc_Adapter(KieuToc.this, listKieuToc);
        // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KieuToc.this, LinearLayoutManager.VERTICAL, false);
        // Set chiều hiển thị cho recycler view word tođay
        // Set chiều hiển thị cho recycler view word tođay
        rcvKieuToc.setLayoutManager(linearLayoutManager);
        // Set adapter cho recycler view word today
        rcvKieuToc.setAdapter(kieutocAdapter);
    }

    public void importData() {
        listKieuToc.add(new KieuToc_Model(1, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(2, R.drawable.img_makeup, "Chăm sóc tóc cơ bản cho mái tóc khỏe đẹp"));
        listKieuToc.add(new KieuToc_Model(3, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(4, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(5, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(6, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(7, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(8, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listKieuToc.add(new KieuToc_Model(9, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));


        kieutocAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
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
