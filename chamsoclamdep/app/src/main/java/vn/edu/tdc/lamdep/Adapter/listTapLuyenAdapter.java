package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifTextView;
import vn.edu.tdc.lamdep.Activity.BaiViet;
import vn.edu.tdc.lamdep.Activity.BatDau;
import vn.edu.tdc.lamdep.Activity.DanhSachBaiViet;
import vn.edu.tdc.lamdep.Activity.listTapLuyen;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.listTapLuyenModel;
import vn.edu.tdc.lamdep.R;

public class listTapLuyenAdapter extends RecyclerView.Adapter<listTapLuyenAdapter.ViewHolder>{
    public DatabaseReference reference;
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public listTapLuyenAdapter(Context context, ArrayList<listTapLuyenModel> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }
    public listTapLuyenAdapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }

    private ArrayList<listTapLuyenModel> listFunction;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgicon;
        public TextView tvdanhmuc;
        public TextView count;
        public Button button;
        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvdanhmuc);
            count = (TextView) itemView.findViewById(R.id.tvcount);
            imgicon = (ImageView) itemView.findViewById(R.id.imgicon);
            button = (Button)itemView.findViewById(R.id.btnBatDau);
        }
    }

    @NonNull
    @Override
    public listTapLuyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_tapluyen, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final listTapLuyenAdapter.ViewHolder viewHolder, int i) {
        final listTapLuyenModel dm = listFunction.get(i);


        // Set từng giá trị lên item

        Glide.with(context).load(dm.getImgtapluyen()).into( viewHolder.imgicon);
        viewHolder.tvdanhmuc.setText(dm.getNametapluyen());
        viewHolder.count.setText(dm.getCounttapluyen());


        // Bắt sự kiện nhấn vào một item
//        viewHolder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent  = new Intent(v.getContext(), BatDau.class);
////                intent.putExtra("batdauname", dm.getNametapluyen());
////                intent.putExtra("batdaucount", dm.getCounttapluyen());
////                intent.putExtra("batdauimg", dm.getImgtapluyen());
//                context.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listFunction.size();
    }

    public interface OnItemClickedListener {
        void onItemClick(int idFunction);

    }
}
