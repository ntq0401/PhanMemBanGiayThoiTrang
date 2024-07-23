/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.shoes.ui;

import com.shoes.dao.KhachHangDAO;
import com.shoes.dao.showdataKhachHang;
import com.shoes.model.KhachHang;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ntq04
 */
public class KhachHangForm extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    KhachHangDAO service = new KhachHangDAO();
    List<KhachHang> dsKH = new ArrayList<>();
    showdataKhachHang showdatakhachHang = new showdataKhachHang();

    /**
     * Creates new form KhachHangForm
     */
    public boolean check() {
        dsKH= service.getAll();
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã");
            return false;
        }
        String maString = txtMa.getText().trim();
        boolean ktm = false;
        for (KhachHang kh : dsKH) {
            if (maString.equals(kh.getIDKhachHang())) {
                ktm = true;
                break;
            }
        }
        if (ktm) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại trong danh sách!");
            return false;
        }
        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
            return false;
        }
        int SDT;
        String sdt = txtSDT.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập sđt");
            return false;
        }
        String phoneNumberInput = txtSDT.getText().trim();
        if (!phoneNumberInput.matches("\\d+") || phoneNumberInput.length() != 10) {
            JOptionPane.showMessageDialog(this, "SDT k hợp lệ!");
            return false;

        }

        if (txtDC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
            return false;
        }
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mail");
            return false;
        }
        String email1 = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!txtEmail.getText().matches(email1)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ email không hợp lệ!");
            return false;
        }
        if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh");
            return false;
        }
         String ngaySinh = txtNgaySinh.getText().trim();
        if (!ngaySinh.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải là kiểu dd/MM/yyyy !");
            return false;
        }


        return true;
    }
    public boolean checkUpdate() {
        dsKH= service.getAll();
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã");
            return false;
        }
        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
            return false;
        }
        int SDT;
        String sdt = txtSDT.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập sđt");
            return false;
        }
        String phoneNumberInput = txtSDT.getText().trim();
        if (!phoneNumberInput.matches("\\d+") || phoneNumberInput.length() != 10) {
            JOptionPane.showMessageDialog(this, "SDT k hợp lệ!");
            return false;

        }

        if (txtDC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
            return false;
        }
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mail");
            return false;
        }
        String email1 = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!txtEmail.getText().matches(email1)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ email không hợp lệ!");
            return false;
        }
        if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh");
            return false;
        }
         String ngaySinh = txtNgaySinh.getText().trim();
        if (!ngaySinh.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải là kiểu dd/MM/yyyy !");
            return false;
        }


        return true;
    }
    public KhachHangForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();

    }

    void loadData() {
        model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        dsKH = service.getAll();
        for (KhachHang kh : dsKH) {
            model.addRow(new Object[]{
                kh.getIDKhachHang(),
                kh.getTenKhachHang(),
                kh.getSoDienThoai(),
                kh.getDiaChi(),
                kh.getEmail(),
                kh.getNgaySinh(),
                kh.getGioiTinh() == 0 ? "Nam" : "Nữ",
                kh.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    void loadDataTT(int tt) {
        model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        dsKH = service.getAll();
        for (KhachHang kh : dsKH) {
            if (kh.getTrangThai() == tt) {
                model.addRow(new Object[]{
                    kh.getIDKhachHang(),
                    kh.getTenKhachHang(),
                    kh.getSoDienThoai(),
                    kh.getDiaChi(),
                    kh.getEmail(),
                    kh.getNgaySinh(),
                    kh.getGioiTinh() == 0 ? "Nam" : "Nữ",
                    kh.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
                });
            } else {
                model.setRowCount(0);
                model.addRow(new Object[]{
                    kh.getIDKhachHang(),
                    kh.getTenKhachHang(),
                    kh.getSoDienThoai(),
                    kh.getDiaChi(),
                    kh.getEmail(),
                    kh.getNgaySinh(),
                    kh.getGioiTinh() == 0 ? "Nam" : "Nữ",
                    kh.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
                });
            }
        }
    }

    KhachHang getFormData() {
        String id = txtMa.getText();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String diaChi = txtDC.getText();
        String email = txtEmail.getText();
        String ngaySinh = txtNgaySinh.getText();
        int gt;
        if (rdoNam.isSelected()) {
            gt = 0;
        } else {
            gt = 1;
        }
        int tt;
        if (rdoDiLam.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new KhachHang(id, ten, sdt, diaChi, email, ngaySinh, gt, tt);
    }
//public void showdata(List<KhachHang>list){
//    for(KhachHang khachhang :list){
//        Object data[]={
//            khachhang.getIDKhachHang(),
//            khachhang.getTenKhachHang(),
//            khachhang.getSoDienThoai(),
//            khachhang.getDiaChi(),
//            khachhang.getEmail(),
//            khachhang.getNgaySinh(),
//            khachhang.getGioiTinh()== 0? "Nam": "Nữ",
//            khachhang.getTrangThai()==0? "Hoạt động" : "Không hoạt động"
//        };
//        model.addRow(data);
//    }
//}

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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rdoDiLam = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtIn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbxLocTrangThai = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÍ KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÊM KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã khách hàng");

        jLabel2.setText("Tên khách hàng");

        jLabel3.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel4.setText("Email");

        jLabel6.setText("Ngày sinh");

        jLabel11.setText("Trạng thái");

        buttonGroup2.add(rdoDiLam);
        rdoDiLam.setSelected(true);
        rdoDiLam.setText("Hoạt động");

        buttonGroup2.add(rdoNghiViec);
        rdoNghiViec.setText("Không hoạt động");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Số điện thoại");

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

        jButton3.setText("Làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtIn.setText("Xuất File");
        txtIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Lọc Trạng Thái");

        cbxLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Hoạt động", "Không hoạt động" }));
        cbxLocTrangThai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbxLocTrangThaiFocusLost(evt);
            }
        });
        cbxLocTrangThai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxLocTrangThaiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxLocTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, 0, 347, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(cbxLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("GIÀY THỜI TRANG");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoDiLam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoNghiViec)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIn)
                                .addContainerGap(18, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDC)
                                    .addComponent(txtMa)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11))
                                .addGap(46, 46, 46)
                                .addComponent(txtNgaySinh))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNam)
                        .addComponent(rdoNu)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rdoDiLam)
                    .addComponent(rdoNghiViec))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel10)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Email", "Ngày sinh", "Giới tính", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(5).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(6).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tìm Kiếm");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(791, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(382, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1180, 670));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (check()) {
            KhachHang kh = getFormData();
            if (service.insert(kh)) {
                loadData();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        KhachHang kh = getFormData();
        if (checkUpdate()) {
            if (service.update(kh)) {
            loadData();
            JOptionPane.showMessageDialog(this, "Sửa thành công !");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại !");
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int row = tblKhachHang.getSelectedRow();
        if (row >= 0) {
            txtMa.setText(dsKH.get(row).getIDKhachHang());
            txtTen.setText(dsKH.get(row).getTenKhachHang());
            txtSDT.setText(dsKH.get(row).getSoDienThoai());
            txtDC.setText(dsKH.get(row).getDiaChi());
            txtEmail.setText(dsKH.get(row).getEmail());
            txtNgaySinh.setText(dsKH.get(row).getNgaySinh());
            if (dsKH.get(row).getGioiTinh() == 0) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            if (dsKH.get(row).getTrangThai() == 0) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiViec.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtDC.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
        rdoNam.setSelected(true);
        rdoDiLam.setSelected(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String timkiem = txtTimKiem.getText().trim();
        String ma = "";
        String ten = "";
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        try {
            ArrayList<KhachHang> list = (ArrayList<KhachHang>) khachHangDAO.timkiem(timkiem, timkiem);
            if (!list.isEmpty()) {
                restTable(list);
            } else {
            }
            System.out.println(list.isEmpty() ? "danh sach" : "danh sach khong ro");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbxLocTrangThaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxLocTrangThaiMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxLocTrangThaiMouseClicked

    private void txtInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInActionPerformed
        // TODO add your handling code here:
// TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Khách Hàng");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên Khách Hàng");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số Điện Thoại");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Địa Chỉ ");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Email");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ngày Sinh");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Giới Tính");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Trạng Thái");

            ArrayList<KhachHang> list = (ArrayList<KhachHang>) showdatakhachHang.showdata();
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    KhachHang spbc = list.get(i);
                    row = sheet.createRow(4 + i);
                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(spbc.getIDKhachHang());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(spbc.getTenKhachHang());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(spbc.getSoDienThoai());

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(spbc.getDiaChi());

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(spbc.getEmail());

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(spbc.getNgaySinh());
                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(spbc.getGioiTinh());
                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(spbc.getTrangThai());
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn nơi lưu tệp Excel");

                fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try (FileOutputStream fos = new FileOutputStream(fileToSave.getAbsolutePath() + ".xlsx")) {
                        workbook.write(fos);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtInActionPerformed

    private void cbxLocTrangThaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxLocTrangThaiFocusLost
        // TODO add your handling code here:
        int tt = cbxLocTrangThai.getSelectedIndex();
        if (tt == 0) {
            tt = 2;
        }
        if (tt==1) {
            tt = 0;
        }
        if (tt==2)
            tt =2;
        loadDataTT(tt);
    }//GEN-LAST:event_cbxLocTrangThaiFocusLost
    public void restTable(List<KhachHang> listkh) {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (KhachHang kh : listkh) {
            model.addRow(new Object[]{
                kh.getIDKhachHang(),
                kh.getTenKhachHang(),
                kh.getSoDienThoai(),
                kh.getDiaChi(),
                kh.getEmail(),
                kh.getNgaySinh(),
                kh.getGioiTinh(),
                kh.getTrangThai()
            });
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxLocTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton txtIn;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
