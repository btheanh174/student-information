package com.example.bthea.htttsvtdt.Objects.hdqt;

/**
 * Created by Tobi on 5/13/2017.
 */

public class KhungDiem {

    private String muc;
    private String name;
    private String maxdiem;
    private String hk;
    private String year;

    public KhungDiem(String muc, String name, String maxdiem, String hk, String year) {
        this.muc = muc;
        this.name = name;
        this.maxdiem = maxdiem;
        this.hk = hk;
        this.year = year;
    }

    public String getMuc() {
        return muc;
    }

    public void setMuc(String muc) {
        this.muc = muc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxdiem() {
        return maxdiem;
    }

    public void setMaxdiem(String maxdiem) {
        this.maxdiem = maxdiem;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
