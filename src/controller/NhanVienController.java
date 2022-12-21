/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.QLKhoaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lop.Khoa;
import lop.NhanVien;
import model.NhanVienModel;
import view.NhanVienView;

/**
 *
 * @author ADMIN
 */
public class NhanVienController {

    private NhanVienModel model;
    private NhanVienView view;

    public NhanVienController(NhanVienModel model, NhanVienView view) {
        this.model = model;
        this.view = view;
        this.view.setVisible(true);
        init();
    }

    private ActionListener moiActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                view.getBtn_sua().setEnabled(false);
                view.getBtn_xoa().setEnabled(false);
                view.getBtn_them().setEnabled(true);
                view.getTxt_manv().setEnabled(true);
                view.getTxt_chucdanh().setText("");
                view.getSpn_luong().setValue(0);
                view.getTxt_hoten().setText("");
                view.getTxt_manv().setText("");
            }
        };
    }

    private ActionListener themActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_manv().getText();
                String hoTen = view.getTxt_hoten().getText();
                int namSinh = Integer.parseInt(view.getCbo_namsinh().getSelectedItem().toString());
                boolean gioiTinh = true;
                if (view.getRdo_nam().isSelected()) {
                    gioiTinh = true;
                } else {
                    gioiTinh = false;
                }
                String chucDanh = view.getTxt_chucdanh().getText();
                int luong = (int) view.getSpn_luong().getValue();
                Khoa khoa = QLKhoaDAO.layKhoaTheoTenKhoa((String) view.getCbo_khoa().getSelectedItem());
                try {
                    if (ma.compareTo("") == 0 || hoTen.compareTo("") == 0 || chucDanh.compareTo("") == 0) {
                        JOptionPane.showMessageDialog(view, "Chưa nhập đủ dữ liệu");
                    } else {
                        boolean kq = model.them(new NhanVien(hoTen, namSinh, gioiTinh, ma, luong, chucDanh, khoa));
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Thêm thành công");
                            view.getTxt_manv().setText("");
                            view.getTxt_chucdanh().setText("");
                            view.getTxt_hoten().setText("");
                            view.getSpn_luong().setValue(0);
                            view.getBtn_them().setEnabled(false);
                        } else {
                            JOptionPane.showMessageDialog(view, "Thêm không thành công");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Lỗi: " + e);
                }
                showTable();
            }
        };
    }

    private ActionListener xoaActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_manv().getText();
                if (JOptionPane.showConfirmDialog(view, "Bạn có muốn xóa nhân viên " + ma + " không?") == JOptionPane.YES_OPTION) {
                    try {
                        boolean kq = model.xoa(ma);
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Xóa thành công");
                            view.getTxt_manv().setText("");
                            view.getTxt_chucdanh().setText("");
                            view.getTxt_hoten().setText("");
                            view.getSpn_luong().setValue(0);
                        } else {
                            JOptionPane.showMessageDialog(view, "Xóa không thành công");
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(view, "Lỗi: " + e);
                    }
                    showTable();
                }
            }
        };
    }

    private ActionListener suaActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_manv().getText();
                if (JOptionPane.showConfirmDialog(view, "Bạn có muốn sửa thông tin Nhân viên " + ma + " không?") == JOptionPane.YES_OPTION) {
                    try {
                        String hoTen = view.getTxt_hoten().getText();
                        int namSinh = Integer.parseInt(view.getCbo_namsinh().getSelectedItem().toString());
                        boolean gioiTinh = true;
                        if (view.getRdo_nam().isSelected()) {
                            gioiTinh = true;
                        } else {
                            gioiTinh = false;
                        }
                        String chucDanh = view.getTxt_chucdanh().getText();
                        int luong = Integer.parseInt(view.getSpn_luong().getValue().toString());
                        Khoa khoa = QLKhoaDAO.layKhoaTheoTenKhoa((String) view.getCbo_khoa().getSelectedItem());
                        boolean kq = model.sua(new NhanVien(hoTen, namSinh, gioiTinh, ma, luong, chucDanh, khoa));
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Sửa thành công");
                        } else {
                            JOptionPane.showMessageDialog(view, "Sửa không thành công");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(view, "Lỗi: " + e);
                    }

                }
                showTable();
            }

        };
    }

    private ActionListener thoatActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(view,
                        "Bạn có muốn thoát không?", "Thoát?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    view.dispose();
                }
            }
        };
    }

    private MouseAdapter tableClickActionListener() {
        return new MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = view.getTbl_nv().getSelectedRow();
                String ma = view.getTbl_nv().getValueAt(index, 0).toString();
                String ten = view.getTbl_nv().getValueAt(index, 1).toString();
                String gioiTinh = view.getTbl_nv().getValueAt(index, 3).toString();
                String namSinh = view.getTbl_nv().getValueAt(index, 2).toString();
                int luong = Integer.parseInt(view.getTbl_nv().getValueAt(index, 4).toString());
                String chucDanh = view.getTbl_nv().getValueAt(index, 5).toString();
                String khoa = view.getTbl_nv().getValueAt(index, 6).toString();
                view.getTxt_manv().setText(ma);
                view.getTxt_hoten().setText(ten);
                if (gioiTinh.compareTo("Nam") == 0) {
                    view.getRdo_nam().setSelected(true);
                    view.getRdo_nu().setSelected(false);

                } else {
                    view.getRdo_nu().setSelected(true);
                    view.getRdo_nam().setSelected(false);
                }
                view.getSpn_luong().setValue(luong);
                view.getTxt_chucdanh().setText(chucDanh);
                view.getCbo_khoa().setSelectedItem(khoa);
                view.getCbo_namsinh().setSelectedItem(namSinh);
                view.getBtn_them().setEnabled(false);
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
                view.getTxt_manv().setEnabled(false);
            }
        };
    }

    private void init() {
        showTable();
        view.getBtn_moi().addActionListener(moiActionListener());
        view.getBtn_them().addActionListener(themActionListener());
        view.getBtn_xoa().addActionListener(xoaActionListener());
        view.getBtn_sua().addActionListener(suaActionListener());
        view.getTbl_nv().addMouseListener(tableClickActionListener());
        view.getBtnThoat().addActionListener(thoatActionListener());
    }

    private void showTable() {

        DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_nv().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        tableModel.addColumn("Mã Nhân Viên");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Năm Sinh");
        tableModel.addColumn("Giới Tính");
        tableModel.addColumn("Lương");
        tableModel.addColumn("Chức Danh");
        tableModel.addColumn("Khoa");
        String gioiTinh = "";

        ArrayList<NhanVien> dsNV = model.layTatCaNhanVien();
        for (NhanVien nv : dsNV) {
            if (nv.isGioiTinh() == true) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            tableModel.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getNamSinh(), gioiTinh, nv.getLuong(), nv.getChucDanh(), nv.getKhoa().getTenKhoa()});
        }
        view.getTbl_nv().setModel(tableModel);
    }

}
