package vn.edu.tdc.lamdep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.DanhSachBaiViet;
import vn.edu.tdc.lamdep.Activity.sanPhamActivity;
import vn.edu.tdc.lamdep.Fragment.DaDep;
import vn.edu.tdc.lamdep.Fragment.DangDep;
import vn.edu.tdc.lamdep.Fragment.MacDep;
import vn.edu.tdc.lamdep.Fragment.MakeUp;
import vn.edu.tdc.lamdep.Fragment.TapLuyen;
import vn.edu.tdc.lamdep.Fragment.TocDep;
import vn.edu.tdc.lamdep.R;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.tdc.lamdep.Model.danhMucHome;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    // Các thuộc tính
    private ArrayList<danhMucHome> listFunction;  // Danh sách
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;



    // Phương thức khởi tạo
    public HomeAdapter(Context context, ArrayList<danhMucHome> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Liên kết với giao diện item
        View itemView = inflater.inflate(R.layout.list_item_home, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        // Lấy từ hiện tại
        final danhMucHome dm = listFunction.get(i);



        // Set từng giá trị lên item

        viewHolder.imgicon.setImageResource(dm.getHinhAnh());
        viewHolder.tvdanhmuc.setText(dm.getTenDanhMuc());
        viewHolder.tvtonghop.setText(dm.getTongHopPhuongPhap());


        // Bắt sự kiện nhấn vào một item
        // Bắt sự kiện nhấn vào một item
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                switch (dm.getIdDanhMuc()){
                    case 1:
                        Intent intent = new Intent(context,sanPhamActivity.class);
                        context.startActivity(intent);
                        break;
                    case 2:
                        FragmentTransaction ft2 = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        Fragment fragment2 = new DaDep();
                        ft2.replace(R.id.content_main, fragment2);
                        ft2.commit();
//                        viewHolder.imgbtnDaDep.setSelected(true);
//                        viewHolder.tvDaDep.setTextColor(context.getResources().getColor(R.color.iconColorPressed));
                        break;
                    case 3:
                        FragmentTransaction ft3 = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        Fragment fragment3 = new MakeUp();
                        ft3.replace(R.id.content_main, fragment3);
                        ft3.commit();
                        break;
                    case 4:
                        FragmentTransaction ft4 = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        Fragment fragment4 = new TocDep();
                        ft4.replace(R.id.content_main, fragment4);
                        ft4.commit();
                        break;
                    case 5:
                        FragmentTransaction ft5 = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        Fragment fragment5 = new MacDep();
                        ft5.replace(R.id.content_main, fragment5);
                        ft5.commit();
                        break;
                    case 6:
                        FragmentTransaction ft6 = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        Fragment fragment6 = new DangDep();
                        ft6.replace(R.id.content_main, fragment6);
                        ft6.commit();
                        break;
                    case 7:
                        FragmentTransaction ft7 = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        Fragment fragment7 = new TapLuyen();
                        ft7.replace(R.id.content_main, fragment7);
                        ft7.commit();
                        break;
                        default:
                            break;

                }
//                Toast.makeText(context, dm.getIdDanhMuc() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFunction.size();
    }


    // Tạo ra một view holder
    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton imgbtnTocDep,imgbtnHome, imgbtnMacDep, imgbtnMakeUp, imgbtnDaDep, imgbtnDangDep,imgbtnTapLuyen,imgbtnSanPham;
        private TextView tvTocDep,tvHome, tvMakeUp, tvDaDep, tvMacDep, tvDangDep,tvTapLuyen,tvSanPham;
        private CircleImageView imgicon;
        private TextView tvdanhmuc;
        private TextView tvtonghop;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgicon = (CircleImageView) itemView.findViewById(R.id.imgicon);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvdanhmuc);
            tvtonghop = (TextView) itemView.findViewById(R.id.tvtonghop);

//            imgbtnTocDep = (ImageButton) itemView.findViewById(R.id.imgbtnTocDep);
//            imgbtnHome =(ImageButton) itemView.findViewById(R.id.imgbtnhome);
//            imgbtnMacDep = (ImageButton) itemView.findViewById(R.id.imgbtnMacDep);
//            imgbtnMakeUp = (ImageButton) itemView.findViewById(R.id.imgbtnMakeUp);
//            imgbtnDaDep = (ImageButton) itemView.findViewById(R.id.imgbtnDaDep);
//            imgbtnDangDep = (ImageButton) itemView.findViewById(R.id.imgbtnDangDep);
//            imgbtnTapLuyen = (ImageButton)itemView.findViewById(R.id.imgbtnTapLuyen);
//            imgbtnSanPham = (ImageButton) itemView.findViewById(R.id.imgbtnSanPham);
//            tvTocDep =(TextView) itemView.findViewById(R.id.tvTocDep);
//            tvHome =(TextView) itemView.findViewById(R.id.tvHome);
//            tvMakeUp = (TextView)itemView.findViewById(R.id.tvMakeUp);
//            tvMacDep = (TextView) itemView.findViewById(R.id.tvMacDep);
//            tvDangDep =(TextView) itemView.findViewById(R.id.tvDangDep);
//            tvDaDep =(TextView)itemView.findViewById(R.id.tvDaDep);
//            tvTapLuyen  = (TextView) itemView.findViewById(R.id.tvTapLuyen);
//            tvSanPham  = (TextView) itemView.findViewById(R.id.tvSanPham);
        }
    }

}
