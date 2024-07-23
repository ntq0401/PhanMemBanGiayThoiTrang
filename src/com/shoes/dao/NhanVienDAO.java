/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.NhanVien;
import com.shoes.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ntq04
 */
public class NhanVienDAO extends SHOESDAO<NhanVien, Object> {

    @Override
    public boolean insert(NhanVien nv) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into nhanvien (idnhanvien,tennhanvien,gioitinh,email,chucvu, ngaysinh,diachi, dienthoai,taikhoan,matkhau,ngaytao,ngaysua,trangthai) values (?,?,?,?,?,?,?,?,?,?,getdate(),getdate(),?)");
            stm.setString(1, nv.getIDNhanVien());
            stm.setString(2, nv.getTenNhanVien());
            stm.setInt(3, nv.getGioiTinh());
            stm.setString(4, nv.getEmail());
            stm.setInt(5, nv.getChucVu());
            stm.setString(6, nv.getNgaySinh());
            stm.setString(7, nv.getDiaChi());
            stm.setString(8, nv.getDienThoai());
            stm.setString(9, nv.getTaiKhoan());
            stm.setString(10, nv.getMatKhau());
            stm.setInt(11, nv.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(NhanVien nv) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update nhanvien set tennhanvien = ?,gioitinh = ?, email = ?, chucvu = ? , ngaysinh = ?, diachi= ?, dienthoai = ?, taikhoan = ?,matkhau = ?, ngaytao = getdate(),trangthai = ? where idnhanvien = ?");
            stm.setString(1, nv.getTenNhanVien());
            stm.setInt(2, nv.getGioiTinh());
            stm.setString(3, nv.getEmail());
            stm.setInt(4, nv.getChucVu());
            stm.setString(5, nv.getNgaySinh());
            stm.setString(6, nv.getDiaChi());
            stm.setString(7, nv.getDienThoai());
            stm.setString(8, nv.getTaiKhoan());
            stm.setString(9, nv.getMatKhau());
            stm.setInt(10, nv.getTrangThai());
            stm.setString(11, nv.getIDNhanVien());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idnhanvien,tennhanvien,gioitinh,email,chucvu, ngaysinh,diachi, dienthoai,taikhoan,matkhau, trangthai from nhanvien");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                int gioitinh = rs.getInt(3);
                String email = rs.getString(4);
                int chucvu = rs.getInt(5);
                String ngaysinh = rs.getString(6);
                String diachi = rs.getString(7);
                String dienthoai = rs.getString(8);
                String taikhoan = rs.getString(9);
                String matkhau = rs.getString(10);
                int trangthai = rs.getInt(11);
                list.add(new NhanVien(id, ten, gioitinh, email, chucvu, ngaysinh, diachi, dienthoai, taikhoan, matkhau, trangthai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVien> search(String id,String ten,String email) throws SQLException {

       ArrayList<NhanVien> listnv= new ArrayList<>();
        try {
            Connection con = JDBCHelper.getConnection();

            String sqlString = "SELECT IDNhanVien,TenNhanVien,GioiTinh,Email,ChucVu,NgaySinh,DiaChi,DienThoai,TaiKhoan,MatKhau,TrangThai FROM NhanVien WHERE IDNhanVien LIKE ? or TenNhanVien like ? or Email like ? ";
            PreparedStatement ps = con.prepareStatement(sqlString);
            ps.setString(1, "%" + id+"%");
            ps.setString(2, "%" + ten+"%");
            ps.setString(3, "%" + email+"%");
            //ps.setDate(3, new java.sql.Date(dateStart.getTime()));
            
            
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                //IDNhanVien,TenNhanVien,GioiTinh,Email,ChucVu,NgaySinh,DiaChi,DienThoai,TaiKhoan,MatKhau,TrangThai
                NhanVien nv= new NhanVien();
                nv.setIDNhanVien(rs.getString("IDNhanVien"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getInt("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getInt("ChucVu"));
                nv.setNgaySinh(rs.getString("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setTaiKhoan(rs.getString("TaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                 
                listnv.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listnv;

    }
    public NhanVien selectByID(String id){
        List<NhanVien> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Select idnhanvien,tennhanvien,gioitinh,email,chucvu, ngaysinh,diachi, dienthoai,taikhoan,matkhau, trangthai from nhanvien where idnhanvien = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                int gioitinh = rs.getInt(3);
                String email = rs.getString(4);
                int chucvu = rs.getInt(5);
                String ngaysinh = rs.getString(6);
                String diachi = rs.getString(7);
                String dienthoai = rs.getString(8);
                String taikhoan = rs.getString(9);
                String matkhau = rs.getString(10);
                int trangthai = rs.getInt(11);
                list.add(new NhanVien(ma, ten, gioitinh, email, chucvu, ngaysinh, diachi, dienthoai, taikhoan, matkhau, trangthai));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
