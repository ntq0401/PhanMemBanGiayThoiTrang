/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class MauSac {
    private String IDMauSac;
    private String TenMauSac;
    private int TrangThai;

    public MauSac() {
    }

    public MauSac(String IDMauSac, String TenMauSac, int TrangThai) {
        this.IDMauSac = IDMauSac;
        this.TenMauSac = TenMauSac;
        this.TrangThai = TrangThai;
    }

    public String getIDMauSac() {
        return IDMauSac;
    }

    public void setIDMauSac(String IDMauSac) {
        this.IDMauSac = IDMauSac;
    }

    public String getTenMauSac() {
        return TenMauSac;
    }

    public void setTenMauSac(String TenMauSac) {
        this.TenMauSac = TenMauSac;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}
