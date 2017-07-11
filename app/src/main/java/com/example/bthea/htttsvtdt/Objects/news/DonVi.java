package com.example.bthea.htttsvtdt.Objects.news;

/**
 * Created by nguye on 4/11/2017.
 */

public class DonVi {
    private String tenDonVi;
    private int soluongtin;

    public DonVi(String tenDonVi, int soluongtin) {

        this.tenDonVi = tenDonVi;
        this.soluongtin = soluongtin;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public int getSoluongtin() {
        return soluongtin;
    }

    public void setSoluongtin(int soluongtin) {
        this.soluongtin = soluongtin;
    }
}

