package vn.edu.tdc.lamdep.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.TapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.Model.danhMucTapLuyen;
import vn.edu.tdc.lamdep.R;

public class TapLuyen extends Fragment {
    private TapLuyenAdapter tapLuyenAdapter;
    private RecyclerView rvdanhsach;
    private ArrayList<danhMucTapLuyen> dsTapLuyen;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Tập luyện");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tapluyen_layout, container, false);
        rvdanhsach = (RecyclerView) rootView.findViewById(R.id.rvdanhsachTapLuyen);
        dsTapLuyen = new ArrayList<>();
        tapLuyenAdapter  = new TapLuyenAdapter(getActivity(),dsTapLuyen);
        rvdanhsach.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvdanhsach.setAdapter(tapLuyenAdapter);
        importDataTapLuyen();
        return rootView;
    }
    public void importDataTapLuyen(){
        dsTapLuyen.add(new danhMucTapLuyen(1,R.drawable.iconsanpham,"Tập bụng"));
        dsTapLuyen.add(new danhMucTapLuyen(2,R.drawable.icondadep,"Tập mông"));
        dsTapLuyen.add(new danhMucTapLuyen(3,R.drawable.icondangdep,"Giảm cân"));
        tapLuyenAdapter.notifyDataSetChanged();

    }

    private void setControl(){
    }
}