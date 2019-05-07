package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Adapter.SearchViewAdapter;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class TimKiemSanPham extends AppCompatActivity {
    public static RecyclerView rvsearchview;
    TextView txtkhongcodulieu;
    SearchView mSearchView;
    public static SearchViewAdapter searchViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        toolBar();
        setControl();
    }


    private void setControl() {
        rvsearchview = (RecyclerView) findViewById(R.id.rvsearchtensanpham);
        txtkhongcodulieu = (TextView) findViewById(R.id.txtkhongcodulieu);
    }

    private void toolBar() {
        // Khởi tạo tool bar và thay thế action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Loại bỏ tiêu đề tool bar
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Hiển thị nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem itemSearch = menu.findItem(R.id.search);
        mSearchView = (SearchView) itemSearch.getActionView();
        // set độ dài
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTenSanPham(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        return true;
    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                // Hủy màn hình
                finish();
                onBackPressed();
                return true;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void SearchTenSanPham(String query) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        if (CheckConnect.haveNetworkConnection(TimKiemSanPham.this)) {
            ApiInterface apiInterface = APISERVISE.getServise();
            Call<List<sanPham>> listCall = apiInterface.GetSearchView(query);
            listCall.enqueue(new Callback<List<sanPham>>() {
                @Override
                public void onResponse(Call<List<sanPham>> call, Response<List<sanPham>> response) {
                    ArrayList<sanPham> mangsanpham = (ArrayList<sanPham>) response.body();
                    if (mangsanpham.size() > 0) {
                        searchViewAdapter = new SearchViewAdapter(getApplicationContext(), mangsanpham);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rvsearchview.setLayoutManager(linearLayoutManager);
                        rvsearchview.setAdapter(searchViewAdapter);
                        txtkhongcodulieu.setVisibility(View.GONE);
                        rvsearchview.setVisibility(View.VISIBLE);
                        searchViewAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        txtkhongcodulieu.setVisibility(View.VISIBLE);
                        rvsearchview.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<List<sanPham>> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });

        } else {
            CheckConnect.showToast_Short(getApplicationContext(), "Lỗi mạng");
            CheckConnect.showToast_Short(getApplicationContext(), "Vui lòng kiểm tra kết nối");
            finish();
        }
    }
}
