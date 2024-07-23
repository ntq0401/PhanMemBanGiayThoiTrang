/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class HoaDon {

    private int IDHoaDon;
    private String nhanVien;
    private String khachHang;
    private String phuongthuc;
    private String khuyenmai;
    private float TongTien;
    private String NgayTao;
    private int TrangThai;

    public HoaDon() {
    }

    public HoaDon(int IDHoaDon, String nhanVien, String khachHang, String phuongthuc, String khuyenmai, float TongTien, String NgayTao, int TrangThai) {
        this.IDHoaDon = IDHoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.phuongthuc = phuongthuc;
        this.khuyenmai = khuyenmai;
        this.TongTien = TongTien;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }

    public int getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(int IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(String phuongthuc) {
        this.phuongthuc = phuongthuc;
    }

    public String getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(String khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}
