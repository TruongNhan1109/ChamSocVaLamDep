package vn.edu.tdc.lamdep.TapLuyen;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.TapLuyen.TapBungAdapter;
import vn.edu.tdc.lamdep.Adapter.TapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.danhMucTapBung;
import vn.edu.tdc.lamdep.R;

public class TapBungActivity extends AppCompatActivity {
    private RecyclerView rvdanhsachtapbung;
    private ArrayList<danhMucTapBung> dm;
    private TapBungAdapter tapBungAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tapbung);
        setControl();
        setEvent();
    }

    public void setControl(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartapbung);
        setSupportActionBar(toolbar);
        // Loại bỏ tiểu đề mặc định
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        rvdanhsachtapbung = (RecyclerView) findViewById(R.id.rvDsTapBung);

    }
    public void setEvent(){

        dm = new ArrayList<>();
        tapBungAdapter  = new TapBungAdapter(this,dm);
        rvdanhsachtapbung.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvdanhsachtapbung.setAdapter(tapBungAdapter);
        importDataTapBung();
    }

    public void importDataTapBung(){
        dm.add(new danhMucTapBung(1,R.drawable.iconsanpham,"Tập bụng"));
        dm.add(new danhMucTapBung(2,R.drawable.icondadep,"Tập mông"));
        dm.add(new danhMucTapBung(3,R.drawable.icondangdep,"Giảm cân"));
        tapBungAdapter.notifyDataSetChanged();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // Hủy màn hình
                finish();
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
