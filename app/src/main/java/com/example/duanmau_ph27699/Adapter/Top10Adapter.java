package com.example.duanmau_ph27699.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph27699.Model.Sach;
import com.example.duanmau_ph27699.R;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMasach.setText("Mã sách: "+String.valueOf(list.get(position).getMaSach()));
        holder.txtTensach.setText("Tên sách: "+list.get(position).getTenSach());
        holder.txtSoluongmuon.setText("Số lượng mượn: "+String.valueOf(list.get(position).getSoluongmuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMasach,txtTensach,txtSoluongmuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtmasach);
            txtTensach = itemView.findViewById(R.id.txttensach);
            txtSoluongmuon = itemView.findViewById(R.id.txtsoluongmuon);

        }
    }
}
