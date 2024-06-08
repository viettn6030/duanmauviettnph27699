package com.example.duanmau_ph27699.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph27699.Fragment.PhieuMuonFragment;
import com.example.duanmau_ph27699.Model.PhieuMuon;
import com.example.duanmau_ph27699.database.DbHelper;

import java.util.ArrayList;

public class PhieuMuonDAO {
    DbHelper dbHelper;
    public PhieuMuonDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<PhieuMuon> getDSPhieuMuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT PHIEUMUON.mapm, PHIEUMUON.matv, THANHVIEN.hoten, PHIEUMUON.matt, THUTHU.hoten, PHIEUMUON.masach, SACH.tensach, PHIEUMUON.ngay, PHIEUMUON.trasach, PHIEUMUON.tienthue FROM PHIEUMUON, THANHVIEN, THUTHU, SACH  ",null);
        String query = "SELECT * from PHIEUMUON ORDER BY PHIEUMUON.mapm DESC ";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2), cursor.getInt(3),cursor.getString(4),cursor.getInt(5), cursor.getInt(6)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("mapm",phieuMuon.getMapm());
        contentValues.put("matv",phieuMuon.getMatv());
        contentValues.  put("matt",phieuMuon.getMatt());
        contentValues.put("masach",phieuMuon.getMasach());
        contentValues.  put("ngay",phieuMuon.getNgay());
        contentValues.  put("trasach",phieuMuon.getTrasach());
        contentValues.  put("tienthue",phieuMuon.getTienthue());
        long check = sqLiteDatabase.insert("PHIEUMUON", null,contentValues);
        if (check == -1 ){
            return false;
        }return true;
    }
    
    public boolean thayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("trasach",1);
        long check = sqLiteDatabase.update("PHIEUMUON",contentValues,"mapm = ?",new String[]{String.valueOf(mapm)});
        if (check == -1){
            return false;
        }
        return true;
    }

}
