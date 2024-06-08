package com.example.duanmau_ph27699.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "QuanLyThuVienn", null, 1);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //thu thu
        String tbThuThu =
                "CREATE TABLE THUTHU("+
                        "matt TEXT PRIMARY KEY,"+
                        "hoten TEXT ,"+
                        "matkhau TEXT )";
        db.execSQL(tbThuThu);
        db.execSQL("INSERT INTO THUTHU VALUES('thuthu1','tran van a','123123'),('thuthu2','tran van b','12341234')");
        // loai sach
        String tbLoaiSach =
                "CREATE TABLE LOAISACH("+
                        "maloai INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                        " tenloai TEXT )";
        db.execSQL(tbLoaiSach);
        db.execSQL("INSERT INTO LOAISACH VALUES(1,'thieu nhi'),(2,'hanh dong'),(3,'tinh cam')");
        //sach
        String tbSach =
                "CREATE TABLE SACH("+
                        "masach INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "tensach TEXT ,"+
                        "giathue INTEGER ,"+
                        "maloai INTEGER REFERENCES LOAISACH(maloai))";
        db.execSQL(tbSach);
        db.execSQL("INSERT INTO SACH VALUES(1,'DORAEMON 1',100000,1),(2,'DORAEMON 2',100000,2),(3,'DORAEMON 3',100000,3)");
        //thanh vien
        String tbThanhVien =
                "CREATE TABLE THANHVIEN("+
                        "matv INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "hoten TEXT ,"+
                        "namsinh TEXT )";
        db.execSQL(tbThanhVien);
        //phieu muon
        String tbPhieuMuon =
                "CREATE TABLE PHIEUMUON("+
                        "mapm INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "matv INTEGER REFERENCES THANHVIEN(maTV),"+
                        "matt TEXT REFERENCES THUTHU(matt),"+
                        "masach INTEGER REFERENCES SACH(maSach),"+
                        "ngay DATE ,"+
                        "trasach INTEGER ,"+
                        "tienthue INTEGER )";

        db.execSQL(tbPhieuMuon);
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/03/2022', 1 ,2500),(2,1,'thuthu01', 3, '19/03/2022',0 ,2000 ),(3,2,'thuthu02', 1, '19/03/2022', 1, 2000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            String dropTableThuThu = "drop table if exists THUTHU";
            db.execSQL(dropTableThuThu);
            String dropTableThanhVien = "drop table if exists THANHVIEN";
            db.execSQL(dropTableThanhVien);
            String dropTableLoaiSach = "drop table if exists LOAISACH";
            db.execSQL(dropTableLoaiSach);
            String dropTableSach = "drop table if exists SACH";
            db.execSQL(dropTableSach);
            String dropTablePhieuMuon = "drop table if exists PHIEUMUON";
            db.execSQL(dropTablePhieuMuon);
            onCreate(db);
        }
    }
}
