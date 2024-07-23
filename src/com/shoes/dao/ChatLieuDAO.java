/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import com.shoes.model.ChatLieu;
import com.shoes.utils.JDBCHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntq04
 */
public class ChatLieuDAO extends SHOESDAO<ChatLieu, String> {

    @Override
    public boolean insert(ChatLieu cl) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into ChatLieu (idChatLieu,tenChatLieu,ngaytao,ngaysua,trangthai) values (?,?,getdate(),getdate(),?)");
            stm.setString(1, cl.getIDChatLieu());
            stm.setString(2, cl.getTenChatLieu());
            stm.setInt(3, cl.getTrangThai());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ChatLieu cl) {
        try {
            Connection conn = JDBCHelper.getConnection();
            PreparedStatement stm = conn.prepareStatement("update ChatLieu set tenChatLieu =?,ngaysua = getdate(), trangthai = ? where idChatLieu = ?");
            stm.setString(1, cl.getTenChatLieu());
            stm.setInt(2, cl.getTrangThai());
            stm.setString(3, cl.getIDChatLieu());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ChatLieu> getAll() {
        List<ChatLieu> list = new ArrayList<>();
        try {
            Connection conn = JDBCHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select idChatLieu,tenChatLieu,trangthai from ChatLieu");
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                int trangThai = rs.getInt(3);
                list.add(new ChatLieu(id, ten, trangThai));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
