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

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.BaiViet;
import vn.edu.tdc.lamdep.Activity.DanhSachBaiViet;
import vn.edu.tdc.lamdep.Model.DanhSachBaiViet_Model;
import vn.edu.tdc.lamdep.R;

public class DanhSachBaiVietAdapter extends RecyclerView.Adapter<DanhSachBaiVietAdapter.ViewHolder>{
    public DatabaseReference reference;
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public DanhSachBaiVietAdapter(Context context, ArrayList<DanhSachBaiViet_Model> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }
    public DanhSachBaiVietAdapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }

    private ArrayList<DanhSachBaiViet_Model> listFunction;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgicon;
        public TextView tvdanhmuc;
        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvTenBaiViet);
            imgicon = (ImageView) itemView.findViewById(R.id.imgBaiViet);
        }
    }

    @NonNull
    @Override
    public DanhSachBaiVietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_danhsachbaiviet, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachBaiVietAdapter.ViewHolder viewHolder, int i) {
        final DanhSachBaiViet_Model dm = listFunction.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(dm.getImgbaiviet()).into(viewHolder.imgicon);
        viewHolder.tvdanhmuc.setText(dm.getTenbaiviet());


        // Bắt sự kiện nhấn vào một item
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BaiViet.class);
                intent.putExtra("tieude",dm.getTenbaiviet() );
                intent.putExtra("mota",dm.getMota() );
                intent.putExtra("chuanbi",dm.getChuanbi() );
                intent.putExtra("thuchien",dm.getThuchien() );
                intent.putExtra("img", dm.getImgbaiviet());
                Toast.makeText(context, dm.getTenbaiviet() + "", Toast.LENGTH_SHORT).show();
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
}
