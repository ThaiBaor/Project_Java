/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lop;

/**
 *
 * @author ADMIN
 */
public class BenhNhan extends Nguoi{
    private String maBN;
    private String bHYT;
    private NhanVien nhanVien;

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getbHYT() {
        return bHYT;
    }

    public void setbHYT(String bHYT) {
        this.bHYT = bHYT;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public BenhNhan(String hoTen, int namSinh, boolean gioiTinh, String maBN, String bHYT, NhanVien nhanVien) {
        super(hoTen, namSinh, gioiTinh);
        this.maBN = maBN;
        this.bHYT = bHYT;
        this.nhanVien = nhanVien;
    }

    @Override
    public String toString() {
        return "BenhNhan{" + "maBN=" + maBN + ", bHYT=" + bHYT +  ", nhanVien=" + nhanVien + '}';
    }
    
    
}
