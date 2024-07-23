/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.ThuongHieu;
import com.shoes.utils.JDBCHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class ThuongHieuDAO extends SHOESDAO<ThuongHieu, Object>{

    @Override
    public boolean insert(ThuongHieu t) {
try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into ThuongHieu (idThuongHieu,tenThuongHieu,mota,ngaytao,ngaysua,trangthai) values (?,?,?,getdate(),getdate(),?)");
            stm.setString(1, t.getIDThuongHieu() );
            stm.setString(2, t.getTenThuongHieu());
            stm.setString(3, t.getMota() );
            stm.setInt(4,t.getTrangThai() );
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ThuongHieu t) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update ThuongHieu set tenThuongHieu =?,mota = ?,ngaysua = getdate(), trangthai = ? where idThuongHieu = ?");
            stm.setString(1, t.getTenThuongHieu());
            stm.setString(2,t.getMota());
            stm.setInt(3, t.getTrangThai());
            stm.setString(4, t.getIDThuongHieu());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ThuongHieu> getAll() {
        List<ThuongHieu> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idThuongHieu,tenThuongHieu,mota,trangthai from ThuongHieu");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                String mota = rs.getString(3);
                int trangThai = rs.getInt(4);
                list.add(new ThuongHieu(id, ten, mota, trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
