/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lop.Khoa;
import model.KhoaModel;
import view.KhoaView;

/**
 *
 * @author Administrator
 */
public class KhoaController {

    private KhoaModel model;
    private KhoaView view;

    public KhoaController(KhoaModel model, KhoaView view) {
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
                view.getTxt_makhoa().setEnabled(true);
                view.getTxt_makhoa().setText("");
                view.getTxt_tenkhoa().setText("");
            }
        };
    }

    private ActionListener themActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_makhoa().getText();
                String ten = view.getTxt_tenkhoa().getText();
                try {
                    if (ma.compareTo("") != 0 && ten.compareTo("") != 0) {
                        boolean kq = model.them(new Khoa(ma, ten));
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Thêm thành công");
                            view.getTxt_makhoa().setText("");
                            view.getTxt_tenkhoa().setText("");
                            view.getBtn_them().setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(view, "Thêm không thành công");
                        }

                    } else {
                        JOptionPane.showMessageDialog(view, "Chưa nhập đủ dữ liệu");
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
                String ma = view.getTxt_makhoa().getText();
                if (JOptionPane.showConfirmDialog(view, "Bạn có muốn xóa " + ma + " không?") == JOptionPane.YES_OPTION) {
                    try {
                        boolean kq = model.xoa(ma);
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Xóa thành công");
                            view.getTxt_makhoa().setText("");
                            view.getTxt_tenkhoa().setText("");
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
                String ma = view.getTxt_makhoa().getText();
                if (JOptionPane.showConfirmDialog(view, "Bạn có muốn sửa thông tin khoa " + ma + " không?") == JOptionPane.YES_OPTION) {
                    try {
                        String ten = view.getTxt_tenkhoa().getText();
                        boolean kq = model.sua(new Khoa(ma, ten));
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
                int index = view.getTbl_khoa().getSelectedRow();
                String ma = (String) view.getTbl_khoa().getValueAt(index, 0);
                String ten = (String) view.getTbl_khoa().getValueAt(index, 1);
                view.getTxt_makhoa().setText(ma);
                view.getTxt_tenkhoa().setText(ten);
                view.getBtn_them().setEnabled(false);
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
            }
        };
    }

    private void firstLoad() {
        view.getBtn_them().setEnabled(false);
        view.getTxt_makhoa().setEnabled(false);

    }

    private void init() {
        firstLoad();
        showTable();
        view.getBtn_moi().addActionListener(moiActionListener());
        view.getBtn_them().addActionListener(themActionListener());
        view.getBtn_xoa().addActionListener(xoaActionListener());
        view.getBtn_sua().addActionListener(suaActionListener());
        view.getTbl_khoa().addMouseListener(tableClickActionListener());
        view.getBtn_thoat().addActionListener(thoatActionListener());
    }

    private void showTable() {

        DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_khoa().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        tableModel.addColumn("Mã Khoa");
        tableModel.addColumn("Tên Khoa");

        ArrayList<Khoa> dsKhoa = model.layTatCaKhoa();
        for (Khoa khoa : dsKhoa) {
            tableModel.addRow(new Object[]{khoa.getMaKhoa(), khoa.getTenKhoa()});
        }
        view.getTbl_khoa().setModel(tableModel);
    }

}
