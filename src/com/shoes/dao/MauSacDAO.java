/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.MauSac;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ntq04
 */
public class MauSacDAO extends SHOESDAO<MauSac, String>{

    @Override
    public boolean insert(MauSac m) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into mausac (idmausac,tenmausac,ngaytao,ngaysua,trangthai) values (?,?,getdate(),getdate(),?)");
            stm.setString(1, m.getIDMauSac());
            stm.setString(2, m.getTenMauSac());
            stm.setInt(3, m.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(MauSac m) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update MauSac set tenmausac =?,ngaysua = getdate(), trangthai = ? where idmausac = ?");
            stm.setString(1, m.getTenMauSac());
            stm.setInt(2, m.getTrangThai());
            stm.setString(3, m.getIDMauSac());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<MauSac> getAll() {
        List<MauSac> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idmausac,tenmausac,trangthai from MauSac");
            while (rs.next()) {                
               String id = rs.getString(1);
               String ten = rs.getString(2);
               int trangThai = rs.getInt(3);
               list.add(new MauSac(id, ten, trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
