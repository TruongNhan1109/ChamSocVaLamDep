package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.tdc.lamdep.Model.ChiTietDonHang;
import vn.edu.tdc.lamdep.Model.DonHang;
import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.R;

public class ChiTietDonHangAdapter extends BaseAdapter {
    public ChiTietDonHangAdapter(ArrayList<ChiTietDonHang> arraychitietdonhang, Context context) {
        this.arraychitietdonhang = arraychitietdonhang;
        this.context = context;
    }

    ArrayList<ChiTietDonHang> arraychitietdonhang;
    Context context;

    @Override
    public int getCount() {
        return arraychitietdonhang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraychitietdonhang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView tvmadonhang, tvmasanpham, tvtensanpham, tvgiasanpham, tvmota, tvsoluong, tvkichthuoc;
        ImageView imgsanpham;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_chitietdonhang, null);
            viewHolder.tvmasanpham = view.findViewById(R.id.tvmasanpham);
            viewHolder.tvtensanpham = view.findViewById(R.id.tvtensanpham);
            viewHolder.tvgiasanpham = view.findViewById(R.id.tvgiasanpham);
            viewHolder.tvmota = view.findViewById(R.id.tvmota);
            viewHolder.imgsanpham = view.findViewById(R.id.imgsanpham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ChiTietDonHang ct = (ChiTietDonHang) getItem(position);
        viewHolder.tvmadonhang.setText(ct.getMadonhang());
        viewHolder.tvmasanpham.setText(ct.getMasanpham());
        viewHolder.tvtensanpham.setText(ct.getTensanpham());
        viewHolder.tvtensanpham.setMaxLines(2);
        viewHolder.tvtensanpham.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvgiasanpham.setText("Giá: " + decimalFormat.format(ct.getGiasanpham()) + ". VNĐ");
        viewHolder.tvmota.setMaxLines(2);
        viewHolder.tvmota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvmota.setText(ct.getMota());
        Picasso.with(context).load(ct.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsanpham);
        return view;
    }
}
