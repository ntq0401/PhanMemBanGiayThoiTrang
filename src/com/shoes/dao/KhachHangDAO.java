/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.KhachHang;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ntq04
 */
public class KhachHangDAO extends SHOESDAO<KhachHang, Object> {

    @Override
    public boolean insert(KhachHang kh) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into khachhang (idkhachhang,tenkhachhang,sodienthoai, diachi,email, ngaysinh,gioitinh, ngaytao,ngaysua,trangthai) values (?,?,?,?,?,?,?,getdate(),getdate(),?)");
            stm.setString(1, kh.getIDKhachHang());
            stm.setString(2, kh.getTenKhachHang());
            stm.setString(3, kh.getSoDienThoai());
            stm.setString(4, kh.getDiaChi());
            stm.setString(5, kh.getEmail());
            stm.setString(6, kh.getNgaySinh());
            stm.setInt(7, kh.getGioiTinh());
            stm.setInt(8, kh.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(KhachHang kh) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update khachhang set tenkhachhang =?,sodienthoai=?, diachi=?,email=?, ngaysinh=?,gioitinh=?,ngaysua =getdate(),trangthai=? where idkhachhang=?");

            stm.setString(1, kh.getTenKhachHang());
            stm.setString(2, kh.getSoDienThoai());
            stm.setString(3, kh.getDiaChi());
            stm.setString(4, kh.getEmail());
            stm.setString(5, kh.getNgaySinh());
            stm.setInt(6, kh.getGioiTinh());
            stm.setInt(7, kh.getTrangThai());
            stm.setString(8, kh.getIDKhachHang());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idkhachhang,tenkhachhang,sodienthoai, diachi,email, ngaysinh,gioitinh,trangthai from khachhang");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String diachi = rs.getString(4);
                String email = rs.getString(5);
                String ngaysinh = rs.getString(6);
                int gioitinh = rs.getInt(7);
                int trangthai = rs.getInt(8);
                list.add(new KhachHang(id, ten, sdt, diachi, email, ngaysinh, gioitinh, trangthai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHang> timkiem(String ma, String ten) throws SQLDataException, SQLException {
        List<KhachHang> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            String sql = "SELECT  IDKhachHang,TenKhachHang,SoDienThoai,DiaChi,Email,NgaySinh,GioiTinh,TrangThai from KhachHang WHERE IDKhachHang like  ? or TenKhachHang like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            ps.setString(2, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return list;
    }
    
    public boolean insertNhanh(String ten, String sdt) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("EXEC THEMNHANH ?,?");
            stm.setString(1, ten);
            stm.setString(2, sdt);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
