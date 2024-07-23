/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.utils;

import com.shoes.model.NhanVien;

/**
 *
 * @author THEQUANG
 */
public class Auth {
    public static NhanVien user = null;
    public static void clear(){
        Auth.user = null;
    }
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static boolean isManager(){
        return Auth.isLogin() && user.getChucVu()==0 ? true:false ;
    }
    public static String isTen(){
        return user.getTenNhanVien();
    }
}
    
