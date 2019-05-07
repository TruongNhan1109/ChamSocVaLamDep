package vn.edu.tdc.lamdep.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Adapter.SanPhamYeuThichAdapter;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class ProductLike extends AppCompatActivity {

    public static RecyclerView rvsanphamyeuthich;
    public static SanPhamYeuThichAdapter sanPhamYeuThichAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_like);
        setControl();
        getData();
    }

    private void setControl() {
        rvsanphamyeuthich = (RecyclerView) findViewById(R.id.rvsanphamyeuthich);
    }

    public void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(ProductLike.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        if (CheckConnect.haveNetworkConnection(ProductLike.this)) {
            ApiInterface apiInterface = APISERVISE.getServise();
            Call<List<sanPham>> listCall = apiInterface.GetDataProductLike();
            listCall.enqueue(new Callback<List<sanPham>>() {
                @Override
                public void onResponse(Call<List<sanPham>> call, Response<List<sanPham>> response) {
                    ArrayList<sanPham> mangsanphamyeuthich = (ArrayList<sanPham>) response.body();
                    sanPhamYeuThichAdapter = new SanPhamYeuThichAdapter(ProductLike.this, mangsanphamyeuthich);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
                    rvsanphamyeuthich.setLayoutManager(linearLayoutManager);
                    rvsanphamyeuthich.setAdapter(sanPhamYeuThichAdapter);
                    rvsanphamyeuthich.setAdapter(new ScaleInAnimationAdapter(sanPhamYeuThichAdapter));
                    rvsanphamyeuthich.setHasFixedSize(true);
                    sanPhamYeuThichAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
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
