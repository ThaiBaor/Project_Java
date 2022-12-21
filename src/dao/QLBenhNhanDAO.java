/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import lop.BenhNhan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class QLBenhNhanDAO extends DAO{
    public  static boolean themBenhNhan(BenhNhan bn)throws Exception{
        try {
            String sql = "INSERT INTO `tbl_benhnhan`(`hoten`, `namsinh`, `gioitinh`, `mabn`, `bhyt`, `nhanvien`) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1,bn.getHoTen());
            ps.setInt(2,bn.getNamSinh());
            ps.setBoolean(3, bn.isGioiTinh());
            ps.setString(4,bn.getMaBN());
            ps.setString(5,bn.getbHYT());
            ps.setString(6,bn.getNhanVien().getMaNV());
            int kq = ps.executeUpdate();
            return (kq==1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public  static boolean xoaBenhNhan(String maBN)throws Exception{
        try {
            String sql = "DELETE FROM `tbl_benhnhan` WHERE `mabn`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1,maBN);
            int kq = ps.executeUpdate();
            return (kq==1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    public  static boolean suaBenhNhan(BenhNhan bn)throws Exception{
        try {
            String sql = "UPDATE `tbl_benhnhan` SET `hoten`=?, `namsinh`=?, `gioitinh`=?, `bhyt`=?, `nhanvien`=? WHERE `mabn`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1,bn.getHoTen());
            ps.setInt(2,bn.getNamSinh());
            ps.setBoolean(3, bn.isGioiTinh());
            ps.setString(4,bn.getbHYT());
            ps.setString(5,bn.getNhanVien().getMaNV());
            ps.setString(6,bn.getMaBN());
            int kq = ps.executeUpdate();
            return (kq==1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<BenhNhan> layTatCaBenhNhan()throws Exception{
        ArrayList<BenhNhan> dsBN = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_benhnhan`";
            PreparedStatement ps = createPreparedStatement(sql);
            ResultSet kq = ps.executeQuery();
            while(kq.next()){
                BenhNhan bn = new BenhNhan(kq.getString(1),kq.getInt(2),kq.getBoolean(3),kq.getString(4),kq.getString(5), QLNhanVienDAO.layNhanVienTheoMaNV(kq.getString(6)));
                dsBN.add(bn);
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
        return dsBN;
    }
    
    public static ArrayList<BenhNhan> timBenhNhanTheoTen(String ten)throws Exception{
        ArrayList<BenhNhan> dsBN = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_benhnhan` WHERE `hoten` LIKE ?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1, "%"+ten+"%");
            ResultSet kq = ps.executeQuery();
            while(kq.next()){
                BenhNhan bn = new BenhNhan(kq.getString(1),kq.getInt(2),kq.getBoolean(3),kq.getString(4),kq.getString(5), QLNhanVienDAO.layNhanVienTheoMaNV(kq.getString(6)));
                dsBN.add(bn);
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
        return dsBN;
    }
}
