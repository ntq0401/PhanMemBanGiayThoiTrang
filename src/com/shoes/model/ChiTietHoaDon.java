/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class ChiTietHoaDon {

    private int IDChiTietHoaDon;
    private int IDhoaDon;
    private String IDCTsanPham;
    private String tenSanPham;
    private float tongTien;
    private int SoLuong;
    private float donGia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int IDChiTietHoaDon, int IDhoaDon, String IDCTsanPham, String tenSanPham, float tongTien, int SoLuong, float donGia) {
        this.IDChiTietHoaDon = IDChiTietHoaDon;
        this.IDhoaDon = IDhoaDon;
        this.IDCTsanPham = IDCTsanPham;
        this.tenSanPham = tenSanPham;
        this.tongTien = tongTien;
        this.SoLuong = SoLuong;
        this.donGia = donGia;
    }

    public int getIDChiTietHoaDon() {
        return IDChiTietHoaDon;
    }

    public void setIDChiTietHoaDon(int IDChiTietHoaDon) {
        this.IDChiTietHoaDon = IDChiTietHoaDon;
    }

    public int getIDhoaDon() {
        return IDhoaDon;
    }

    public void setIDhoaDon(int IDhoaDon) {
        this.IDhoaDon = IDhoaDon;
    }

    public String getIDCTsanPham() {
        return IDCTsanPham;
    }

    public void setIDCTsanPham(String IDCTsanPham) {
        this.IDCTsanPham = IDCTsanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    
}
