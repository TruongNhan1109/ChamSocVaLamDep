package vn.edu.tdc.lamdep.Adapter;

import android.app.ProgressDialog;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Activity.chiTietSanPham;
import vn.edu.tdc.lamdep.Activity.sanPhamActivity;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

import static vn.edu.tdc.lamdep.Activity.ProductLike.sanPhamYeuThichAdapter;

public class SanPhamYeuThichAdapter extends RecyclerView.Adapter<SanPhamYeuThichAdapter.ViewHolder> {

    // Các thuộc tính
    ArrayList<sanPham> mangyeuthich;  // Danh sách
    Context context;   // Màn hình hiện tại
    LayoutInflater inflater;


    // Phương thức khởi tạo
    public SanPhamYeuThichAdapter(Context context, ArrayList<sanPham> mangyeuthich) {
        this.context = context;
        this.mangyeuthich = mangyeuthich;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Liên kết với giao diện item
        View itemView = inflater.inflate(R.layout.list_item_sanpham_yeuthich, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // Lấy từ hiện tại
        sanPham sp = mangyeuthich.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(sp.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsanphamyeuthich);
        viewHolder.tvtensanphamyeuthich.setMaxLines(2);
        viewHolder.tvtensanphamyeuthich.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvtensanphamyeuthich.setText(sp.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvgiasanphamyeuthich.setText("Giá: " + decimalFormat.format(sp.getGiasanpham()) + ". VNĐ");
        viewHolder.tvmotayeuthich.setMaxLines(2);
        viewHolder.tvmotayeuthich.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvmotayeuthich.setText(sp.getMotasanpham());
        viewHolder.tvluotthich.setText(String.valueOf(sp.getYeuthich()));

    }


    @Override
    public int getItemCount() {
        return mangyeuthich.size();
    }


    // Tạo ra một view holder
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imgsanphamyeuthich, imglike;
        private TextView tvtensanphamyeuthich;
        private TextView tvgiasanphamyeuthich;
        private TextView tvmotayeuthich;
        private TextView tvluotthich;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgsanphamyeuthich = (ImageView) itemView.findViewById(R.id.imgsanphamyeuthich);
            tvtensanphamyeuthich = (TextView) itemView.findViewById(R.id.tvtensanphamyeuthich);
            tvgiasanphamyeuthich = (TextView) itemView.findViewById(R.id.tvgiasanphamyeuthich);
            tvmotayeuthich = (TextView) itemView.findViewById(R.id.tvmotayeuthich);
            tvluotthich = (TextView) itemView.findViewById(R.id.tvluotthich);
            imglike = (ImageView) itemView.findViewById(R.id.imglike);
            if (CheckConnect.haveNetworkConnection(context)) {
                imglike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imglike.setImageResource(R.drawable.like);
                        ApiInterface apiInterface = APISERVISE.getServise();
                        Call<String> stringCall = apiInterface.updatelike("1", String.valueOf(mangyeuthich.get(getPosition()).getId()));
                        stringCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if (ketqua.equals("Success")) {
                                    CheckConnect.showToast_Short(context, "Yêu thích");
                                    sanPhamYeuThichAdapter.notifyDataSetChanged();
                                } else {
                                    CheckConnect.showToast_Short(context, "Lỗi");
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        imglike.setEnabled(false);
                    }
                });

            } else {
                CheckConnect.showToast_Short(context, "Lỗi mạng");
                CheckConnect.showToast_Short(context, "Vui lòng kiểm tra kết nối");
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, chiTietSanPham.class);
                    intent.putExtra("thongtinsanpham", mangyeuthich.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(context, mangyeuthich.get(getPosition()).getTensanpham(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }

}
