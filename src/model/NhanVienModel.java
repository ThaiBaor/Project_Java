/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.QLNhanVienDAO;
import java.util.ArrayList;
import lop.NhanVien;

/**
 *
 * @author ADMIN
 */
public class NhanVienModel {
    public boolean them(NhanVien nv){
        try {
            return QLNhanVienDAO.themNhanVien(nv);
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean xoa(String maNV){
        try {
            return QLNhanVienDAO.xoaNhanVien(maNV);
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean sua(NhanVien nv){
        try {
            return QLNhanVienDAO.suaNhanVien(nv);
        } catch (Exception ex) {
            return false;
        }
    }
    public ArrayList<NhanVien> layTatCaNhanVien(){
        try {
            return QLNhanVienDAO.layTatCaNhanVien();
        } catch (Exception ex) {
            return null;
        }
    }
    
}
