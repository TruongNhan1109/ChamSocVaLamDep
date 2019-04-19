package vn.edu.tdc.lamdep.TapLuyen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import vn.edu.tdc.lamdep.Model.danhMucTapBung;
import vn.edu.tdc.lamdep.R;

public class TapBungAdapter extends RecyclerView.Adapter<TapBungAdapter.ViewHolder>{
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public TapBungAdapter(Context context, ArrayList<danhMucTapBung> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }
    public TapBungAdapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }

    private ArrayList<danhMucTapBung> listFunction;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgicon;
        public TextView tvdanhmuc;
        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvDanhMucTapLuyen);
            imgicon = (ImageView) itemView.findViewById(R.id.imgDanhMucTapLuyen);
        }
    }

    @NonNull
    @Override
    public TapBungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_tapbung, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TapBungAdapter.ViewHolder viewHolder, int i) {
        final danhMucTapBung dm = listFunction.get(i);


        // Set từng giá trị lên item

        viewHolder.imgicon.setImageResource(dm.getHinhAnh());
        viewHolder.tvdanhmuc.setText(dm.getTenDanhMuc());

    }

    @Override
    public int getItemCount() {
        return listFunction.size();
    }



}
