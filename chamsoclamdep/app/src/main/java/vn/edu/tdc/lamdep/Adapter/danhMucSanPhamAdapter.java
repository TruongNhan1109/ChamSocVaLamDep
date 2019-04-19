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

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.tdc.lamdep.Activity.chiTietSanPham;
import vn.edu.tdc.lamdep.Model.danhMucSanPham;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;

public class danhMucSanPhamAdapter extends RecyclerView.Adapter<danhMucSanPhamAdapter.ViewHolder> {

    // Các thuộc tính
    private ArrayList<danhMucSanPham> listFunction;  // Danh sách
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;


    // Phương thức khởi tạo
    public danhMucSanPhamAdapter(Context context, ArrayList<danhMucSanPham> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Liên kết với giao diện item
        View itemView = inflater.inflate(R.layout.list_item_danhmucsanpham, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        // Lấy từ hiện tại
        final danhMucSanPham dm = listFunction.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(dm.getHinhAnhLoai())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgloaidanhmuc);
        viewHolder.txttendanhmuc.setText(dm.getTenLoaiSanPham());


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
        return listFunction.size();
    }


    // Tạo ra một view holder
    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgloaidanhmuc;
        private TextView txttendanhmuc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgloaidanhmuc = (CircleImageView) itemView.findViewById(R.id.imgloaidanhmuc);
            txttendanhmuc = (TextView) itemView.findViewById(R.id.tvloaidanhmuc);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(int idFunction);

    }

    // Khai báo một biến interface
    private danhMucSanPhamAdapter.OnItemClickedListener onItemClickedListener;

    // Tạo setter cho biến interface ta vừa tạo
    public void setOnItemClickedListener(danhMucSanPhamAdapter.OnItemClickedListener onItemClickedListener) {

        this.onItemClickedListener = onItemClickedListener;
    }

}
