package com.example.bthea.htttsvtdt.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobi on 5/3/2017.
 */

public class hocky {

    private String tenHocki;
    private int year;
    private int hocky;

    public hocky(String tenHocki, int year, int hocky) {
        this.tenHocki = tenHocki;
        this.year = year;
        this.hocky = hocky;
    }

    public String getTenHocki() {
        return tenHocki;
    }

    public void setTenHocki(String tenHocki) {
        this.tenHocki = tenHocki;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHocky() {
        return hocky;
    }

    public void setHocky(int hocky) {
        this.hocky = hocky;
    }

    public static List<hocky> getdata()
    {
        List<hocky> lsthocky = new ArrayList<>();
        lsthocky.add( new hocky("HK3/2016-2017",2016,3));
        lsthocky.add( new hocky("HK2/2016-2017",2016,2));
        lsthocky.add( new hocky("HK1/2016-2017",2016,1));
        lsthocky.add( new hocky("HK2/2015-2016",2015,2));

        return  lsthocky;
    }
    @Override
    public String toString() {
        return getTenHocki();
    }
}
