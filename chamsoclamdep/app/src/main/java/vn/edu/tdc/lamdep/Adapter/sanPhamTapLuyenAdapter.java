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
import vn.edu.tdc.lamdep.Activity.ProductLike;
import vn.edu.tdc.lamdep.Activity.sanPhamTapLuyen;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

public class sanPhamTapLuyenAdapter extends BaseAdapter {
    sanPhamTapLuyen context;
    ArrayList<sanPham> arrayListtapluyen;

    public sanPhamTapLuyenAdapter(sanPhamTapLuyen context, ArrayList<sanPham> arrayListtapluyen) {
        this.context = context;
        this.arrayListtapluyen = arrayListtapluyen;
    }

    @Override
    public int getCount() {
        return arrayListtapluyen.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListtapluyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txttensptl, txtgiatapluyen, txtmotatapluyen;
        public ImageView imgtapluyen, imglike;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        sanPhamTapLuyenAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new sanPhamTapLuyenAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_sanphamtapluyen, null);
            viewHolder.txttensptl = convertView.findViewById(R.id.tvtensanpham);
            viewHolder.txtgiatapluyen = convertView.findViewById(R.id.tvgiasanpham);
            viewHolder.txtmotatapluyen = convertView.findViewById(R.id.tvmota);
            viewHolder.imgtapluyen = convertView.findViewById(R.id.imgsanphamtapluyen);
            viewHolder.imglike = convertView.findViewById(R.id.imglike);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (sanPhamTapLuyenAdapter.ViewHolder) convertView.getTag();
        }
        sanPham sp = (sanPham) getItem(position);
        viewHolder.txttensptl.setText(sp.getTensanpham());
        viewHolder.txttensptl.setMaxLines(2);
        viewHolder.txttensptl.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiatapluyen.setText("Giá: " + decimalFormat.format(sp.getGiasanpham()) + ". VNĐ");
        viewHolder.txtmotatapluyen.setMaxLines(2);
        viewHolder.txtmotatapluyen.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatapluyen.setText(sp.getMotasanpham());
        Picasso.with(context).load(sp.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgtapluyen);
        final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.imglike.setImageResource(R.drawable.like);
                ApiInterface apiInterface = APISERVISE.getServise();
                Call<String> stringCall = apiInterface.updatelike("1", String.valueOf(arrayListtapluyen.get(position).getId()));
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("Success")) {
                            CheckConnect.showToast_Short(context, "Yêu thích");
                            context.refresh();
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
