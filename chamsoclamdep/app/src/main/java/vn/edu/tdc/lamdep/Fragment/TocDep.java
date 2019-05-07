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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Adapter.DaDepAdapter;
import vn.edu.tdc.lamdep.Adapter.TocDep_Adapter;
import vn.edu.tdc.lamdep.Model.TocDep_Model;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.R;

public class TocDep extends Fragment {
    DatabaseReference reference;
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
        tocDepAdapter  = new TocDep_Adapter(getActivity(),dsTocDep);


        recycle_view_TocDep.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("tocdep");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    TocDep_Model d = dataSnapshot1.getValue(TocDep_Model.class);
                    dsTocDep.add(d);
                    tocDepAdapter = new TocDep_Adapter(getActivity(),dsTocDep);
                }
                recycle_view_TocDep.setAdapter(tocDepAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Lỗi database", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;

    }

}