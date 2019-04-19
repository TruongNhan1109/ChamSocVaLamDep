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

import vn.edu.tdc.lamdep.Adapter.DuongToc_Adapter;
import vn.edu.tdc.lamdep.Model.DuongToc_Model;
import vn.edu.tdc.lamdep.R;

public class DuongToc extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<DuongToc_Model> listDuongToc;
    private DuongToc_Adapter duongtocAdapter;
    private RecyclerView rcvDuongToc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duong_toc_layout);

        setControl();
        importData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Get view from layout
        Button btnAll = (Button) findViewById(R.id.btnAll);
        Button btnBiKipDT = (Button) findViewById(R.id.btnBiKipDT);
        Button btnDuongTocTN = (Button) findViewById(R.id.btnDuongTocTN);
        Button btnSPLQ = (Button) findViewById(R.id.btnSPLQ);
    }

    private void setControl() {
        rcvDuongToc = (RecyclerView) findViewById(R.id.recycle_view_DuongToc);
        listDuongToc = new ArrayList<>();
        duongtocAdapter = new DuongToc_Adapter(DuongToc.this, listDuongToc);
        // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DuongToc.this, LinearLayoutManager.VERTICAL, false);
        // Set chiều hiển thị cho recycler view word tođay
        // Set chiều hiển thị cho recycler view word tođay
        rcvDuongToc.setLayoutManager(linearLayoutManager);
        // Set adapter cho recycler view word today
        rcvDuongToc.setAdapter(duongtocAdapter);
    }

    public void importData() {
        listDuongToc.add(new DuongToc_Model(1, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(2, R.drawable.img_makeup, "Chăm sóc tóc cơ bản cho mái tóc khỏe đẹp"));
        listDuongToc.add(new DuongToc_Model(3, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(4, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(5, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(6, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(7, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(8, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listDuongToc.add(new DuongToc_Model(9, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));


        duongtocAdapter.notifyDataSetChanged();
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
