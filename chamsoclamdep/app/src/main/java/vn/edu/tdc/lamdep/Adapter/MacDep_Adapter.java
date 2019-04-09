package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.model.Macdep_model;

public class MacDep_Adapter  extends BaseAdapter {
    // Các thuộc tính
    private ArrayList<Macdep_model> listFunction;  // Danh sách
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    public MacDep_Adapter(ArrayList<Macdep_model> listFunction, Context context, LayoutInflater inflater) {
        this.listFunction = listFunction;
        this.context = context;
        this.inflater = inflater;
    }

    public MacDep_Adapter(FragmentActivity activity, int list_item_macdep, ArrayList<Macdep_model> arrayListMacdep) {
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        private ImageView imgicon;
        private TextView tvdanhmuc;
        private TextView tvtonghop;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate((XmlPullParser) inflater,null);
            holder.imgicon = (ImageView) view.findViewById(R.id.imgicon);
            holder.tvdanhmuc = (TextView) view.findViewById(R.id.tvdanhmuc);
            holder.tvtonghop = (TextView) view.findViewById(R.id.tvtonghop);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();

        }
        Macdep_model macdep = listFunction.get(position);
        holder.tvdanhmuc.setText(macdep.getTenDanhMuc());
        holder.tvtonghop.setText(macdep.getTongHopPhuongPhap());
        return view;
    }
}
