package com.example.duanmau_ph27699.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph27699.DAO.PhieuMuonDAO;
import com.example.duanmau_ph27699.Model.PhieuMuon;
import com.example.duanmau_ph27699.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{
    private ArrayList<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view   = inflater.inflate(R.layout.item_phieumuon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtmaPM.setText("Mã PM:"+ list.get(position).getMapm());
            holder.txtmaTV.setText("Mã TV:"+ list.get(position).getMatv());
//            holder.txttenTV.setText("Tên TV:"+ list.get(position).getTentv());
            holder.txtmaTT.setText("Mã TT:"+ list.get(position).getMatt());
//            holder.txttenTT.setText("Tên TT:"+ list.get(position).getTentt());
            holder.txtmaSach.setText("Mã Sách:"+ list.get(position).getMasach());
//            holder.txttenSach.setText("Tên Sách:"+ list.get(position).getTensach());
            holder.txtNgay.setText("Ngày:"+ list.get(position).getNgay());
            String trangthai="";
            if (list.get(position).getTrasach() == 1){
                trangthai = "đã trả sách";
                holder.btnTraSach.setVisibility(View.GONE);
            }else {
                trangthai = "chưa trả sách";
                holder.btnTraSach.setVisibility(View.VISIBLE);

        }
            holder.txtTrangThai.setText("Trạng thái:"+ trangthai);
            holder.txtTien.setText("Tiền:"+ list.get(position).getTienthue());
            holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PhieuMuonDAO phieuMuonDAO= new PhieuMuonDAO(context);
                    boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                    if (kiemtra == true){
                        list.clear();
                        list = phieuMuonDAO.getDSPhieuMuon();
                        notifyDataSetChanged();

                    }else {
                        Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtmaPM,txtmaTV,txttenTV,txtmaTT,txttenTT,txtmaSach,txttenSach,txtNgay,txtTrangThai,txtTien;
        Button btnTraSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaPM = itemView.findViewById(R.id.txtmaPM);
            txtmaTT = itemView.findViewById(R.id.txttmaTT);
//            txttenTT = itemView.findViewById(R.id.txttenTT);
            txtmaTV = itemView.findViewById(R.id.txtmaTV);
//            txttenTV = itemView.findViewById(R.id.txttenTV);
            txtmaSach = itemView.findViewById(R.id.txtmaSach);
//            txttenSach= itemView.findViewById(R.id.txttenSach);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtTien = itemView.findViewById(R.id.txtTien);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);

        }
    }
}
