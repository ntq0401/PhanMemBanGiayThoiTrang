/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.KichCo;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class KichCoDAO extends SHOESDAO<KichCo, Object> {

    @Override
    public boolean insert(KichCo kc) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into KichCo (idKichCo,tenKichCo,ngaytao,ngaysua,trangthai) values (?,?,getdate(),getdate(),?)");
            stm.setString(1, kc.getIDKichCo());
            stm.setString(2, kc.getTenKichCo());
            stm.setInt(3, kc.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(KichCo kc) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update KichCo set tenKichCo =?,ngaysua = getdate(), trangthai = ? where idKichCo = ?");
            stm.setString(1, kc.getTenKichCo());
            stm.setInt(2, kc.getTrangThai());
            stm.setString(3, kc.getIDKichCo());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<KichCo> getAll() {

        List<KichCo> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idKichCo,tenKichCo,trangthai from KichCo");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                int trangThai = rs.getInt(3);
                list.add(new KichCo(id, ten, trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
