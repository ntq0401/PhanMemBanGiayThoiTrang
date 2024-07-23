/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class SanPham {
    private String IDSanPham;
    private String thuongHieu;
    private String nhaCungCap;
    private String TenSanPham;
    private int TrangThai;

    public SanPham() {
    }

    public SanPham(String IDSanPham, String thuongHieu, String nhaCungCap, String TenSanPham, int TrangThai) {
        this.IDSanPham = IDSanPham;
        this.thuongHieu = thuongHieu;
        this.nhaCungCap = nhaCungCap;
        this.TenSanPham = TenSanPham;
        this.TrangThai = TrangThai;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}
