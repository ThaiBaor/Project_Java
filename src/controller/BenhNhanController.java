/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.QLNhanVienDAO;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import lop.BenhNhan;
import lop.NhanVien;
import model.BenhNhanModel;
import model.NhanVienModel;
import view.BenhNhanView;

/**
 *
 * @author ADMIN
 */
public class BenhNhanController {

    private BenhNhanView view;
    private BenhNhanModel model;

    public BenhNhanController(BenhNhanView view, BenhNhanModel model) {
        this.view = view;
        this.model = model;
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
                view.getTxt_mabn().setEnabled(true);
                view.getTxt_ten().setText("");
                view.getTxt_mabn().setText("");
                view.getTxt_bhyt().setText("");
            }
        };
    }

    private ActionListener themActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_mabn().getText();
                String ten = view.getTxt_ten().getText();
                String bhyt = view.getTxt_bhyt().getText();
                int namSinh = Integer.parseInt(view.getCbo_namsinh().getSelectedItem().toString());
                boolean gioiTinh = true;
                if (view.getRdo_nam().isSelected()) {
                    gioiTinh = true;
                } else {
                    gioiTinh = false;
                }
                String s[] = view.getCbo_nv().getSelectedItem().toString().split("-");
                NhanVien nv = QLNhanVienDAO.layNhanVienTheoMaNV(s[1]);
                try {
                    if (ma.compareTo("") != 0 && ten.compareTo("") != 0 && bhyt.compareTo("") != 0) {
                        boolean kq = model.them(new BenhNhan(ten, namSinh, gioiTinh, ma, bhyt, nv));
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Thêm thành công");
                            view.getTxt_mabn().setText("");
                            view.getTxt_ten().setText("");
                            view.getTxt_bhyt().setText("");
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
                showTable(model.layTatCaBenhNhan());
            }
        };
    }

    private ActionListener xoaActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_mabn().getText();
                if (JOptionPane.showConfirmDialog(view, "Bạn có muốn xóa Bệnh nhân " + ma + " không?") == JOptionPane.YES_OPTION) {
                    try {
                        boolean kq = model.xoa(ma);
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Xóa thành công");
                            view.getTxt_mabn().setText("");
                            view.getTxt_ten().setText("");
                            view.getTxt_bhyt().setText("");
                        } else {
                            JOptionPane.showMessageDialog(view, "Xóa không thành công");
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(view, "Lỗi: " + e);
                    }
                    showTable(model.layTatCaBenhNhan());
                }
            }
        };
    }

    private ActionListener suaActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String ma = view.getTxt_mabn().getText();
                if (JOptionPane.showConfirmDialog(view, "Bạn có muốn sửa thông tin Bênh nhân " + ma + " không?") == JOptionPane.YES_OPTION) {
                    try {
                        String hoTen = view.getTxt_ten().getText();
                        int namSinh = Integer.parseInt(view.getCbo_namsinh().getSelectedItem().toString());
                        boolean gioiTinh = true;
                        if (view.getRdo_nam().isSelected()) {
                            gioiTinh = true;
                        } else {
                            gioiTinh = false;
                        }
                        String bhyt = view.getTxt_bhyt().getText();
                        String s[] = view.getCbo_nv().getSelectedItem().toString().split("-");
                        NhanVien nv = QLNhanVienDAO.layNhanVienTheoMaNV(s[1]);
                        boolean kq = model.sua(new BenhNhan(hoTen, namSinh, gioiTinh, ma, bhyt, nv));
                        if (kq == true) {
                            JOptionPane.showMessageDialog(view, "Sửa thành công");
                        } else {
                            JOptionPane.showMessageDialog(view, "Sửa không thành công");
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(view, "Lỗi: " + e);
                    }
                }
                showTable(model.layTatCaBenhNhan());
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
                int index = view.getTbl_bn().getSelectedRow();
                String ma = view.getTbl_bn().getValueAt(index, 0).toString();
                String ten = view.getTbl_bn().getValueAt(index, 1).toString();
                String gioiTinh = view.getTbl_bn().getValueAt(index, 3).toString();
                String namSinh = view.getTbl_bn().getValueAt(index, 2).toString();
                String bhyt = view.getTbl_bn().getValueAt(index, 4).toString();
                NhanVien nv = QLNhanVienDAO.layNhanVienTheoMaNV(view.getTbl_bn().getValueAt(index, 5).toString());
                String manv = nv.getHoTen()+"-"+nv.getMaNV();
                view.getTxt_mabn().setText(ma);
                view.getTxt_ten().setText(ten);
                if (gioiTinh.compareTo("Nam") == 0) {
                    view.getRdo_nam().setSelected(true);
                    view.getRdo_nu().setSelected(false);

                } else {
                    view.getRdo_nu().setSelected(true);
                    view.getRdo_nam().setSelected(false);
                }
                view.getTxt_bhyt().setText(bhyt);
                view.getCbo_nv().setSelectedItem(manv);
                view.getCbo_namsinh().setSelectedItem(namSinh);
                view.getBtn_them().setEnabled(false);
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
                view.getTxt_mabn().setEnabled(false);
            }
        };
    }

    private void init() {
        showTable(model.layTatCaBenhNhan());
        view.getBtn_moi().addActionListener(moiActionListener());
        view.getBtn_them().addActionListener(themActionListener());
        view.getBtn_xoa().addActionListener(xoaActionListener());
        view.getBtn_sua().addActionListener(suaActionListener());
        view.getTbl_bn().addMouseListener(tableClickActionListener());
        view.getBtn_thoat().addActionListener(thoatActionListener());
        view.getBtn_tim().addActionListener(timActionListener());
        view.getBtn_lammoi().addActionListener(lamMoiActionListener());
        view.getCbo_sapxep().addItemListener(sapXepListener());
    }

    private void showTable(ArrayList<BenhNhan> dsBN) {

        DefaultTableModel tableModel = (DefaultTableModel) view.getTbl_bn().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        tableModel.addColumn("Mã Bệnh nhân");
        tableModel.addColumn("Tên Bệnh nhân");
        tableModel.addColumn("Năm sinh");
        tableModel.addColumn("Giới tính");
        tableModel.addColumn("Mã BHYT");
        tableModel.addColumn("Nhân viên phụ trách");
        String gioiTinh = "";

        for (BenhNhan bn : dsBN) {
            if (bn.isGioiTinh() == true) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            tableModel.addRow(new Object[]{bn.getMaBN(), bn.getHoTen(), bn.getNamSinh(), gioiTinh, bn.getbHYT(), bn.getNhanVien().getMaNV()});
        }
        view.getTbl_bn().setModel(tableModel);
    }

    public ActionListener timActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTxt_tim().getText().compareTo("") == 0) {
                    showTable(model.layTatCaBenhNhan());
                } else {
                    showTable(model.timBenhNhanTheoTen(view.getTxt_tim().getText()));
                }
            }
        };
    }

    public ActionListener lamMoiActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxt_tim().setText("");
                showTable(model.layTatCaBenhNhan());
            }
        };
    }

    public ItemListener sapXepListener() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = view.getCbo_sapxep().getSelectedIndex();
                if (index == 1) {
                    showTable(model.sapXepBenhNhanTheoMa());
                } else {
                    showTable(model.sapXepBenhNhanTheoTenRoiTheoTuoi());
                }
            }
        };
    }

}
