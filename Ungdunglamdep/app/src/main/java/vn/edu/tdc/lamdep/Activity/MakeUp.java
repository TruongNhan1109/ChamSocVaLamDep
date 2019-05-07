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

import vn.edu.tdc.lamdep.Adapter.MakeUp_Adapter;
import vn.edu.tdc.lamdep.Model.Make_Up_Model;
import vn.edu.tdc.lamdep.R;

public class MakeUp extends Fragment {

    private MakeUp_Adapter makeUpAdapter;
    private RecyclerView recycle_view_MakeUp;
    private ArrayList<Make_Up_Model> dsMakeUp;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Make up");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.makeup_layout, container, false);
        recycle_view_MakeUp = (RecyclerView) rootView.findViewById(R.id.recycle_view_MakeUp);
        dsMakeUp = new ArrayList<>();
        makeUpAdapter  = new MakeUp_Adapter(getActivity(),dsMakeUp);
        recycle_view_MakeUp.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycle_view_MakeUp.setAdapter(makeUpAdapter);
        importData();
        return rootView;
    }

    public void importData(){
        dsMakeUp.add(new Make_Up_Model(1,R.drawable.iconsanpham,"Kẻ lông mày"));
        dsMakeUp.add(new Make_Up_Model(2,R.drawable.icondadep,"Kẻ mắt"));
        dsMakeUp.add(new Make_Up_Model(3,R.drawable.icondangdep,"Tạo khối cho mắt"));
        dsMakeUp.add(new Make_Up_Model(4,R.drawable.iconsanpham,"Đánh má hồng"));
        dsMakeUp.add(new Make_Up_Model(5,R.drawable.icondadep,"Đánh son môi"));

        makeUpAdapter.notifyDataSetChanged();
    }

    private void setControl(){
    }
}