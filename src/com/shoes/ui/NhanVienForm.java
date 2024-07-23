/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.shoes.ui;

import com.shoes.dao.NhanVienDAO;
import com.shoes.model.NhanVien;
import com.shoes.utils.ExportNV;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ntq04
 */
public class NhanVienForm extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    NhanVienDAO service = new NhanVienDAO();
    List<NhanVien> dsNV = new ArrayList<>();

    /**
     * Creates new form NhanVien
     */
    public NhanVienForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();
    }

    void loadData() {
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        dsNV = service.getAll();
        for (NhanVien nv : dsNV) {
            model.addRow(new Object[]{
                nv.getIDNhanVien(),
                nv.getTenNhanVien(),
                nv.getGioiTinh() == 0 ? "Nam" : "Nữ",
                nv.getEmail(),
                nv.getChucVu() == 0 ? "Quản lí" : "Nhân viên",
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getDienThoai(),
                nv.getTaiKhoan(),
                nv.getMatKhau(),
                nv.getTrangThai() == 0 ? "Đi làm" : "Nghỉ việc"
            });
        }
    }

    void loadDataGT(ArrayList<NhanVien> nv) {
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        dsNV = service.getAll();
        for (NhanVien a : nv) {
            model.addRow(new Object[]{
                a.getIDNhanVien(),
                a.getTenNhanVien(),
                a.getGioiTinh() == 0 ? "Nam" : "Nữ",
                a.getEmail(),
                a.getChucVu() == 0 ? "Quản lí" : "Nhân viên",
                a.getNgaySinh(),
                a.getDiaChi(),
                a.getDienThoai(),
                a.getTaiKhoan(),
                a.getMatKhau(),
                a.getTrangThai() == 0 ? "Đi làm" : "Nghỉ việc"

            });
        }
    }

    void loadDataTT(ArrayList<NhanVien> nv) {
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        dsNV = service.getAll();
        for (NhanVien a : nv) {
            model.addRow(new Object[]{
                a.getIDNhanVien(),
                a.getTenNhanVien(),
                a.getGioiTinh() == 0 ? "Nam" : "Nữ",
                a.getEmail(),
                a.getChucVu() == 0 ? "Quản lí" : "Nhân viên",
                a.getNgaySinh(),
                a.getDiaChi(),
                a.getDienThoai(),
                a.getTaiKhoan(),
                a.getMatKhau(),
                a.getTrangThai() == 0 ? "Đi làm" : "Nghỉ việc"

            });
        }
    }

    NhanVien getFormData() {
        String id = txtMa.getText();
        String ten = txtTen.getText();
        int gt;
        if (rdoNam.isSelected()) {
            gt = 0;
        } else {
            gt = 1;
        }
        String email = txtEmail.getText();
        int chucvu;
        if (rdoNV.isSelected()) {
            chucvu = 0;
        } else {
            chucvu = 1;
        }
        String ngaysinh = txtNgaySinh.getText();
        String diaChi = txtDiaChi.getText();
        String dienThoai = txtSDT.getText();
        String taiKhoan = txtTaiKhoan.getText();
        String matKhau = txtMatKhau.getText();
        int tt;
        if (rdoDiLam.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new NhanVien(id, ten, gt, email, chucvu, ngaysinh, diaChi, dienThoai, taiKhoan, matKhau, tt);
    }

    private void resetTable(ArrayList<NhanVien> searchResult) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        for (NhanVien nhanVien : searchResult) {
            model.addRow(new Object[]{
                nhanVien.getIDNhanVien(),
                nhanVien.getTenNhanVien(),
                nhanVien.getGioiTinh()== 0 ? "Nam":"Nữ",
                nhanVien.getEmail(),
                nhanVien.getChucVu() ==0 ? "Quản lí" : "Nhân viên",
                nhanVien.getNgaySinh(),
                nhanVien.getDiaChi(),
                nhanVien.getDienThoai(),
                nhanVien.getTaiKhoan(),
                nhanVien.getMatKhau(),
                nhanVien.getTrangThai()== 0 ? "Đi làm":"Nghỉ việc"
                    });
        }

    }

    void lamMoi() {
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();

    }

    boolean checkValidate() throws java.text.ParseException {
        List<NhanVien> lst = service.getAll();
        if (txtTen.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()
                || txtMatKhau.getText().trim().isEmpty() || txtMa.getText().trim().isEmpty()
                || txtTaiKhoan.getText().trim().isEmpty() || txtSDT.getText().trim().isEmpty() || txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap du thong tin!");
            return false;
        }
        String email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!txtEmail.getText().matches(email)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ email không hợp lệ!");
            return false;
        }
        String maString = txtMa.getText().trim();
        boolean ktm = false;
        for (NhanVien nhanVien : lst) {
            if (maString.equals(nhanVien.getIDNhanVien())) {
                ktm = true;
                break;
            }
        }
        if (ktm) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại trong danh sách!");
            return false;
        }
        String email1 = txtEmail.getText().trim();
        boolean kte = false;
        for (NhanVien nhanVien : lst) {
            if (email1.equals(nhanVien.getEmail())) {
                kte = true;
                break;
            }
        }

        if (kte) {
            JOptionPane.showMessageDialog(this, "Email nhân viên đã tồn tại!");
            return false;
        }
        String ngaySinh = txtNgaySinh.getText().trim();
        if (!ngaySinh.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải là kiểu dd/MM/yyyy !");
            return false;
        }

        String sdt = txtSDT.getText().trim();

        boolean dt = false;
        for (NhanVien nhanVien : lst) {
            if (sdt.equals(nhanVien.getDienThoai())) {
                dt = true;
                System.out.println(dt);
                break;
            }
        }
        if (dt) {
            JOptionPane.showMessageDialog(this, "SDT nhân viên đã tồn tại!");
            return false;
        }
        String phoneNumberInput = txtSDT.getText().trim();
        if (!phoneNumberInput.matches("\\d+") || phoneNumberInput.length() != 10) {
            JOptionPane.showMessageDialog(this, "SDT k hợp lệ!");
            return false;

        }

        boolean tk = false;
        for (NhanVien nv : lst) {
            if (txtTaiKhoan.getText().trim().equals(nv.getTaiKhoan())) {
                tk = true;
            }
        }
        if (tk) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
            return false;
        }

        return true;
    }

    boolean checkValidateUpdate() throws java.text.ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse("16/03/2024", formatter);
        LocalDate date2 = LocalDate.parse(txtNgaySinh.getText(), formatter);
        int comparisonResult = date1.compareTo(date2);
        List<NhanVien> lst = service.getAll();
        if (txtTen.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtMatKhau.getText().isEmpty() || txtMa.getText().isEmpty()
                || txtTaiKhoan.getText().isEmpty() || txtSDT.getText().isEmpty() || txtNgaySinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return false;
        }
        String email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!txtEmail.getText().matches(email)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ email không hợp lệ!");
            return false;
        }
       
        String ngaySinh = txtNgaySinh.getText();
        if (!ngaySinh.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải là kiểu dd/MM/yyyy !");
            return false;
        }
        if (comparisonResult < 0) {
            JOptionPane.showMessageDialog(this, "Bạn tới từ tương lai à ?");
            return false;
        }
        String sdt = txtSDT.getText();

        boolean dt = false;
        for (NhanVien nhanVien : lst) {
            if (sdt.equals(nhanVien.getDienThoai())) {
                dt = true;
                System.out.println(dt);
                break;
            }
        }
        if (dt) {
            JOptionPane.showMessageDialog(this, "SDT nhân viên đã tồn tại!");
            return false;
        }
        String phoneNumberInput = txtSDT.getText();
        if (!phoneNumberInput.matches("\\d+") || phoneNumberInput.length() != 10) {
            JOptionPane.showMessageDialog(this, "SDT k hợp lệ!");
            return false;

        }
        return true;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdoDiLam = new javax.swing.JRadioButton();
        txtDiaChi = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        rdoNu = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        rdoNghiViec = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        rdoNV = new javax.swing.JRadioButton();
        rdoQLy = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÍ NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÊM NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel4.setText("Email");

        jLabel7.setText("Địa chỉ");

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel9.setText("Tài khoản");

        jLabel10.setText("Mật khẩu");

        jLabel11.setText("Trạng thái");

        buttonGroup2.add(rdoDiLam);
        rdoDiLam.setText("Đi làm");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jButton3.setText("Xuất File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên nhân viên");

        jLabel3.setText("Giới tính");

        jLabel8.setText("Số điện thoại");

        jLabel5.setText("Chức vụ");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNghiViec);
        rdoNghiViec.setText("Nghỉ việc");

        jLabel6.setText("Ngày sinh");

        jLabel1.setText("Mã nhân viên");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoNV);
        rdoNV.setText("Quản lí");

        buttonGroup3.add(rdoQLy);
        rdoQLy.setText("Nhân viên");
        rdoQLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoQLyActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LỌC NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel14.setText("Giới tính");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Nam", "Nữ" }));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        jLabel15.setText("Trạng thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Đi làm", "Nghỉ Việc" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtMa))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMatKhau)
                                    .addComponent(txtTaiKhoan)
                                    .addComponent(txtSDT)
                                    .addComponent(txtDiaChi)
                                    .addComponent(txtNgaySinh)
                                    .addComponent(txtEmail)
                                    .addComponent(txtTen)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(rdoNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoNu))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(rdoDiLam)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoNghiViec))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(rdoNV)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoQLy)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoNV)
                    .addComponent(rdoQLy))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rdoDiLam)
                    .addComponent(rdoNghiViec))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Email", "Chức vụ", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Tài khoản", "Mật khẩu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(1).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(2).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(3).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(4).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(5).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(6).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(7).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(8).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(9).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(10).setResizable(false);
        }

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel12.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1211, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        lamMoi();


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        JFileChooser fileChooser = new JFileChooser("./file/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Export Excel");
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                int chk = JOptionPane.showConfirmDialog(this, "Xác nhận xuất file ?");
                if (chk == JOptionPane.YES_OPTION) {
                    ExportNV.writeExcel(service.getAll(), fileToSave.getAbsolutePath() + filter.getDescription());
                    JOptionPane.showMessageDialog(this, "Xuất File Excel thành công");

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Xuất File Excel thất bại");
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath() + filter.getDescription());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        NhanVien nv = getFormData();
        try {
            if (checkValidate()) {
                if (service.insert(nv)) {
                    loadData();
                    JOptionPane.showMessageDialog(this, "Thêm thành công !");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại !");
                }
            }

        } catch (java.text.ParseException ex) {
            Logger.getLogger(NhanVienForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            if (checkValidateUpdate()) {
                NhanVien nv = getFormData();
                if (service.update(nv)) {
                    loadData();
                    JOptionPane.showMessageDialog(this, "Sửa thành công !");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại !");
                }
            }

        } catch (java.text.ParseException ex) {
            Logger.getLogger(NhanVienForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate


    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String tk = txtTimKiem.getText().trim();
        String id = "";
        String name = "";
        String email = "";
        NhanVienDAO nvd = new NhanVienDAO();
        try {
            ArrayList<NhanVien> list = (ArrayList<NhanVien>) nvd.search(tk, tk, tk);

            if (!list.isEmpty()) {

                resetTable(list);

            } else {

            }
            System.out.println(list.isEmpty() ? "Danh sách rỗng" : "Danh sách không rỗng");
        } catch (SQLException ex) {

            Logger.getLogger(NhanVienForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
        List<NhanVien> lstall = service.getAll();
        List<NhanVien> lstnam = new ArrayList<>();
        List<NhanVien> lstnu = new ArrayList<>();
        String gioiTinh = (String) cboGioiTinh.getSelectedItem();
        for (NhanVien nhanVien : service.getAll()) {
            System.out.println("Tên: " + nhanVien.getTenNhanVien() + ", Giới tính: " + nhanVien.getGioiTinh());
            if (nhanVien.getGioiTinh() == 0) {
                lstnam.add(nhanVien);
                //              loadDataGT((ArrayList<NhanVien>) lstnam);
            } else if (nhanVien.getGioiTinh() == 1) {
                lstnu.add(nhanVien);
                //                loadDataGT((ArrayList<NhanVien>) lstnu);
            }

        }
        if ("All".equals(gioiTinh)) {
            loadDataGT((ArrayList<NhanVien>) lstall);
        } else if ("Nam".equals(gioiTinh)) {
            loadDataGT((ArrayList<NhanVien>) lstnam);
        } else if ("Nữ".equals(gioiTinh)) {
            loadDataGT((ArrayList<NhanVien>) lstnu);
        }

    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        List<NhanVien> lstall = service.getAll();
        List<NhanVien> lstdl = new ArrayList<>();
        List<NhanVien> lstnv = new ArrayList<>();
        String trangThai = (String) cboTrangThai.getSelectedItem();
        for (NhanVien nhanVien : service.getAll()) {
            System.out.println("Tên: " + nhanVien.getTenNhanVien() + ", TT: " + nhanVien.getTrangThai());
            if (nhanVien.getTrangThai() == 0) {
                lstdl.add(nhanVien);
                //              loadDataGT((ArrayList<NhanVien>) lstnam);
            } else if (nhanVien.getTrangThai() == 1) {
                lstnv.add(nhanVien);
                //                loadDataGT((ArrayList<NhanVien>) lstnu);
            }

        }
        if ("All".equals(trangThai)) {
            loadDataTT((ArrayList<NhanVien>) lstall);
        } else if ("Đi làm".equals(trangThai)) {
            loadDataGT((ArrayList<NhanVien>) lstdl);
        } else if ("Nghỉ Việc".equals(trangThai)) {
            loadDataTT((ArrayList<NhanVien>) lstnv);
        }

    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        if (row >= 0) {
            txtMa.setText(dsNV.get(row).getIDNhanVien());
            txtSDT.setText(dsNV.get(row).getDienThoai());
            txtEmail.setText(dsNV.get(row).getEmail());
            txtDiaChi.setText(dsNV.get(row).getDiaChi());
            txtTaiKhoan.setText(dsNV.get(row).getTaiKhoan());
            txtMatKhau.setText(dsNV.get(row).getMatKhau());
            txtNgaySinh.setText(dsNV.get(row).getNgaySinh());
            txtTen.setText(dsNV.get(row).getTenNhanVien());
            if (dsNV.get(row).getGioiTinh() == 0) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            if (dsNV.get(row).getTrangThai() == 0) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiViec.setSelected(true);
            }
            if (dsNV.get(row).getChucVu() == 0) {
                rdoNV.setSelected(true);
            } else {
                rdoQLy.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void rdoQLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoQLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoQLyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQLy;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
