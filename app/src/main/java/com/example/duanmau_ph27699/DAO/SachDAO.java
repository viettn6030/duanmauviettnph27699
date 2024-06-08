package com.example.duanmau_ph27699.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph27699.Model.Sach;
import com.example.duanmau_ph27699.database.DbHelper;

import java.util.ArrayList;

public class SachDAO {
    DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= dbHelper.getReadableDatabase();
        Cursor cursor   = sqLiteDatabase.rawQuery("SELECT * FROM SACH",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
