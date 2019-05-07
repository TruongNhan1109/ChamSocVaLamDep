package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.ChamSoc;
import vn.edu.tdc.lamdep.Activity.DanhSachBaiViet;
import vn.edu.tdc.lamdep.Activity.DuongToc;
import vn.edu.tdc.lamdep.Activity.KieuToc;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.R;

public class TapLuyenAdapter extends RecyclerView.Adapter<TapLuyenAdapter.ViewHolder>{
    public DatabaseReference reference;
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public TapLuyenAdapter(Context context, ArrayList<danhMucDaDep> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }
    public TapLuyenAdapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }

    private ArrayList<danhMucDaDep> listFunction;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgicon;
        public TextView tvdanhmuc;
        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvdanhmuc);
            imgicon = (ImageView) itemView.findViewById(R.id.imgicon);

        }
    }

    @NonNull
    @Override
    public TapLuyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_macdep, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TapLuyenAdapter.ViewHolder viewHolder, final int i) {
        final danhMucDaDep dm = listFunction.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(dm.getImg()).into(viewHolder.imgicon);
        viewHolder.tvdanhmuc.setText(dm.getName());

        // Bắt sự kiện nhấn vào một item
    }

    @Override
    public int getItemCount() {
        return listFunction.size();
    }

    public interface OnItemClickedListener {
        void onItemClick(View view,int idFunction);

    }
    private DaDepAdapter.OnItemClickedListener mClickedListener;
    public void setOnclickListener(DaDepAdapter.OnItemClickedListener onclickListener){
        mClickedListener = onclickListener;
    }
}
