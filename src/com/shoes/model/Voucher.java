/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class Voucher {
    private String IDVoucher;
    private String MaVoucher;
    private float PhanTramGiamGia;
    private int TrangThai;

    public Voucher() {
    }

    public Voucher(String IDVoucher, String MaVoucher, float PhanTramGiamGia, int TrangThai) {
        this.IDVoucher = IDVoucher;
        this.MaVoucher = MaVoucher;
        this.PhanTramGiamGia = PhanTramGiamGia;
        this.TrangThai = TrangThai;
    }

    public String getIDVoucher() {
        return IDVoucher;
    }

    public void setIDVoucher(String IDVoucher) {
        this.IDVoucher = IDVoucher;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public float getPhanTramGiamGia() {
        return PhanTramGiamGia;
    }

    public void setPhanTramGiamGia(float PhanTramGiamGia) {
        this.PhanTramGiamGia = PhanTramGiamGia;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
    
}
