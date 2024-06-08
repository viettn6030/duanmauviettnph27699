package com.example.duanmau_ph27699.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph27699.Adapter.PhieuMuonAdapter;
import com.example.duanmau_ph27699.DAO.PhieuMuonDAO;
import com.example.duanmau_ph27699.DAO.SachDAO;
import com.example.duanmau_ph27699.DAO.ThanhVienDAO;
import com.example.duanmau_ph27699.Model.PhieuMuon;
import com.example.duanmau_ph27699.Model.Sach;
import com.example.duanmau_ph27699.Model.ThanhVien;
import com.example.duanmau_ph27699.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PhieuMuonFragment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    RecyclerView recyclerViewPhieuMuon;
    FloatingActionButton floatAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.activity_phieu_muon_fragment,container,false);

        recyclerViewPhieuMuon = view.findViewById(R.id.recyclerViewQLPhieuMuon);
         floatAdd = view.findViewById(R.id.floatAdd);
        loadData();
        //giao dien
        // data
//         phieuMuonDAO = new PhieuMuonDAO(getContext());
//         list = phieuMuonDAO.getDSPhieuMuon();

//        list.add(new PhieuMuon(1,1,"thuthu1",1,"20/12/2024",1,100000,"tran van a","tran van c","doraemon 1"));

        //adapter
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerViewPhieuMuon.setLayoutManager(linearLayoutManager);
//        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(list,getContext());
//        recyclerViewPhieuMuon.setAdapter(phieuMuonAdapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_phieumuon,null);
        Spinner spinThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spinSach = view.findViewById(R.id.spnSach);
//        EditText edtTien = view.findViewById(R.id.edtTien);
        getDataThanhvien(spinThanhVien);
        getDataSach(spinSach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // lay ma thanh vien
                HashMap<String,Object> hsTV = (HashMap<String, Object>) spinThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                // lay ma sach
                HashMap<String,Object> hsSACH = (HashMap<String, Object>) spinSach.getSelectedItem();
                int masach = (int) hsSACH.get("masach");

                int tien = (int) hsSACH.get("giathue");
                themPhieuMuon(matv,masach,tien);
            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private void loadData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewPhieuMuon.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(list,getContext());
        recyclerViewPhieuMuon.setAdapter(phieuMuonAdapter);

    }

    private void getDataThanhvien(Spinner spnThanhVien){
        ThanhVienDAO   thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("matv",tv.getMatv());
            hs.put("hoten",tv.getHoten());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter =   new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
                spnThanhVien.setAdapter(simpleAdapter);

    }
    private void getDataSach(Spinner spnSach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getDSSach();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (Sach tv : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("masach",tv.getMaSach());
            hs.put("tensach",tv.getTenSach());
            hs.put("giathue",tv.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter =   new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);

    }
    private void themPhieuMuon(int matv,int masach,int tien){
        //lay ma thuthu
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");
        // lay ngay hien tai
        Date curritme = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(curritme);
        PhieuMuon phieuMuon = new PhieuMuon(matv,matt,masach,ngay,0,tien);
        boolean kiemtra = phieuMuonDAO.themPhieuMuon(phieuMuon);
        if (kiemtra){
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else{
            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

        }

    }
}