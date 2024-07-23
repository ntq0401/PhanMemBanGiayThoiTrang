/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.SanPhamChiTiet;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thitb
 */
public class ShowdataSanPham {

    public List<SanPhamChiTiet> showSPCT(String Ten) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
             Connection conn = JDBCHelper.getConnection();
            String sql = "SELECT IDChiTietSanPham, IDSanPham, TenSanPham, TenThuongHieu,TenNhaCungCap,TenMauSac,TenChatLieu,TenKieuDang,TenPhongCach,TenKichCo, MoTa,GiaBan,SoLuong,TrangThai FROM VIEW_CTSP";
            Statement St = conn.createStatement();
            ResultSet rs = St.executeQuery(sql);

            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setIDChiTietSanPham(rs.getString("IDChiTietSanPham"));
                sanPhamChiTiet.setIDsanPham(rs.getString("IDSanPham"));
                sanPhamChiTiet.setTensp(rs.getString("TenSanPham"));
                sanPhamChiTiet.setThuonghieu(rs.getString("TenThuongHieu"));
                sanPhamChiTiet.setNhacungcap(rs.getString("TenNhaCungCap"));
                sanPhamChiTiet.setMauSac(rs.getString("TenMauSac"));
                sanPhamChiTiet.setChatLieu(rs.getString("TenChatLieu"));
                sanPhamChiTiet.setKieuDang(rs.getString("TenKieuDang"));
                sanPhamChiTiet.setPhongcach(rs.getString("TenPhongCach"));
                sanPhamChiTiet.setKichco(rs.getString("TenKichCo"));
                sanPhamChiTiet.setMoTa(rs.getString("MoTa"));
                sanPhamChiTiet.setGiaBan(rs.getFloat("GiaBan"));
                sanPhamChiTiet.setSoLuong(rs.getInt("SoLuong"));
                sanPhamChiTiet.setTrangThai(rs.getInt("TrangThai"));
          
                list.add(sanPhamChiTiet);
                        }
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
