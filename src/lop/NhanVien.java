/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lop;

/**
 *
 * @author ADMIN
 */
public class NhanVien extends Nguoi{
    private String maNV;
    private int luong;
    private String chucDanh;
    private Khoa khoa;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public NhanVien( String hoTen, int namSinh, boolean gioiTinh, String maNV, int luong, String chucDanh, Khoa khoa) {
        super(hoTen, namSinh, gioiTinh);
        this.maNV = maNV;
        this.luong = luong;
        this.chucDanh = chucDanh;
        this.khoa = khoa;
    }
}
