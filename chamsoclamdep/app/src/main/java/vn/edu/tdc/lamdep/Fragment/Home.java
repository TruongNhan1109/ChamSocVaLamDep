package vn.edu.tdc.lamdep.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.DangDepAdapter;
import vn.edu.tdc.lamdep.Adapter.HomeAdapter;
import vn.edu.tdc.lamdep.Adapter.TapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.danhMucHome;
import vn.edu.tdc.lamdep.R;

public class Home extends Fragment {
    private ArrayList<danhMucHome> ds;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Màn hình chủ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.home_layout, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvDsHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ds = new ArrayList<>();

        homeAdapter = new HomeAdapter(getActivity(),ds);
        recyclerView.setAdapter(homeAdapter);
        importData();
        return  rootView;

    }
    public void importData(){
        ds.add(new danhMucHome(1,R.drawable.iconsanpham,"Sản phẩm","Tổng hợp một số sản phẩm tốt nhất"));
        ds.add(new danhMucHome(2,R.drawable.icondadep,"Da đẹp","Tổng hợp phương pháp chăm sóc da tốt nhất"));
        ds.add(new danhMucHome(3,R.drawable.icontrangdiem,"Trang điểm","Trợ giúp trong việc trang điểm"));
        ds.add(new danhMucHome(4,R.drawable.icontocdep,"Tóc đẹp","Tổng hợp phương pháp chăm sóc và mẫu tóc"));
        ds.add(new danhMucHome(5,R.drawable.iconmacdep,"Mặc đẹp","Nổi bậc với phong cách mới"));
        ds.add(new danhMucHome(6,R.drawable.icondangdep,"Dáng đẹp","Chế độ ăn để có một vóc dáng đẹp"));
        ds.add(new danhMucHome(7,R.drawable.icontapluyen,"Tập luyện","Giảm cân - Bài tập tổng hợp"));
        homeAdapter.notifyDataSetChanged();

    }
}