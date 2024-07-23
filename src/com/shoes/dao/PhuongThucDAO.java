/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;
import com.shoes.model.PhuongThucThanhToan;
import com.shoes.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ntq04
 */
public class PhuongThucDAO {
    public List<PhuongThucThanhToan> getAll() {
        List<PhuongThucThanhToan> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idPhuongThuc,tenPhuongThuc,trangthai from phuongthucthanhtoan");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                int trangthai = rs.getInt(3);
                list.add(new PhuongThucThanhToan(id, ten, trangthai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
