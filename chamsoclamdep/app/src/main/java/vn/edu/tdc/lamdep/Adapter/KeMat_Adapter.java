package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Model.KeMat_Model;
import vn.edu.tdc.lamdep.R;

/**
 * Created by USER on 24/04/2019.
 */

public class KeMat_Adapter extends RecyclerView.Adapter<KeMat_Adapter.ViewHolder> {

    // Màn hình hiện tại
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<KeMat_Model> listFunction;

    // Phương thức khởi tạo
    public KeMat_Adapter(Context context, ArrayList<KeMat_Model> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }
    public KeMat_Adapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public KeMat_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.list_item_chamsoc, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KeMat_Adapter.ViewHolder viewHolder, int i) {
        final KeMat_Model dm = listFunction.get(i);

        // Set từng giá trị lên item

        viewHolder.imgicon.setImageResource(dm.getHinhAnh());
        viewHolder.tvdanhmuc.setText(dm.getTenbaiviet());


        // Bắt sự kiện nhấn vào một item
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickedListener.onItemClick(dm.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgicon;
        public TextView tvdanhmuc;
        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.txtChamSoc1);
            imgicon = (ImageView) itemView.findViewById(R.id.imgChamSoc1);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(int idFunction);

    }

    // Khai báo một biến interface
    private KeMat_Adapter.OnItemClickedListener onItemClickedListener;

    // Tạo setter cho biến interface ta vừa tạo
    public void setOnItemClickedListener(KeMat_Adapter.OnItemClickedListener onItemClickedListener) {

        this.onItemClickedListener = onItemClickedListener;
    }
}
