package com.example.bthea.htttsvtdt.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobi on 5/3/2017.
 */

public class namhoc {

    private String tennamhoc;
    private int year;

    public namhoc(String tennamhoc, int year) {
        this.tennamhoc = tennamhoc;
        this.year = year;
    }

    public String getTennamhoc() {
        return tennamhoc;
    }

    public void setTennamhoc(String tennamhoc) {
        this.tennamhoc = tennamhoc;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static List<namhoc> getdata()
    {
        List<namhoc> lstnamhoc = new ArrayList<>();
        lstnamhoc.add( new namhoc("2016-2017",2016));
        lstnamhoc.add( new namhoc("2015-2016",2015));
        lstnamhoc.add( new namhoc("2014-2015",2014));
        lstnamhoc.add( new namhoc("2013-2014",2013));

        return  lstnamhoc;
    }
    @Override
    public String toString() {
        return getTennamhoc();
    }
}
