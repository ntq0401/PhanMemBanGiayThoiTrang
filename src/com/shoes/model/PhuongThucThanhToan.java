/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class PhuongThucThanhToan {
    private String IDPhuongThuc;
    private String TenPhuongThuc;
    private int TrangThai;

    public PhuongThucThanhToan() {
    }

    public PhuongThucThanhToan(String IDPhuongThuc, String TenPhuongThuc, int TrangThai) {
        this.IDPhuongThuc = IDPhuongThuc;
        this.TenPhuongThuc = TenPhuongThuc;
        this.TrangThai = TrangThai;
    }

    public String getIDPhuongThuc() {
        return IDPhuongThuc;
    }

    public void setIDPhuongThuc(String IDPhuongThuc) {
        this.IDPhuongThuc = IDPhuongThuc;
    }

    public String getTenPhuongThuc() {
        return TenPhuongThuc;
    }

    public void setTenPhuongThuc(String TenPhuongThuc) {
        this.TenPhuongThuc = TenPhuongThuc;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
}


    