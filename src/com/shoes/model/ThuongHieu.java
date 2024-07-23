/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class ThuongHieu {
    private String IDThuongHieu;
    private String TenThuongHieu;
    private String Mota;
    private int TrangThai;

    public ThuongHieu() {
    }

    public ThuongHieu(String IDThuongHieu, String TenThuongHieu, String Mota, int TrangThai) {
        this.IDThuongHieu = IDThuongHieu;
        this.TenThuongHieu = TenThuongHieu;
        this.Mota = Mota;
        this.TrangThai = TrangThai;
    }

    public String getIDThuongHieu() {
        return IDThuongHieu;
    }

    public void setIDThuongHieu(String IDThuongHieu) {
        this.IDThuongHieu = IDThuongHieu;
    }

    public String getTenThuongHieu() {
        return TenThuongHieu;
    }

    public void setTenThuongHieu(String TenThuongHieu) {
        this.TenThuongHieu = TenThuongHieu;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}
