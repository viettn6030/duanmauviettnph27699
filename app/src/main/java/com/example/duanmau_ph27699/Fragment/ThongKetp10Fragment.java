package com.example.duanmau_ph27699.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph27699.Adapter.Top10Adapter;
import com.example.duanmau_ph27699.DAO.ThongKeDAO;
import com.example.duanmau_ph27699.Model.Sach;
import com.example.duanmau_ph27699.R;

import java.util.ArrayList;

public class ThongKetp10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_thongketp10,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rcviewTop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
