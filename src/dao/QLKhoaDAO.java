/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lop.Khoa;

/**
 *
 * @author Administrator
 */
public class QLKhoaDAO extends DAO{
    public static boolean themKhoa(Khoa khoa)throws Exception{
        try {
            String sql = "INSERT INTO `tbl_khoa`(`makhoa`, `tenkhoa`) VALUES (?,?)";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1, khoa.getMaKhoa());
            ps.setString(2, khoa.getTenKhoa());
            int kq = ps.executeUpdate();
            return (kq==1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean xoaKhoa(String maKhoa)throws Exception{
        try {
            String sql = "DELETE FROM `tbl_khoa` WHERE `makhoa`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1, maKhoa);
            int kq = ps.executeUpdate();
            return (kq==1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean suaKhoa(Khoa khoa)throws Exception{
        try {
            String sql = "UPDATE `tbl_khoa` SET `tenkhoa`=? WHERE `makhoa`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(2, khoa.getMaKhoa());
            ps.setString(1, khoa.getTenKhoa());
            int kq = ps.executeUpdate();
            return (kq==1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Khoa layKhoaTheoTenKhoa(String tenKhoa){
        try {
            String sql = "SELECT * FROM `tbl_khoa` WHERE `tenkhoa`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1,tenKhoa);
            ResultSet kq = ps.executeQuery();
            while (kq.next()){
                Khoa khoa = new Khoa(kq.getString(1),kq.getString(2));
                return khoa;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }      
        return null;
    }
    public static ArrayList<Khoa> layTatCaKhoa()throws Exception{
        ArrayList<Khoa> dsKhoa = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_khoa`";
            PreparedStatement ps = createPreparedStatement(sql);
            ResultSet kq = ps.executeQuery();
            while(kq.next()){
                Khoa khoa = new Khoa(kq.getString(1),kq.getString(2));
                dsKhoa.add(khoa);
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
        return dsKhoa;
    }
    public static ArrayList<String> layTenTatCaKhoa()throws Exception{
        ArrayList<String> dsTenKhoa = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_khoa`";
            PreparedStatement ps = createPreparedStatement(sql);
            ResultSet kq = ps.executeQuery();
            while(kq.next()){
                dsTenKhoa.add(kq.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
        return dsTenKhoa;
    }
    
}
