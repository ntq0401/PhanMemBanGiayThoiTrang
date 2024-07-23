/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.SanPham;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ntq04
 */
public class SanPhamDAO extends SHOESDAO<SanPham, Object> {

    @Override
    public boolean insert(SanPham sp) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC InsertSanPham ?,?,?,?,?");
            stm.setString(1, sp.getIDSanPham());
            stm.setString(2, sp.getThuongHieu());
            stm.setString(3, sp.getNhaCungCap());
            stm.setString(4, sp.getTenSanPham());
            stm.setInt(5, sp.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(SanPham sp) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC UpdateSanPham ?,?,?,?,?");
            stm.setString(1, sp.getIDSanPham());
            stm.setString(2, sp.getThuongHieu());
            stm.setString(3, sp.getNhaCungCap());
            stm.setString(4, sp.getTenSanPham());
            stm.setInt(5, sp.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select idsanpham,tenthuonghieu,tennhacungcap,tensanpham, sanpham.trangthai from sanpham join thuonghieu on SanPham.IDThuongHieu=ThuongHieu.IDThuongHieu join NhaCungCap on SanPham.IDNhaCungCap=NhaCungCap.IDNhaCungCap");
            while (rs.next()) {
                String id = rs.getString(1);
                String th = rs.getString(2);
                String ncc = rs.getString(3);
                String tensp = rs.getString(4);
                int tt = rs.getInt(5);
                list.add(new SanPham(id, th, ncc, tensp, tt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
