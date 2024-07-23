/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.model;

/**
 *
 * @author ntq04
 */
public class ChatLieu {
    private String IDChatLieu;
    private String TenChatLieu;
    private int TrangThai;

    public ChatLieu() {
    }

    public ChatLieu(String IDChatLieu, String TenChatLieu, int TrangThai) {
        this.IDChatLieu = IDChatLieu;
        this.TenChatLieu = TenChatLieu;
        this.TrangThai = TrangThai;
    }

    public String getIDChatLieu() {
        return IDChatLieu;
    }

    public void setIDChatLieu(String IDChatLieu) {
        this.IDChatLieu = IDChatLieu;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}
