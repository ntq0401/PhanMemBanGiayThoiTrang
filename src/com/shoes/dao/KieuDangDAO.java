/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.KieuDang;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class KieuDangDAO extends SHOESDAO<KieuDang, Object>{

    @Override
    public boolean insert(KieuDang k) {
    try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into KieuDang (idKieuDang,tenKieuDang,ngaytao,ngaysua,trangthai) values (?,?,getdate(),getdate(),?)");
            stm.setString(1, k.getIDKieuDang());
            stm.setString(2, k.getTenKieuDang());
            stm.setInt(3, k.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(KieuDang k) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update KieuDang set tenKieuDang =?,ngaysua = getdate(), trangthai = ? where idKieuDang = ?");
            stm.setString(1, k.getTenKieuDang());
            stm.setInt(2, k.getTrangThai());
            stm.setString(3, k.getIDKieuDang());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<KieuDang> getAll() {
        List<KieuDang> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idKieuDang,tenKieuDang,trangthai from KieuDang");
            while (rs.next()) {                
               String id = rs.getString(1);
               String ten = rs.getString(2);
               int trangThai = rs.getInt(3);
               list.add(new KieuDang(id, ten,trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
