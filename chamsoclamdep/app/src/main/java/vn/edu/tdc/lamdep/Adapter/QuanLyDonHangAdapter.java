package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Model.DonHang;
import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.R;

public class QuanLyDonHangAdapter extends BaseAdapter {
    ArrayList<DonHang> arrayquanlydonhang;
    Context context;

    public QuanLyDonHangAdapter(ArrayList<DonHang> arrayquanlydonhang, Context context) {
        this.arrayquanlydonhang = arrayquanlydonhang;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayquanlydonhang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayquanlydonhang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txttenkhachhang, txtsodienthoai, txtemail, txtdiachi;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_donhang, null);
            viewHolder.txttenkhachhang = view.findViewById(R.id.tvtenkhachhang);
            viewHolder.txtsodienthoai = view.findViewById(R.id.tvsodienthoai);
            viewHolder.txtemail = view.findViewById(R.id.tvemail);
            viewHolder.txtdiachi = view.findViewById(R.id.tvdiachi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DonHang donHang = (DonHang) getItem(position);
        viewHolder.txttenkhachhang.setText(donHang.getTenkhachhang());
        viewHolder.txtsodienthoai.setText(donHang.getSodienthoai());
        viewHolder.txtemail.setText(donHang.getEmail());
        viewHolder.txtdiachi.setText(donHang.getDiachi());
        return view;
    }
}
