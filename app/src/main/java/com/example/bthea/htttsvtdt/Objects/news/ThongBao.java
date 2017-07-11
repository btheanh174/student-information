package com.example.bthea.htttsvtdt.Objects.news;

/**
 * Created by nguye on 4/12/2017.
 */

public class ThongBao {
    private int id;
    private boolean isread;
    private String tieude;

    public ThongBao(int id, boolean isread, String tieude) {
        this.id = id;
        this.isread = isread;
        this.tieude = tieude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
}
