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

import vn.edu.tdc.lamdep.Adapter.ChamSoc_Adapter;
import vn.edu.tdc.lamdep.Model.ChamSoc_Model;
import vn.edu.tdc.lamdep.R;

public class ChamSoc extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<ChamSoc_Model> listChamsoc1;
    private ChamSoc_Adapter chamsocAdapter;
    private RecyclerView rcvChamSoc1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cham_soc_layout);

        setControl();
        importData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        //Get view from layout
        Button btnAll = (Button) findViewById(R.id.btnAll);
        Button btnBiKip = (Button) findViewById(R.id.btnBiKip);
        Button btnTocDai = (Button) findViewById(R.id.btnTocDai);
        Button btnTocNhuom = (Button) findViewById(R.id.btnTocNhuom);
        Button btnTocNoi = (Button) findViewById(R.id.btnTocNoi);
        Button btnTocUon = (Button) findViewById(R.id.btnTocUon);
        Button btnTocXoan = (Button) findViewById(R.id.btnTocXoan);
        Button btnSPLQ = (Button) findViewById(R.id.btnSPLQ);
    }

    private void setControl() {
        rcvChamSoc1 = (RecyclerView) findViewById(R.id.recycle_view_ChamSoc1);
        listChamsoc1 = new ArrayList<>();
        chamsocAdapter = new ChamSoc_Adapter(ChamSoc.this, listChamsoc1);
        // Quy định chiều hiển thị của recycler view (Vertical hoăc Hozital)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChamSoc.this, LinearLayoutManager.VERTICAL, false);
        // Set chiều hiển thị cho recycler view word tođay
        // Set chiều hiển thị cho recycler view word tođay
        rcvChamSoc1.setLayoutManager(linearLayoutManager);
        // Set adapter cho recycler view word today
        rcvChamSoc1.setAdapter(chamsocAdapter);
    }

    public void importData() {
        listChamsoc1.add(new ChamSoc_Model(1, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(2, R.drawable.img_makeup, "Chăm sóc tóc cơ bản cho mái tóc khỏe đẹp"));
        listChamsoc1.add(new ChamSoc_Model(3, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(4, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(5, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(6, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(7, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(8, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));
        listChamsoc1.add(new ChamSoc_Model(9, R.drawable.img_makeup, "Chăm sóc tóc hiệu quả trong mùa du lịch"));


        chamsocAdapter.notifyDataSetChanged();
    }


    // Sự kiện nút back
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
