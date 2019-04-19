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
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class SanPhamFlashSaleAdapter extends RecyclerView.Adapter<SanPhamFlashSaleAdapter.ITemHolder> {
    Context context;
    ArrayList<sanPham> arraysanpham;

    public SanPhamFlashSaleAdapter(Context context, ArrayList<sanPham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ITemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_sanphamflashsale, null);
        ITemHolder iTemHolder = new ITemHolder(v);

        return iTemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ITemHolder iTemHolder, int i) {
        sanPham sanpham = arraysanpham.get(i);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        iTemHolder.txtgiasanpham.setText(decimalFormat.format(sanpham.getGiasanpham()) + ". VNĐ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
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

        public ITemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham = itemView.findViewById(R.id.anhSanPham);
            txtgiasanpham = itemView.findViewById(R.id.giaSanPham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, chiTietSanPham.class);
                    intent.putExtra("thongtinsanpham", arraysanpham.get(getAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnect.showToast_Short(context, arraysanpham.get(getAdapterPosition()).getTensanpham());
                    context.startActivity(intent);

                }
            });
        }
    }
}
