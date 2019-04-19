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
import vn.edu.tdc.lamdep.Model.SanPhamBanChay;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class SanPhamBanChayAdapter extends RecyclerView.Adapter<SanPhamBanChayAdapter.ITemHolder> {
    Context context;
    ArrayList<SanPhamBanChay> arraysanpham;

    public SanPhamBanChayAdapter(Context context, ArrayList<SanPhamBanChay> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ITemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_product_banchay, null);
        ITemHolder iTemHolder = new ITemHolder(v);
        return iTemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ITemHolder iTemHolder, int i) {
        SanPhamBanChay sanPhamBanChay = arraysanpham.get(i);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        iTemHolder.txtgiasanpham.setText(decimalFormat.format(sanPhamBanChay.getGiasanpham()) + ". VNĐ");
        iTemHolder.txtsoluongsanpham.setText(String.valueOf(sanPhamBanChay.getSoluongsanpham()));
        Picasso.with(context).load(sanPhamBanChay.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(iTemHolder.imghinhsanpham);

    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ITemHolder extends RecyclerView.ViewHolder {
        public ImageView imghinhsanpham;
        public TextView txtgiasanpham;
        public TextView txtsoluongsanpham;

        public ITemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham = itemView.findViewById(R.id.anhSanPham);
            txtgiasanpham = itemView.findViewById(R.id.giaSanPham);
            txtsoluongsanpham = itemView.findViewById(R.id.txtsanphambanchay);
        }
    }
}
