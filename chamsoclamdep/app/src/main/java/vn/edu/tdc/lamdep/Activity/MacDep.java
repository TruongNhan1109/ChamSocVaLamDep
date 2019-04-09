package vn.edu.tdc.lamdep.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import vn.edu.tdc.lamdep.R;


public class MacDep extends Fragment {
    // Các thuộc tính
//    private ArrayList<Macdep_model> arrayListMacdep;
//    private MacDep_Adapter macdepAdapter;

    String[] arrayDSMacDep = {"Outfit thời trang", "Bí quyết mặc đẹp", "Xu hướng thời trang"};
    ArrayAdapter adapter;
    ListView listView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Mặc đẹp");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        arrayListMacdep = new ArrayList<>();
//
//        macdepAdapter = new MacDep_Adapter(getActivity(), R.layout.list_item_macdep, arrayListMacdep);
//        setListAdapter();
        View view = inflater.inflate(R.layout.macdep_layout, container, false);
        listView = (ListView)  view.findViewById(R.id.listviewmacdep);
        adapter =  new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayDSMacDep);
        listView.setAdapter(adapter);

        return  view;
    }
}