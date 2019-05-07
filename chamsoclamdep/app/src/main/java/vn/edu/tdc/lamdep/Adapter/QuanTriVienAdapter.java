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

import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.R;

public class QuanTriVienAdapter extends BaseAdapter {
    ArrayList<danhMucSanPham> arrayquanlysp;
    Context context;

    public QuanTriVienAdapter(ArrayList<danhMucSanPham> arrayquanlysp, Context context) {
        this.arrayquanlysp = arrayquanlysp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayquanlysp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayquanlysp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txttenquanlysanpham;
        ImageView imgqlsp;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_quanlysanpham, null);
            viewHolder.txttenquanlysanpham = view.findViewById(R.id.tvtenquanly);
            viewHolder.imgqlsp = view.findViewById(R.id.imgquanlysanpham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        danhMucSanPham loaisp = (danhMucSanPham) getItem(position);
        viewHolder.txttenquanlysanpham.setText(loaisp.getTenLoaiSanPham());
        Picasso.with(context).load(loaisp.getHinhAnhLoai())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgqlsp);
        return view;
    }
}
