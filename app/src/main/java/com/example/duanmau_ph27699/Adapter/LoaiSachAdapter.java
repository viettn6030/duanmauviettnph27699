package com.example.duanmau_ph27699.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph27699.DAO.LoaiSachDAO;
import com.example.duanmau_ph27699.Model.LoaiSach;
import com.example.duanmau_ph27699.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    ArrayList<LoaiSach> list;
    Context context ;

    public LoaiSachAdapter(ArrayList<LoaiSach> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view   = inflater.inflate(R.layout.item_loaisach,parent,false  );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText("Tên loại:" +list.get(position).getTenLoai());
        holder.txtMaLoai.setText("Mã loại:" + String.valueOf(list.get(position).getId()));
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO dao = new LoaiSachDAO(context);
                int check = dao.xoaSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        list.clear();
                        list= dao.getLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xoá vì đã có loại sách thuộc loại này", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xoá không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLoai,txtTenLoai;
        ImageView imageView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtmaloai);
            txtTenLoai = itemView.findViewById(R.id.txttenloai);
            imageView1 = itemView.findViewById(R.id.delete);

        }
    }
}
