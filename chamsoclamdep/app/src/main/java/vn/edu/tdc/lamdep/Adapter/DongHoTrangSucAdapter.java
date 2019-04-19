package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class DongHoTrangSucAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanPham> arrayListdonghotrangsuc;

    public DongHoTrangSucAdapter(Context context, ArrayList<sanPham> arrayListdonghotrangsuc) {
        this.context = context;
        this.arrayListdonghotrangsuc = arrayListdonghotrangsuc;
    }

    @Override
    public int getCount() {
        return arrayListdonghotrangsuc.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListdonghotrangsuc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txttenspdonghotrangsuc, txtgiatrangsuc, txtmotatrangsuc;
        public ImageView imgdonghotrangsuc, imglike;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DongHoTrangSucAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new DongHoTrangSucAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_dongho_trangsuc, null);
            viewHolder.txttenspdonghotrangsuc = convertView.findViewById(R.id.tvtensanpham);
            viewHolder.txtgiatrangsuc = convertView.findViewById(R.id.tvgiasanpham);
            viewHolder.txtmotatrangsuc = convertView.findViewById(R.id.tvmota);
            viewHolder.imgdonghotrangsuc = convertView.findViewById(R.id.imgsanphamdonghotrangsuc);
            viewHolder.imglike = convertView.findViewById(R.id.imglike);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DongHoTrangSucAdapter.ViewHolder) convertView.getTag();
        }
        sanPham sp = (sanPham) getItem(position);
        viewHolder.txttenspdonghotrangsuc.setText(sp.getTensanpham());
        viewHolder.txttenspdonghotrangsuc.setMaxLines(2);
        viewHolder.txttenspdonghotrangsuc.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiatrangsuc.setText("Giá: " + decimalFormat.format(sp.getGiasanpham()) + ". VNĐ");
        viewHolder.txtmotatrangsuc.setMaxLines(2);
        viewHolder.txtmotatrangsuc.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatrangsuc.setText(sp.getMotasanpham());
        Picasso.with(context).load(sp.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgdonghotrangsuc);
        final DongHoTrangSucAdapter.ViewHolder finalViewHolder = viewHolder;
        final DongHoTrangSucAdapter.ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.imglike.setImageResource(R.drawable.like);
                ApiInterface apiInterface = APISERVISE.getServise();
                Call<String> stringCall = apiInterface.updatelike("1", String.valueOf(arrayListdonghotrangsuc.get(position).getId()));
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("Success")) {
                            CheckConnect.showToast_Short(context, "Yêu thích");
                        } else {
                            CheckConnect.showToast_Short(context, "Lỗi");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                finalViewHolder1.imglike.setEnabled(false);
            }
        });
        return convertView;
    }
}
