package com.example.duanmau_ph27699.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph27699.Model.Sach;
import com.example.duanmau_ph27699.database.DbHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThongKeDAO {
    DbHelper dbHelper;
    public ThongKeDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= dbHelper.getReadableDatabase();
        Cursor cursor   =sqLiteDatabase.rawQuery("SELECT pm.masach, sc.tensach, COUNT(pm.masach) from PHIEUMUON pm, SACH sc WHERE pm.masach = sc.masach GROUP by pm.masach,sc.tensach ORDER by COUNT(pm.masach) DESC limit 10",null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
