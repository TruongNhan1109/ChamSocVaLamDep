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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.ChamSoc;
import vn.edu.tdc.lamdep.Activity.DanhSachBaiViet;
import vn.edu.tdc.lamdep.Activity.DuongToc;
import vn.edu.tdc.lamdep.Activity.KieuToc;
import vn.edu.tdc.lamdep.Model.TocDep_Model;
import vn.edu.tdc.lamdep.R;

/**
 * Created by USER on 10/04/2019.
 */

public class TocDep_Adapter extends RecyclerView.Adapter<TocDep_Adapter.ViewHolder> {

    private static Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;
    private ArrayList<TocDep_Model> listFunction;

    // Phương thức khởi tạo
    public TocDep_Adapter(Context context, ArrayList<TocDep_Model> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }

    public TocDep_Adapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgicon;
        public TextView tvdanhmuc;

        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.txtTocDep);
            imgicon = (ImageView) itemView.findViewById(R.id.imgTocDep);


        }
    }

    @NonNull
    @Override
    public TocDep_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_tocdep, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TocDep_Adapter.ViewHolder viewHolder, int i) {
        final TocDep_Model dm = listFunction.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(dm.getImg()).into(viewHolder.imgicon);
        viewHolder.tvdanhmuc.setText(dm.getName());


        // Bắt sự kiện nhấn vào một item
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DanhSachBaiViet.class);
                intent.putExtra("id", dm.getId());
                intent.putExtra("name", dm.getName());
                Toast.makeText(context, dm.getId() + "", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listFunction.size();
    }

    public interface OnItemClickedListener {
        void onItemClick(int idFunction);

    }

    // Khai báo một biến interface
    private TocDep_Adapter.OnItemClickedListener onItemClickedListener;

    // Tạo setter cho biến interface ta vừa tạo
    public void setOnItemClickedListener(TocDep_Adapter.OnItemClickedListener onItemClickedListener) {

        this.onItemClickedListener = onItemClickedListener;
    }
}
