/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lop.NhanVien;

/**
 *
 * @author ADMIN
 */
public class QLNhanVienDAO extends DAO{

    public static boolean themNhanVien(NhanVien nv) throws Exception {
        try {
            String sql = "INSERT INTO `tbl_nhanvien`(`hoten`, `namsinh`, `gioitinh`, `manv`, `luong`, `chucdanh`, `Khoa`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1, nv.getHoTen());
            ps.setInt(2, nv.getNamSinh());
            ps.setBoolean(3, nv.isGioiTinh());
            ps.setString(4, nv.getMaNV());
            ps.setInt(5, nv.getLuong());
            ps.setString(6, nv.getChucDanh());
            ps.setString(7, nv.getKhoa().getTenKhoa());
            int kq = ps.executeUpdate();
            return (kq == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaNhanVien(String maNV) throws Exception {
        try {
            String sql = "DELETE FROM `tbl_nhanvien` WHERE `manv`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1, maNV);
            int kq = ps.executeUpdate();
            return (kq == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean suaNhanVien(NhanVien nv) throws Exception {
        try {
            String sql = "UPDATE `tbl_nhanvien` SET `hoten`=?,`namsinh`=?,`gioitinh`=?,`luong`=?,`chucdanh`=?,`Khoa`=? WHERE `manv`=?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1, nv.getHoTen());
            ps.setInt(2, nv.getNamSinh());
            ps.setBoolean(3, nv.isGioiTinh());
            ps.setString(7, nv.getMaNV());
            ps.setInt(4, nv.getLuong());
            ps.setString(5, nv.getChucDanh());
            ps.setString(6, nv.getKhoa().getTenKhoa());
            int kq = ps.executeUpdate();
            return (kq == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<NhanVien> layTatCaNhanVien() throws Exception {
        ArrayList<NhanVien> dsNV = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_nhanvien`";
            PreparedStatement ps = createPreparedStatement(sql);
            ResultSet kq = ps.executeQuery();
            while (kq.next()) {
                NhanVien nv = new NhanVien(kq.getString(1), kq.getInt(2),kq.getBoolean(3),kq.getString(4),kq.getInt(5), kq.getString(6),QLKhoaDAO.layKhoaTheoTenKhoa(kq.getString(7)));
                dsNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNV;
    }
    public static NhanVien layNhanVienTheoMaNV(String maNV){
        try {
            String sql = "SELECT * FROM `tbl_nhanvien` WHERE `manv` = ?";
            PreparedStatement ps = createPreparedStatement(sql);
            ps.setString(1,maNV);
            ResultSet kq = ps.executeQuery();
            while (kq.next()){
                NhanVien nv = new NhanVien(kq.getString(1),kq.getInt(2),kq.getBoolean(3),kq.getString(4),kq.getInt(5),kq.getString(6), QLKhoaDAO.layKhoaTheoTenKhoa(kq.getString(7)));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }      
        return null;
    }
    public static ArrayList<String> layTenTatCaNhanVien()throws Exception{
        ArrayList<String> dsTenNV = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_nhanvien`";
            PreparedStatement ps = createPreparedStatement(sql);
            ResultSet kq = ps.executeQuery();
            while(kq.next()){
                dsTenNV.add(kq.getString(1)+"-"+kq.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
        return dsTenNV;
    }
}
