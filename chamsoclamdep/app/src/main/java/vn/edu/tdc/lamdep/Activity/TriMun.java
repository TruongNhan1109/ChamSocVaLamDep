package vn.edu.tdc.lamdep.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import vn.edu.tdc.lamdep.R;

public class TriMun extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trimun_layout);
        setControl();
    }
    public void setControl(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartrimun);
        setSupportActionBar(toolbar);
        // Loại bỏ tiểu đề mặc định
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("Trị Mụn");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return super.onCreateOptionsMenu(menu);
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
