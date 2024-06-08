package com.example.duanmau_ph27699.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph27699.Model.LoaiSach;
import com.example.duanmau_ph27699.database.DbHelper;

import java.util.ArrayList;

public class LoaiSachDAO {
    DbHelper dbHelper;
    public LoaiSachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiSach> getLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH",null);
        if (cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("tenloai",tenLoai);
        long check = sqLiteDatabase.insert("LOAISACH", null,contentValues);
        if (check == -1){
            return false;

        }return true;
    }
    public int xoaSach(int  id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor   = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH WHERE maloai = ? ", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0 ){
            return -1;

        }
        long check = sqLiteDatabase.delete("LOAISACH", "maloai =?",new String[]{String.valueOf(id)});
        if (check == -1){
            return 0;
        }

        return 1;
    }
}
