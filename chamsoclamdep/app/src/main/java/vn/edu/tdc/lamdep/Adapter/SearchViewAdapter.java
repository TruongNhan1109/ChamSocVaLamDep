package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Activity.DanhSachSanPham;
import vn.edu.tdc.lamdep.Activity.ProductLike;
import vn.edu.tdc.lamdep.Activity.TimKiemSanPham;
import vn.edu.tdc.lamdep.Activity.chiTietSanPham;
import vn.edu.tdc.lamdep.Activity.sanPhamActivity;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

import static vn.edu.tdc.lamdep.Activity.ProductLike.sanPhamYeuThichAdapter;
import static vn.edu.tdc.lamdep.Activity.TimKiemSanPham.searchViewAdapter;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> {

    // Các thuộc tính
    ArrayList<sanPham> mangtimkiem;  // Danh sách
    TimKiemSanPham context;   // Màn hình hiện tại
    LayoutInflater inflater;


    // Phương thức khởi tạo
    public SearchViewAdapter(TimKiemSanPham context, ArrayList<sanPham> mangtimkiem) {
        this.context = context;
        this.mangtimkiem = mangtimkiem;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Liên kết với giao diện item
        View itemView = inflater.inflate(R.layout.list_item_search, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // Lấy từ hiện tại
        sanPham sp = mangtimkiem.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(sp.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsanphamsearchview);
        viewHolder.tvtensanphamsearchview.setMaxLines(2);
        viewHolder.tvtensanphamsearchview.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvtensanphamsearchview.setText(sp.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvgiasanphamsearchview.setText("Giá: " + decimalFormat.format(sp.getGiasanpham()) + ". VNĐ");
        viewHolder.tvmotasearchview.setMaxLines(2);
        viewHolder.tvmotasearchview.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvmotasearchview.setText(sp.getMotasanpham());
        viewHolder.tvluotthich.setText(String.valueOf(sp.getYeuthich()));

    }


    @Override
    public int getItemCount() {
        return mangtimkiem.size();
    }


    // Tạo ra một view holder
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imgsanphamsearchview;
        private TextView tvtensanphamsearchview;
        private TextView tvgiasanphamsearchview;
        private TextView tvmotasearchview;
        private TextView tvluotthich;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgsanphamsearchview = (ImageView) itemView.findViewById(R.id.imgsanphamsearchview);
            tvtensanphamsearchview = (TextView) itemView.findViewById(R.id.tvtensanphamsearchview);
            tvgiasanphamsearchview = (TextView) itemView.findViewById(R.id.tvgiasanphamsearchview);
            tvmotasearchview = (TextView) itemView.findViewById(R.id.tvmotasearchview);
            tvluotthich = (TextView) itemView.findViewById(R.id.tvluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, chiTietSanPham.class);
                    intent.putExtra("thongtinsanpham", mangtimkiem.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(context, mangtimkiem.get(getPosition()).getTensanpham(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }

}
