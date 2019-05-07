package vn.edu.tdc.lamdep.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tdc.lamdep.Activity.AddProduct;
import vn.edu.tdc.lamdep.Activity.CapNhatSanPham;
import vn.edu.tdc.lamdep.Activity.DanhSachSanPham;
import vn.edu.tdc.lamdep.Activity.UpLoadImages;
import vn.edu.tdc.lamdep.Activity.cartActivity;
import vn.edu.tdc.lamdep.Activity.chiTietSanPham;
import vn.edu.tdc.lamdep.Activity.sanPhamActivity;
import vn.edu.tdc.lamdep.Model.sanPham;
import vn.edu.tdc.lamdep.R;
import vn.edu.tdc.lamdep.retrofit.APISERVISE;
import vn.edu.tdc.lamdep.retrofit.ApiInterface;
import vn.edu.tdc.lamdep.unitl.CheckConnect;

import static vn.edu.tdc.lamdep.Activity.DanhSachSanPham.mangsanpham;

public class SanPhamAdapter extends BaseAdapter {
    DanhSachSanPham context;
    ArrayList<sanPham> arrayList;
    Button btndongy, btnclose;
    ImageView imgShow;
    TextView showTenSP;

    public SanPhamAdapter(DanhSachSanPham context, ArrayList<sanPham> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txtloaisanpham, txttensp, txtgia, txtmota, txtluotthich;
        public ImageView imgsanpham, imgdelete, imgupdate;
        public Button btnclick;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SanPhamAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new SanPhamAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_product, null);
            viewHolder.txtloaisanpham = convertView.findViewById(R.id.tvloaisanpham);
            viewHolder.txttensp = convertView.findViewById(R.id.tvtensanpham);
            viewHolder.txtgia = convertView.findViewById(R.id.tvgiasanpham);
            viewHolder.txtmota = convertView.findViewById(R.id.tvmota);
            viewHolder.txtluotthich = convertView.findViewById(R.id.tvluotthich);
            viewHolder.imgsanpham = convertView.findViewById(R.id.imgsanpham);
            viewHolder.imgdelete = convertView.findViewById(R.id.imgdelete);
            viewHolder.imgupdate = convertView.findViewById(R.id.imgupdate);
            viewHolder.btnclick = convertView.findViewById(R.id.clickchonhinh);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SanPhamAdapter.ViewHolder) convertView.getTag();
        }
        sanPham sp = (sanPham) getItem(position);
        viewHolder.txtloaisanpham.setText(String.valueOf(sp.getIdsanpham()));
        viewHolder.txttensp.setText(sp.getTensanpham());
        viewHolder.txttensp.setMaxLines(2);
        viewHolder.txttensp.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgia.setText("Giá: " + decimalFormat.format(sp.getGiasanpham()) + ". VNĐ");
        viewHolder.txtmota.setMaxLines(2);
        viewHolder.txtmota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmota.setText(sp.getMotasanpham());
        viewHolder.txtluotthich.setText(String.valueOf(sp.getYeuthich()));

        viewHolder.btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpLoadImages.class);
                intent.putExtra("thongtinsanpham", arrayList.get(position));
                context.startActivity(intent);
            }
        });
        viewHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DanhSachSanPham.capnhatdulieu();
               context.showdialog();
            }
        });

        viewHolder.imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CapNhatSanPham.class);
                intent.putExtra("thongtinsanpham", arrayList.get(position));
                context.startActivity(intent);
            }
        });

        Picasso.with(context).load(sp.getHinhanhsanpham())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsanpham);

        return convertView;
    }
}
