package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.TapLuyen;
import vn.edu.tdc.lamdep.Model.danhMucDaDep;
import vn.edu.tdc.lamdep.Model.danhMucTapLuyen;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.TapLuyen.TapBungActivity;

public class TapLuyenAdapter extends RecyclerView.Adapter<TapLuyenAdapter.ViewHolder>{
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public TapLuyenAdapter(Context context, ArrayList<danhMucTapLuyen> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }
    public TapLuyenAdapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }

    private ArrayList<danhMucTapLuyen> listFunction;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgicon;
        public TextView tvdanhmuc;
        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvDanhMucTapLuyen);
            imgicon = (ImageView) itemView.findViewById(R.id.imgDanhMucTapLuyen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TapBungActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public TapLuyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_tapluyen, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TapLuyenAdapter.ViewHolder viewHolder, int i) {
        final danhMucTapLuyen dm = listFunction.get(i);


        // Set từng giá trị lên item

        viewHolder.imgicon.setImageResource(dm.getHinhAnh());
        viewHolder.tvdanhmuc.setText(dm.getTenDanhMuc());


    }

    @Override
    public int getItemCount() {
        return listFunction.size();
    }

}
