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

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.chiTietSanPham;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.Model.SanPhamBanChay;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class ListBaiVietAdapter extends RecyclerView.Adapter<ListBaiVietAdapter.ITemHolder> {
    Context context;
    ArrayList<DanhSachBaiViet_Model> arraybaiviet;

    public ListBaiVietAdapter(Context context, ArrayList<DanhSachBaiViet_Model> arraybaiviet) {
        this.context = context;
        this.arraybaiviet = arraybaiviet;
    }

    @NonNull
    @Override
    public ITemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_baiviet, null);
        ITemHolder iTemHolder = new ITemHolder(v);
        return iTemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ITemHolder iTemHolder, int i) {
        DanhSachBaiViet_Model ds = arraybaiviet.get(i);
        iTemHolder.txttenbaiviet.setText(ds.getTenbaiviet());
        Picasso.with(context).load(ds.getImgbaiviet())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(iTemHolder.imgbaiviet);
        iTemHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckConnect.showToast_Short(context,"Delete");
            }
        });

    }

    @Override
    public int getItemCount() {
        return arraybaiviet.size();
    }

    public class ITemHolder extends RecyclerView.ViewHolder {
        public ImageView imgbaiviet;
        public TextView txttenbaiviet;
        public ImageView imgdelete, imgupdate;

        public ITemHolder(@NonNull View itemView) {
            super(itemView);
            imgupdate = itemView.findViewById(R.id.imgupdate);
            imgdelete = itemView.findViewById(R.id.imgdelete);
            imgbaiviet = itemView.findViewById(R.id.imgbaiviet);
            txttenbaiviet = itemView.findViewById(R.id.tvtenbaiviet);
        }
    }
}
