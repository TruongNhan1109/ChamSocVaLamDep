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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.TapLuyenAdapter;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.R;


public class TapLuyen extends Fragment {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<danhMucDaDep> list;
    private TapLuyenAdapter tapLuyenAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Tập luyện");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tapluyen_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvDsTapLuyen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("luyentap");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    danhMucDaDep d = dataSnapshot1.getValue(danhMucDaDep.class);
                    list.add(d);
                    tapLuyenAdapter = new TapLuyenAdapter(getActivity(),list);
                }
                recyclerView.setAdapter(tapLuyenAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Đã xảy ra lỗi", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }


}