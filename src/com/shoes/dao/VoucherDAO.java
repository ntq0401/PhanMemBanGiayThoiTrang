/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.Voucher;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class VoucherDAO extends SHOESDAO<Voucher, Object>{
    public List<Voucher> getAll() {
        List<Voucher> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idvoucher,mavoucher,phantramgiamgia,trangthai from voucher");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                float phanTram = rs.getFloat(3);
                int trangthai = rs.getInt(4);
                list.add(new Voucher(id, ten, phanTram, trangthai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(Voucher km) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("insert into voucher values (?,?,?,getdate(),getdate(),?)");
            stm.setString(1, km.getIDVoucher());
            stm.setString(2, km.getMaVoucher());
            stm.setFloat(3, km.getPhanTramGiamGia());
            stm.setInt(4, km.getTrangThai());
            stm.executeUpdate();
            conn.close();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Voucher km) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update Voucher set maVoucher = ?,phantramgiamgia = ?,ngaysua = getdate(),trangthai = ? where idVoucher = ?");
            stm.setString(1, km.getMaVoucher());
            stm.setFloat(2, km.getPhanTramGiamGia());
            stm.setInt(3, km.getTrangThai());
            stm.setString(4, km.getIDVoucher());
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
