package com.example.bthea.htttsvtdt.Objects.hdqt;

/**
 * Created by Tobi on 5/13/2017.
 */

public class DiemQuaTrinh {

    private String tenSukien;
    private String diemRL;
    private String thoigiantochuc;

    public DiemQuaTrinh(String tenSukien, String diemRL, String thoigiantochuc) {
        this.tenSukien = tenSukien;
        this.diemRL = diemRL;
        this.thoigiantochuc = thoigiantochuc;
    }

    public String getTenSukien() {
        return tenSukien;
    }

    public void setTenSukien(String tenSukien) {
        this.tenSukien = tenSukien;
    }

    public String getDiemRL() {
        return diemRL;
    }

    public void setDiemRL(String diemRL) {
        this.diemRL = diemRL;
    }

    public String getThoigiantochuc() {
        return thoigiantochuc;
    }

    public void setThoigiantochuc(String thoigiantochuc) {
        this.thoigiantochuc = thoigiantochuc;
    }
}
