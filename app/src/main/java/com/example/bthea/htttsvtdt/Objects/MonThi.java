package com.example.bthea.htttsvtdt.Objects;

/**
 * Created by Tobi on 5/4/2017.
 */

public class MonThi {

    private String MaMH;
    private String TenMH;
    private String Ngaythi;
    private String GioThi;
    private String Nhomthi;
    private int Thư;
    private String PhongThi;
    private String Loaikythi;
    private int ThoiLuong;
    private String GhiChu;
    private String ToThi;

    public MonThi(String maMH, String tenMH, String ngaythi, String gioThi, String nhomthi, int thư, String phongThi, String loaikythi, int thoiLuong, String ghiChu, String toThi) {
        MaMH = maMH;
        TenMH = tenMH;
        Ngaythi = ngaythi;
        GioThi = gioThi;
        Nhomthi = nhomthi;
        Thư = thư;
        PhongThi = phongThi;
        Loaikythi = loaikythi;
        ThoiLuong = thoiLuong;
        GhiChu = ghiChu;
        ToThi = toThi;
    }


    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public String getNgaythi() {
        return Ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        Ngaythi = ngaythi;
    }

    public String getGioThi() {
        return GioThi;
    }

    public void setGioThi(String gioThi) {
        GioThi = gioThi;
    }

    public String getNhomthi() {
        return Nhomthi;
    }

    public void setNhomthi(String nhomthi) {
        Nhomthi = nhomthi;
    }

    public int getThư() {
        return Thư;
    }

    public void setThư(int thư) {
        Thư = thư;
    }

    public String getPhongThi() {
        return PhongThi;
    }

    public void setPhongThi(String phongThi) {
        PhongThi = phongThi;
    }

    public String getLoaikythi() {
        return Loaikythi;
    }

    public void setLoaikythi(String loaikythi) {
        Loaikythi = loaikythi;
    }

    public int getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        ThoiLuong = thoiLuong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getToThi() {
        return ToThi;
    }

    public void setToThi(String toThi) {
        ToThi = toThi;
    }
}
