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


import vn.edu.tdc.lamdep.R;



import java.util.ArrayList;
import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;


public class DaDep extends Fragment {
    private DaDepAdapter daDepAdapter;
    private RecyclerView rvdanhsach;
    private ArrayList<danhMucDaDep> dsDaDep;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Da đẹp");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.dadep_layout, container, false);
        rvdanhsach = (RecyclerView) rootView.findViewById(R.id.rvdanhsach);
        dsDaDep = new ArrayList<>();
        daDepAdapter  = new DaDepAdapter(getActivity(),dsDaDep);
        rvdanhsach.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvdanhsach.setAdapter(daDepAdapter);
        importDataDaDep();
        return rootView;
    }
    public void importDataDaDep(){
        dsDaDep.add(new danhMucDaDep(1,R.drawable.iconsanpham,"Trị Mụn"));
        dsDaDep.add(new danhMucDaDep(2,R.drawable.icondadep,"Dưỡng ẩm"));
        dsDaDep.add(new danhMucDaDep(3,R.drawable.icondangdep,"Làm trắng"));
        daDepAdapter.notifyDataSetChanged();

    }

    private void setControl(){
    }
}