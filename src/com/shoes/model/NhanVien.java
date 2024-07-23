/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class NhanVien {
    private String IDNhanVien;
    private String TenNhanVien;
    private int GioiTinh;
    private String Email;
    private int ChucVu;
    private String NgaySinh;
    private String DiaChi;
    private String DienThoai;
    private String TaiKhoan;
    private String MatKhau;
    private int TrangThai;

    public NhanVien(String IDNhanVien, String TenNhanVien, int GioiTinh, String Email, int ChucVu, String NgaySinh, String DiaChi, String DienThoai, String TaiKhoan, String MatKhau, int TrangThai) {
        this.IDNhanVien = IDNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.ChucVu = ChucVu;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.DienThoai = DienThoai;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.TrangThai = TrangThai;
    }

    public NhanVien() {
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}
