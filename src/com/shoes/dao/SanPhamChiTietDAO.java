/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.SanPhamChiTiet;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ntq04
 */
public class SanPhamChiTietDAO extends SHOESDAO<SanPhamChiTiet, Object> {

    @Override
    public boolean insert(SanPhamChiTiet ct) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC InsertChiTietSanPham ?,?,?,?,?,?,?,?,?,?,?");
            stm.setString(1, ct.getIDChiTietSanPham());
            stm.setString(2, ct.getIDsanPham());
            stm.setString(3, ct.getMauSac());
            stm.setString(4, ct.getChatLieu());
            stm.setString(5, ct.getKieuDang());
            stm.setString(6, ct.getPhongcach());
            stm.setString(7, ct.getKichco());
            stm.setString(8, ct.getMoTa());
            stm.setFloat(9, ct.getGiaBan());
            stm.setInt(10, ct.getSoLuong());
            stm.setInt(11, ct.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(SanPhamChiTiet ct) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC UpdateChiTietSanPham ?,?,?,?,?,?,?,?,?,?,?");
            stm.setString(1, ct.getIDsanPham());
            stm.setString(2, ct.getMauSac());
            stm.setString(3, ct.getChatLieu());
            stm.setString(4, ct.getKieuDang());
            stm.setString(5, ct.getPhongcach());
            stm.setString(6, ct.getKichco());
            stm.setString(7, ct.getMoTa());
            stm.setFloat(8, ct.getGiaBan());
            stm.setInt(9, ct.getSoLuong());
            stm.setInt(10, ct.getTrangThai());
            stm.setString(11, ct.getIDChiTietSanPham());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPhamChiTiet> getAll() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM VIEW_CTSP");
            while (rs.next()) {
                String idct = rs.getString(1);
                String idsp = rs.getString(2);
                String ten = rs.getString(3);
                String th = rs.getString(4);
                String ncc = rs.getString(5);
                String ms = rs.getString(6);
                String cl = rs.getString(7);
                String kd = rs.getString(8);
                String pc = rs.getString(9);
                String kc = rs.getString(10);
                String mt = rs.getString(11);
                float giaBan = rs.getFloat(12);
                int sl = rs.getInt(13);
                int tt = rs.getInt(14);
                list.add(new SanPhamChiTiet(idct, idsp, ten, th, ncc, ms, cl, kd, pc, kc, mt, giaBan, sl, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<SanPhamChiTiet> TimKiemgetAll(String Ten) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String querry = "SELECT * FROM VIEW_CTSP where IDSanPham =?";
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            PreparedStatement ps = conn.prepareCall(querry);
            ps.setObject(1, Ten);
            ResultSet rs = ps.executeQuery();
           
            
            while (rs.next()) {
                String idct = rs.getString(1);
                String idsp = rs.getString(2);
                String tensp = rs.getString(3);
                String th = rs.getString(4);
                String ncc = rs.getString(5);
                String ms = rs.getString(6);
                String cl = rs.getString(7);
                String kd = rs.getString(8);
                String pc = rs.getString(9);
                String kc = rs.getString(10);
                String mt = rs.getString(11);
                float giaBan = rs.getFloat(12);
                int sl = rs.getInt(13);
                int tt = rs.getInt(14);
                list.add(new SanPhamChiTiet(idct, idsp, tensp, th, ncc, ms, cl, kd, pc, kc, mt, giaBan, sl, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<SanPhamChiTiet> THgetAll(String Ten) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String querry = "SELECT * FROM VIEW_CTSP where TenThuongHieu =?";
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            PreparedStatement ps = conn.prepareCall(querry);
            ps.setObject(1, Ten);
            ResultSet rs = ps.executeQuery();
           
            
            while (rs.next()) {
                String idct = rs.getString(1);
                String idsp = rs.getString(2);
                String tensp = rs.getString(3);
                String th = rs.getString(4);
                String ncc = rs.getString(5);
                String ms = rs.getString(6);
                String cl = rs.getString(7);
                String kd = rs.getString(8);
                String pc = rs.getString(9);
                String kc = rs.getString(10);
                String mt = rs.getString(11);
                float giaBan = rs.getFloat(12);
                int sl = rs.getInt(13);
                int tt = rs.getInt(14);
                list.add(new SanPhamChiTiet(idct, idsp, tensp, th, ncc, ms, cl, kd, pc, kc, mt, giaBan, sl, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public List<SanPhamChiTiet> CLgetAll(String Ten) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String querry = "SELECT * FROM VIEW_CTSP where TenChatLieu =?";
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            PreparedStatement ps = conn.prepareCall(querry);
            ps.setObject(1, Ten);
            ResultSet rs = ps.executeQuery();
           
            
            while (rs.next()) {
                 String idct = rs.getString(1);
                String idsp = rs.getString(2);
                String tensp = rs.getString(3);
                String th = rs.getString(4);
                String ncc = rs.getString(5);
                String ms = rs.getString(6);
                String cl = rs.getString(7);
                String kd = rs.getString(8);
                String pc = rs.getString(9);
                String kc = rs.getString(10);
                String mt = rs.getString(11);
                float giaBan = rs.getFloat(12);
                int sl = rs.getInt(13);
                int tt = rs.getInt(14);
                list.add(new SanPhamChiTiet(idct, idsp, tensp, th, ncc, ms, cl, kd, pc, kc, mt, giaBan, sl, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public List<SanPhamChiTiet> MSgetAll(String Ten) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String querry = "SELECT * FROM VIEW_CTSP where TenMauSac =?";
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            PreparedStatement ps = conn.prepareCall(querry);
            ps.setObject(1, Ten);
            ResultSet rs = ps.executeQuery();
           
            
            while (rs.next()) {
                String idct = rs.getString(1);
                String idsp = rs.getString(2);
                String tensp = rs.getString(3);
                String th = rs.getString(4);
                String ncc = rs.getString(5);
                String ms = rs.getString(6);
                String cl = rs.getString(7);
                String kd = rs.getString(8);
                String pc = rs.getString(9);
                String kc = rs.getString(10);
                String mt = rs.getString(11);
                float giaBan = rs.getFloat(12);
                int sl = rs.getInt(13);
                int tt = rs.getInt(14);
                list.add(new SanPhamChiTiet(idct, idsp, tensp, th, ncc, ms, cl, kd, pc, kc, mt, giaBan, sl, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public List<SanPhamChiTiet> KDgetAll(String Ten) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String querry = "SELECT * FROM VIEW_CTSP where TenKieuDang =?";
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            PreparedStatement ps = conn.prepareCall(querry);
            ps.setObject(1, Ten);
            ResultSet rs = ps.executeQuery();
           
            
            while (rs.next()) {
                 String idct = rs.getString(1);
                String idsp = rs.getString(2);
                String tensp = rs.getString(3);
                String th = rs.getString(4);
                String ncc = rs.getString(5);
                String ms = rs.getString(6);
                String cl = rs.getString(7);
                String kd = rs.getString(8);
                String pc = rs.getString(9);
                String kc = rs.getString(10);
                String mt = rs.getString(11);
                float giaBan = rs.getFloat(12);
                int sl = rs.getInt(13);
                int tt = rs.getInt(14);
                list.add(new SanPhamChiTiet(idct, idsp, tensp, th, ncc, ms, cl, kd, pc, kc, mt, giaBan, sl, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}


