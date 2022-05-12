package com.example.sqliteeee;

public class Mon {
    int maMon;
    String tenMon;
    double gia;
//một lớp đối tượng món

    public Mon(int maMon, String tenMon, double gia) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.gia = gia;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
