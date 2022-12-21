/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.QLBenhNhanDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import lop.BenhNhan;

/**
 *
 * @author ADMIN
 */
public class BenhNhanModel {

    public boolean them(BenhNhan bn) {
        try {
            return QLBenhNhanDAO.themBenhNhan(bn);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean xoa(String maBN) {
        try {
            return QLBenhNhanDAO.xoaBenhNhan(maBN);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean sua(BenhNhan bn) {
        try {
            return QLBenhNhanDAO.suaBenhNhan(bn);
        } catch (Exception ex) {
            return false;
        }
    }

    public ArrayList<BenhNhan> timBenhNhanTheoTen(String ten) {
        try {
            return QLBenhNhanDAO.timBenhNhanTheoTen(ten);
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<BenhNhan> layTatCaBenhNhan() {
        try {
            return QLBenhNhanDAO.layTatCaBenhNhan();
        } catch (Exception ex) {
            return null;
        }
    }

    

    public ArrayList<BenhNhan> sapXepBenhNhanTheoMa() {
        try {
            ArrayList<BenhNhan> dsBN = (ArrayList<BenhNhan>) QLBenhNhanDAO.layTatCaBenhNhan().clone();
            Collections.sort(dsBN, new Comparator<BenhNhan>() {
                @Override
                public int compare(BenhNhan bn1, BenhNhan bn2) {
                    return bn1.getMaBN().compareTo(bn2.getMaBN());
                }
            });
            return dsBN;
        } catch (Exception ex) {
            return null;
        }
    }

    public ArrayList<BenhNhan> sapXepBenhNhanTheoTenRoiTheoTuoi() {
        try {
            ArrayList<BenhNhan> dsBN = (ArrayList<BenhNhan>) QLBenhNhanDAO.layTatCaBenhNhan().clone();
            Collections.sort(dsBN, new Comparator<BenhNhan>() {
                @Override
                public int compare(BenhNhan bn1, BenhNhan bn2) {
                    if (bn1.getMaBN().compareTo(bn2.getMaBN()) == 0) {
                        return bn2.getNamSinh() - bn1.hashCode();
                    } else {
                        return bn1.getMaBN().compareTo(bn2.getMaBN());
                    }
                }
            });
            return dsBN;
        } catch (Exception ex) {
            return null;
        }
    }
    public static void main(String[] args) {
        for(int i=1960;i<2023;i++){
            System.out.println(i);
        }
    }

}
