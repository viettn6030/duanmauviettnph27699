package com.example.duanmau_ph27699.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph27699.database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?",new String[]{matt,matkhau});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean doimatkhau(String user,String pass,String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{user,pass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau",newPass);
            long check = sqLiteDatabase.update("THUTHU",contentValues,"matt = ?", new String[]{user});
            if (check == -1){
                return false;
            }return true;
        }
return false;
    }
}
