/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.PhongCach;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class PhongCachDAO extends SHOESDAO<PhongCach, Object> {

    @Override
    public boolean insert(PhongCach pc) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into PhongCach (idPhongCach,tenPhongCach,ngaytao,ngaysua,trangthai) values (?,?,getdate(),getdate(),?)");
            stm.setString(1, pc.getIDPhongCach());
            stm.setString(2, pc.getTenPhongCach());
            stm.setInt(3, pc.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PhongCach pc) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update PhongCach set tenPhongCach =?,ngaysua = getdate(), trangthai = ? where idPhongCach = ?");
            stm.setString(1, pc.getTenPhongCach());
            stm.setInt(2, pc.getTrangThai());
            stm.setString(3, pc.getIDPhongCach());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PhongCach> getAll() {
        List<PhongCach> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idPhongCach,tenPhongCach,trangthai from PhongCach");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                int trangThai = rs.getInt(3);
                list.add(new PhongCach(id, ten, trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
