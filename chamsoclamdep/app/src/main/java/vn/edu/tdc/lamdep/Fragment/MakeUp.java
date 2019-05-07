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

import vn.edu.tdc.lamdep.Adapter.MakeUp_Adapter;
import vn.edu.tdc.lamdep.Model.Make_Up_Model;
import vn.edu.tdc.lamdep.R;

public class MakeUp extends Fragment {

    private DatabaseReference reference;
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
        recycle_view_MakeUp.setLayoutManager(new LinearLayoutManager(getActivity()));

        dsMakeUp = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("makeup");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Make_Up_Model d = dataSnapshot1.getValue(Make_Up_Model.class);
                    dsMakeUp.add(d);
                    makeUpAdapter  = new MakeUp_Adapter(getActivity(), dsMakeUp);
                    recycle_view_MakeUp.setAdapter(makeUpAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Có lỗi!",Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

}