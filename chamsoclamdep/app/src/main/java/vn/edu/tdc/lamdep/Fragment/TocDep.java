package vn.edu.tdc.lamdep.Fragment;

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

import vn.edu.tdc.lamdep.Adapter.TocDep_Adapter;
import vn.edu.tdc.lamdep.Model.TocDep_Model;
import vn.edu.tdc.lamdep.R;

public class TocDep extends Fragment {

    private TocDep_Adapter tocDepAdapter;
    private RecyclerView recycle_view_TocDep;
    private ArrayList<TocDep_Model> dsTocDep;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Tóc đẹp");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tocdep_layout, container, false);
        recycle_view_TocDep = (RecyclerView) rootView.findViewById(R.id.recycle_view_TocDep);
        dsTocDep = new ArrayList<>();
        tocDepAdapter  = new TocDep_Adapter(getActivity(), dsTocDep);
        recycle_view_TocDep.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycle_view_TocDep.setAdapter(tocDepAdapter);
        importData();
        return rootView;
    }

    public void importData(){
        dsTocDep.add(new TocDep_Model(1,R.drawable.iconsanpham,"Chăm sóc"));
        dsTocDep.add(new TocDep_Model(2,R.drawable.icondadep,"Dưỡng tóc"));
        dsTocDep.add(new TocDep_Model(3,R.drawable.icondangdep,"Kiểu tóc"));
        dsTocDep.add(new TocDep_Model(4,R.drawable.iconsanpham,"Đánh má hồng"));
        dsTocDep.add(new TocDep_Model(5,R.drawable.icondadep,"Đánh son môi"));

        tocDepAdapter.notifyDataSetChanged();
    }

    private void setControl(){
    }

}