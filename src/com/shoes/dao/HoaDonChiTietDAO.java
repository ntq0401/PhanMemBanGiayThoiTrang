/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.ChiTietHoaDon;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ntq04
 */
public class HoaDonChiTietDAO {

    public List<ChiTietHoaDon> getSelect(int idHD) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT [IDChiTietHoaDon],[IDHoaDon] ,[ChiTietHoaDon].[IDChiTietSanPham] ,[TenSanPham] ,[ChiTietHoaDon].[SoLuong],ChiTietSanPham.GiaBan FROM [FASHION_SHOES].[dbo].[ChiTietHoaDon] join ChiTietSanPham on ChiTietHoaDon.IDChiTietSanPham=ChiTietSanPham.IDChiTietSanPham where IDHoaDon = ?");
            stm.setInt(1, idHD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int idct = rs.getInt(1);
                int idhd = rs.getInt(2);
                String idsp = rs.getString(3);
                String tensp = rs.getString(4);
                int sl = rs.getInt(5);
                float gia = rs.getFloat(6);
                list.add(new ChiTietHoaDon(idct, idhd, idsp, tensp, sl*gia, sl, gia));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete1SP(int idct,String idsp) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC XOA1SANPHAM ?,?");
            stm.setString(1, idsp);
            stm.setInt(2, idct);
            stm.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
