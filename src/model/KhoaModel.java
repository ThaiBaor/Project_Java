/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.QLKhoaDAO;
import java.util.ArrayList;
import lop.Khoa;

/**
 *
 * @author Administrator
 */
public class KhoaModel {
    public boolean them(Khoa khoa){
        try {
            return QLKhoaDAO.themKhoa(khoa);
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean xoa(String maKhoa){
        try {
            return QLKhoaDAO.xoaKhoa(maKhoa);
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean sua(Khoa khoa){
        try {
            return QLKhoaDAO.suaKhoa(khoa);
        } catch (Exception ex) {
            return false;
        }
    }
    public ArrayList<Khoa> layTatCaKhoa(){
        try {
            return QLKhoaDAO.layTatCaKhoa();
        } catch (Exception ex) {
            return null;
        }
    }
}
