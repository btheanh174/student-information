package com.example.bthea.htttsvtdt.Objects.hdqt;

/**
 * Created by Tobi on 5/20/2017.
 */

public class DiemHoatDong {
    private String Diem;
    private String DiemTongKet;
    private String KetQua;
    private String MucID;
    private String Namvaotruong;
    private String NoiDung;
    private String Khoa;

    public DiemHoatDong(String diem, String diemTongKet, String ketQua, String mucID, String namvaotruong, String noiDung, String khoa) {
        Diem = diem;
        DiemTongKet = diemTongKet;
        KetQua = ketQua;
        MucID = mucID;
        Namvaotruong = namvaotruong;
        NoiDung = noiDung;
        Khoa = khoa;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }

    public String getDiemTongKet() {
        return DiemTongKet;
    }

    public void setDiemTongKet(String diemTongKet) {
        DiemTongKet = diemTongKet;
    }

    public String getKetQua() {
        return KetQua;
    }

    public void setKetQua(String ketQua) {
        KetQua = ketQua;
    }

    public String getMucID() {
        return MucID;
    }

    public void setMucID(String mucID) {
        MucID = mucID;
    }

    public String getNamvaotruong() {
        return Namvaotruong;
    }

    public void setNamvaotruong(String namvaotruong) {
        Namvaotruong = namvaotruong;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }
}
