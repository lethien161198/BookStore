package com.example.bookstore.model;

public class Loaisp {
    public int id;
    public String Tenloaisp;
    public String Hinhanhloaisp;

    public Loaisp(int id, String tenloaisp, String hinhanhloaisp) {
        this.id = id;
        Tenloaisp = tenloaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaisp() {
        return Tenloaisp;
    }

    public void setTenLoaisp(String tenLoaisp) {
        Tenloaisp = tenLoaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }
}
