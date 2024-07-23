/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author THEQUANG
 */
public class PhongCach {
    private String IDPhongCach;
    private String tenPhongCach;
    private int trangThai;

    public PhongCach() {
    }

    public PhongCach(String IDPhongCach, String tenPhongCach, int trangThai) {
        this.IDPhongCach = IDPhongCach;
        this.tenPhongCach = tenPhongCach;
        this.trangThai = trangThai;
    }

    public String getIDPhongCach() {
        return IDPhongCach;
    }

    public void setIDPhongCach(String IDPhongCach) {
        this.IDPhongCach = IDPhongCach;
    }

    public String getTenPhongCach() {
        return tenPhongCach;
    }

    public void setTenPhongCach(String tenPhongCach) {
        this.tenPhongCach = tenPhongCach;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
