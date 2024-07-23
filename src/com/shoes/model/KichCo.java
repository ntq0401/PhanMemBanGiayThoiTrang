/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author THEQUANG
 */
public class KichCo {
    private String IDKichCo;
    private String tenKichCo;
    private int trangThai;

    public KichCo() {
    }

    public KichCo(String IDKichCo, String tenKichCo, int trangThai) {
        this.IDKichCo = IDKichCo;
        this.tenKichCo = tenKichCo;
        this.trangThai = trangThai;
    }

    public String getIDKichCo() {
        return IDKichCo;
    }

    public void setIDKichCo(String IDKichCo) {
        this.IDKichCo = IDKichCo;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
