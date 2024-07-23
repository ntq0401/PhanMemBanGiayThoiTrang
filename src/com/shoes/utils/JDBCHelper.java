/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ntq04
 */
public class JDBCHelper {
    static String url = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=FASHION_SHOES;integratedSecurity=true;"
            + "encrypt=true;trustServerCertificate=true";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, "sa", "123");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}
    public static void main(String[] args) {
        getConnection();
    }
}
