/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.NhaCungCap;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class NhaCungCapDAO extends SHOESDAO<NhaCungCap, Object> {

    @Override
    public boolean insert(NhaCungCap n) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into NhaCungCap (idNhaCungCap,tenNhaCungCap,DiaChi,SoDT,Email,ngaytao,ngaysua,trangthai) values (?,?,?,?,?,getdate(),getdate(),?)");
            stm.setString(1, n.getIDNhaCungCap());
            stm.setString(2,n.getTenNhaCungCap() );
            stm.setString(3, n.getDiaChi());
            stm.setString(4,n.getSoDT() );
            stm.setString(5, n.getEmail());
            stm.setInt(6, n.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(NhaCungCap n) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update NhaCungCap set tenNhaCungCap = ?,DiaChi = ?,SoDT = ?,Email = ?,ngaysua = getdate(),trangthai = ? where idNhaCungCap = ?");
            stm.setString(1,n.getTenNhaCungCap() );
            stm.setString(2, n.getDiaChi());
            stm.setString(3,n.getSoDT() );
            stm.setString(4, n.getEmail());
            stm.setInt(5, n.getTrangThai());
            stm.setString(6, n.getIDNhaCungCap());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idNhaCungCap,tenNhaCungCap,DiaChi,SoDT,Email,trangthai from NhaCungCap");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                String diaChi = rs.getString(3);
                String soDt = rs.getString(4);
                String email = rs.getString(5);
                int trangThai = rs.getInt(6);
                list.add(new NhaCungCap(id, ten, diaChi, soDt, email, trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
