package com.example.bthea.htttsvtdt.Objects;

/**
 * Created by Tobi on 5/3/2017.
 */

public class MonHoc {
    private String MaMH;
    private int Nhom;
    private String Phong;
    private int sotiet;
    private int tietbatdau;
    private int thu;
    private String tenMH;
    private String tuan;


    public MonHoc(String maMH, int nhom, String phong, int sotiet, int tietbatdau, int thu, String tenMH, String tuan) {
        MaMH = maMH;
        Nhom = nhom;
        Phong = phong;
        this.sotiet = sotiet;
        this.tietbatdau = tietbatdau;
        this.thu = thu;
        this.tenMH = tenMH;
        this.tuan = tuan;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public int getNhom() {
        return Nhom;
    }

    public void setNhom(int nhom) {
        Nhom = nhom;
    }

    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public int getSotiet() {
        return sotiet;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }

    public int getTietbatdau() {
        return tietbatdau;
    }

    public void setTietbatdau(int tietbatdau) {
        this.tietbatdau = tietbatdau;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getTuan() {
        return tuan;
    }

    public void setTuan(String tuan) {
        this.tuan = tuan;
    }
}

