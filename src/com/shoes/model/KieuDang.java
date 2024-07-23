/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class KieuDang {
    private String IDKieuDang;
    private String TenKieuDang;
    private int TrangThai;

    public KieuDang() {
    }

    public KieuDang(String IDKieuDang, String TenKieuDang, int TrangThai) {
        this.IDKieuDang = IDKieuDang;
        this.TenKieuDang = TenKieuDang;
        this.TrangThai = TrangThai;
    }

    public String getIDKieuDang() {
        return IDKieuDang;
    }

    public void setIDKieuDang(String IDKieuDang) {
        this.IDKieuDang = IDKieuDang;
    }

    public String getTenKieuDang() {
        return TenKieuDang;
    }

    public void setTenKieuDang(String TenKieuDang) {
        this.TenKieuDang = TenKieuDang;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

   
}
