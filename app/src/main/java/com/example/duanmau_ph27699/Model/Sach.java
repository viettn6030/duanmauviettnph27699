package com.example.duanmau_ph27699.Model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int maLoai;
    private int soluongmuon;

    public Sach(int maSach, String tenSach, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public Sach(int maSach, String tenSach, int soluongmuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soluongmuon = soluongmuon;
    }

    public int getSoluongmuon() {
        return soluongmuon;
    }

    public void setSoluongmuon(int soluongmuon) {
        this.soluongmuon = soluongmuon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
