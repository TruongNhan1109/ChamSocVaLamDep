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

public class LoaispAdapter extends BaseAdapter {
    ArrayList<danhMucSanPham> arrayListloaisp;
    Context context;

    public LoaispAdapter(ArrayList<danhMucSanPham> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txttenLoaiSanPham;
        ImageView imgLoaiSp;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.txttenLoaiSanPham = view.findViewById(R.id.textviewLoaisp);
            viewHolder.imgLoaiSp = view.findViewById(R.id.imageLoaisp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        danhMucSanPham loaisp = (danhMucSanPham) getItem(position);
        viewHolder.txttenLoaiSanPham.setText(loaisp.getTenLoaiSanPham());
        Picasso.with(context).load(loaisp.getHinhAnhLoai())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgLoaiSp);
        return view;
    }
}
