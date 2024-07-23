/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.KhachHang;
import com.shoes.utils.JDBCHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duck1IDKhachHang"));
 */
public class showdataKhachHang {

    public List<KhachHang> showdata() {
        List<KhachHang> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT IDKhachHang,TenKhachHang,SoDienThoai,DiaChi,Email,NgaySinh,GioiTinh,TrangThai  from KhachHang");
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIDKhachHang(rs.getString("IDKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setGioiTinh(rs.getInt("GioiTinh"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                list.add(kh);
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
