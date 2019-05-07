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

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.tdc.lamdep.Activity.DangKy;
import vn.edu.tdc.lamdep.Activity.KeLongMay;
import vn.edu.tdc.lamdep.Activity.KeMat;
import vn.edu.tdc.lamdep.Activity.KieuToc;
import vn.edu.tdc.lamdep.Model.Make_Up_Model;
import vn.edu.tdc.lamdep.R;

public class MakeUp_Adapter extends RecyclerView.Adapter<MakeUp_Adapter.ViewHolder> {

    public DatabaseReference reference;
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public MakeUp_Adapter(Context context, ArrayList<Make_Up_Model> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }

    public MakeUp_Adapter() {
        this.context = context;
        this.listFunction = listFunction;
        inflater = LayoutInflater.from(context);

    }

    private ArrayList<Make_Up_Model> listFunction;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgicon;
        public TextView tvdanhmuc;
        public android.support.v7.widget.CardView cardView;

        public ViewHolder(View itemView) {

            super(itemView);
            tvdanhmuc = (TextView) itemView.findViewById(R.id.tvmakeup);
            imgicon = (ImageView) itemView.findViewById(R.id.imgmakeup);
        }
    }

    @NonNull
    @Override
    public MakeUp_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_makeup, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MakeUp_Adapter.ViewHolder viewHolder, int i) {
        final Make_Up_Model dm = listFunction.get(i);


        // Set từng giá trị lên item

        Picasso.with(context).load(dm.getImg()).into(viewHolder.imgicon);
        viewHolder.tvdanhmuc.setText(dm.getName());



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dm.getIddanhmuc()) {
                    case 1:
                        Intent intent = new Intent(context, KeLongMay.class);
                        context.startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(context, KeMat.class);
                        context.startActivity(intent1);
                        break;
                    case 3:
                        Intent intent2 = new Intent(context, KieuToc.class);
                        context.startActivity(intent2);
                        break;
                    case 4:
                        Intent intent3 = new Intent(context, DangKy.class);
                        context.startActivity(intent3);
                        break;
                    case 5:
                        Intent intent4 = new Intent(context, KeLongMay.class);
                        context.startActivity(intent4);
                        break;
                    default:
                        break;
                }
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

    // Khai báo một biến interface
    private MakeUp_Adapter.OnItemClickedListener onItemClickedListener;

    // Tạo setter cho biến interface ta vừa tạo
    public void setOnItemClickedListener(MakeUp_Adapter.OnItemClickedListener onItemClickedListener) {

        this.onItemClickedListener = onItemClickedListener;
    }
}
