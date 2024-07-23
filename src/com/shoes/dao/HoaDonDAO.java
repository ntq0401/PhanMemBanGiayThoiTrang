/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.HoaDon;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ntq04
 */
public class HoaDonDAO{

    public boolean insert(HoaDon hd) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC AddHD ?,?,?,?");
            stm.setString(1, hd.getKhachHang());
            stm.setString(2, hd.getNhanVien());
            stm.setString(3,hd.getPhuongthuc());
            stm.setString(4,hd.getKhuyenmai());           
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(int tt,int id) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update hoadon set trangthai = ? where idhoadon = ?");
            stm.setInt(1, tt);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from View_HoaDonInfo");
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKH = rs.getString(2);
                String tenNV = rs.getString(3);
                String tenPT = rs.getString(4);
                String tenKM = rs.getString(5);
                float tongTien = rs.getFloat(6);
                String ngayT = rs.getString(7);     
                int tt = rs.getInt(8);
                list.add(new HoaDon(id, tenNV, tenKH, tenPT, tenKM, tongTien, ngayT, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<HoaDon> getSelect(int tt) {
        List<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Select * from View_HoaDonInfo where trangthai = ?");
            stm.setInt(1, tt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKH = rs.getString(2);
                String tenNV = rs.getString(3);
                String tenPT = rs.getString(4);
                String tenKM = rs.getString(5);
                float tongTien = rs.getFloat(6);
                String ngayT = rs.getString(7);     
                int tthai = rs.getInt(8);
                list.add(new HoaDon(id, tenNV, tenKH, tenPT, tenKM, tongTien, ngayT, tthai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<HoaDon> getSelectByID(int id) {
        List<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Select * from View_HoaDonInfo where idHoaDon = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int idHD = rs.getInt(1);
                String tenKH = rs.getString(2);
                String tenNV = rs.getString(3);
                String tenPT = rs.getString(4);
                String tenKM = rs.getString(5);
                float tongTien = rs.getFloat(6);
                String ngayT = rs.getString(7);     
                int tthai = rs.getInt(8);
                list.add(new HoaDon(idHD, tenNV, tenKH, tenPT, tenKM, tongTien, ngayT, tthai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean thanhToan(int id){
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Update hoadon set trangthai = 1 where idhoadon = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            conn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
