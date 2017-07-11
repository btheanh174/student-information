package com.example.bthea.htttsvtdt.Objects.kqht;

/**
 * Created by Tobi on 5/13/2017.
 */

public class DiemMonHoc {
    private String MaMH;
    private String TenMH;
    private int soTinChi;
    private String diem1;
    private String diem2;
    private String giuaky;
    private String cuoiky;
    private String tongket;

    public DiemMonHoc(String maMH, String tenMH, int soTinChi, String diem1, String diem2, String giuaky, String cuoiky, String tongket) {
        MaMH = maMH;
        TenMH = tenMH;
        this.soTinChi = soTinChi;
        this.diem1 = diem1;
        this.diem2 = diem2;
        this.giuaky = giuaky;
        this.cuoiky = cuoiky;
        this.tongket = tongket;
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

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getDiem1() {
        return diem1;
    }

    public void setDiem1(String diem1) {
        this.diem1 = diem1;
    }

    public String getDiem2() {
        return diem2;
    }

    public void setDiem2(String diem2) {
        this.diem2 = diem2;
    }

    public String getGiuaky() {
        return giuaky;
    }

    public void setGiuaky(String giuaky) {
        this.giuaky = giuaky;
    }

    public String getCuoiky() {
        return cuoiky;
    }

    public void setCuoiky(String cuoiky) {
        this.cuoiky = cuoiky;
    }

    public String getTongket() {
        return tongket;
    }

    public void setTongket(String tongket) {
        this.tongket = tongket;
    }
}
