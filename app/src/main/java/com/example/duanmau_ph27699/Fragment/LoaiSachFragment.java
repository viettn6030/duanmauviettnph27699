package com.example.duanmau_ph27699.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph27699.Adapter.LoaiSachAdapter;
import com.example.duanmau_ph27699.DAO.LoaiSachDAO;
import com.example.duanmau_ph27699.Model.LoaiSach;
import com.example.duanmau_ph27699.R;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    RecyclerView recyclerView;
    LoaiSachDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.activity_loai_sach_fragment,container,false);
        EditText edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnthem = view.findViewById(R.id.btnThemLsach);
        recyclerView = view.findViewById(R.id.recyLoaiSach);
        loadData();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();
                if (dao.themLoaiSach(tenloai)){
                    loadData();
edtLoaiSach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thêm Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        dao = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = dao.getLoaiSach();
        LoaiSachAdapter  adapter =new LoaiSachAdapter(list,getContext());
        recyclerView.setAdapter(adapter);

    }

}