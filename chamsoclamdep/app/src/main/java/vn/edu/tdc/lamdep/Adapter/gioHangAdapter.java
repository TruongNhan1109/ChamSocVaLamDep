package vn.edu.tdc.lamdep.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.cartActivity;
import vn.edu.tdc.lamdep.Activity.sanPhamActivity;
import vn.edu.tdc.lamdep.Model.gioHang;
import vn.edu.tdc.lamdep.R;

public class gioHangAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<gioHang> gioHangArrayList;
    private int position;

    public gioHangAdapter(Context context, ArrayList<gioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public ImageView imgicon;
        public TextView tvtengiohang, tvgiagiohang;
        public Button btntang, btngiam, btnvalues, btndelete, btnkichthuoc, btnupdate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_cart, null);
            viewHolder.tvtengiohang = convertView.findViewById(R.id.tvtengiohang);
            viewHolder.tvgiagiohang = convertView.findViewById(R.id.tvgiagiohang);
            viewHolder.imgicon = convertView.findViewById(R.id.imgicon);
            viewHolder.btntang = convertView.findViewById(R.id.btntang);
            viewHolder.btnvalues = convertView.findViewById(R.id.btnvalues);
            viewHolder.btngiam = convertView.findViewById(R.id.btngiam);
            viewHolder.btndelete = convertView.findViewById(R.id.btndelete);
            viewHolder.btnkichthuoc = convertView.findViewById(R.id.btnkichthuoc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        gioHang item = (gioHang) getItem(position);
        viewHolder.tvtengiohang.setText(item.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvgiagiohang.setText(decimalFormat.format(item.getGia()) + "VNĐ");
        Picasso.with(context).load(item.getHinhAnh())
                .placeholder(R.drawable.noiimage)
                .error(R.drawable.error)
                .into(viewHolder.imgicon);
        viewHolder.btnkichthuoc.setText(item.getKichthuoc());
        viewHolder.btnvalues.setText(item.getSoLuong() + "");

        final int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        final String kt = viewHolder.btnkichthuoc.getText().toString();
        if (sl >= 10) {
            viewHolder.btntang.setVisibility(View.INVISIBLE);
            viewHolder.btngiam.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            viewHolder.btngiam.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            viewHolder.btngiam.setVisibility(View.VISIBLE);
            viewHolder.btntang.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder1 = viewHolder;
        //delete item on listview shop cart
        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn muốn loại bỏ sản phẩm này khỏi giỏ hàng");
                builder.setCancelable(false);
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Thành công: \n" + gioHangArrayList.get(position).getTensp(), Toast.LENGTH_LONG).show();
                        sanPhamActivity.manggiohang.remove(position);
                        cartActivity.checkTongTien();
                        cartActivity.notifileDatachange();
                        cartActivity.checkData();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartActivity.notifileDatachange();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        viewHolder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) + 1;
                int slhientai = sanPhamActivity.manggiohang.get(position).getSoLuong();
                long giaht = sanPhamActivity.manggiohang.get(position).getGia();
                sanPhamActivity.manggiohang.get(position).setSoLuong(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) / slhientai;
                sanPhamActivity.manggiohang.get(position).setGia(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.tvgiagiohang.setText(decimalFormat.format(giamoinhat) + " VNĐ");
                cartActivity.checkTongTien();
                if (slmoinhat > 9) {
                    finalViewHolder1.btntang.setVisibility(View.INVISIBLE);
                    finalViewHolder1.btngiam.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                } else {
                    finalViewHolder.btngiam.setVisibility(View.VISIBLE);
                    finalViewHolder.btntang.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) - 1;
                int slhientai = sanPhamActivity.manggiohang.get(position).getSoLuong();
                long giaht = sanPhamActivity.manggiohang.get(position).getGia();
                sanPhamActivity.manggiohang.get(position).setSoLuong(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) / slhientai;
                sanPhamActivity.manggiohang.get(position).setGia(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.tvgiagiohang.setText(decimalFormat.format(giamoinhat) + " VNĐ");
                cartActivity.checkTongTien();
                if (slmoinhat < 2) {
                    finalViewHolder.btngiam.setVisibility(View.INVISIBLE);
                    finalViewHolder.btntang.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                } else {
                    finalViewHolder.btngiam.setVisibility(View.VISIBLE);
                    finalViewHolder.btntang.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return convertView;
    }
}

