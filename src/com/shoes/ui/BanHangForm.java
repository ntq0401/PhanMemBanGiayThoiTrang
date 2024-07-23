package com.shoes.ui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.shoes.dao.HoaDonChiTietDAO;
import com.shoes.dao.HoaDonDAO;
import com.shoes.dao.KhachHangDAO;
import com.shoes.dao.VoucherDAO;
import com.shoes.dao.PhuongThucDAO;
import com.shoes.dao.SanPhamChiTietDAO;
import com.shoes.model.ChiTietHoaDon;
import com.shoes.model.HoaDon;
import com.shoes.model.KhachHang;
import com.shoes.model.Voucher;
import com.shoes.model.PhuongThucThanhToan;
import com.shoes.model.SanPhamChiTiet;
import com.shoes.utils.JDBCHelper;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.shoes.utils.Auth;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ntq04
 */
public class BanHangForm extends javax.swing.JInternalFrame implements Runnable, ThreadFactory {

    NumberFormat formatter = new DecimalFormat("#,###.###");
    NumberFormat formatter1 = new DecimalFormat("#,###.###");
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    DefaultTableModel model;
    DefaultTableModel model1;
    KhachHangDAO serviceKH = new KhachHangDAO();
    List<KhachHang> dsKH = new ArrayList<>();
    HoaDonDAO serviceHD = new HoaDonDAO();
    List<HoaDon> dsHD = new ArrayList<>();
    SanPhamChiTietDAO serviceSPCT = new SanPhamChiTietDAO();
    List<SanPhamChiTiet> dsSPCT = new ArrayList<>();
    HoaDonChiTietDAO serviceCT = new HoaDonChiTietDAO();
    List<ChiTietHoaDon> dsCT = new ArrayList<>();
    PhuongThucDAO servicePT = new PhuongThucDAO();
    List<PhuongThucThanhToan> dsPT = new ArrayList<>();
    VoucherDAO serviceVC = new VoucherDAO();
    List<Voucher> dsVC = new ArrayList<>();

    /**
     * Creates new form BanHangForm
     */
    public BanHangForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadTenNV();
        initComboBoxKH();
        initComboBoxPT();
        initComboBoxKM();
        loadDataHD();
        loadDataSPCT();
        initWebcam();

    }

    public void initComboBoxKH() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        dsKH = serviceKH.getAll();
        for (KhachHang kh : dsKH) {
            if (kh.getTrangThai() == 0) {
                cbo.addElement(kh.getTenKhachHang() + "-" + kh.getSoDienThoai());
            }
        }
        cboTenKH.setModel(cbo);
    }

    public void initComboBoxPT() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        dsPT = servicePT.getAll();
        for (PhuongThucThanhToan pt : dsPT) {
            if (pt.getTrangThai() == 0) {
                cbo.addElement(pt.getTenPhuongThuc());
            }
        }
        cboPhuongThuc.setModel(cbo);
    }

    public void initComboBoxKM() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        dsVC = serviceVC.getAll();
        for (Voucher km : dsVC) {
            if (km.getTrangThai() == 0) {
                cbo.addElement(km.getMaVoucher());
            }
        }
        cboKM.setModel(cbo);
    }

    void loadTenNV() {
        lblNhanVien.setText(Auth.isTen());
    }

    void loadDataHD() {
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        dsHD = serviceHD.getSelect(0);
        for (HoaDon hd : dsHD) {
            if (hd.getTrangThai() == 0) {
                model.addRow(new Object[]{
                    hd.getIDHoaDon(),
                    hd.getKhachHang(),
                    hd.getNhanVien(),
                    hd.getNgayTao(),
                    hd.getKhuyenmai(),
                    formatter.format(hd.getTongTien()),
                    hd.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã thanh toán",});

            }
        }
    }

    void loadHDtheoCBO(int tt) {
        dsHD = serviceHD.getSelect(tt);
        for (HoaDon hd : dsHD) {
            model.addRow(new Object[]{
                hd.getIDHoaDon(),
                hd.getKhachHang(),
                hd.getNhanVien(),
                hd.getNgayTao(),
                hd.getKhuyenmai(),
                hd.getTongTien(),
                hd.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã thanh toán",});
        }
    }

    HoaDon getFormDataHD() {
        String tenKH = (String) cboTenKH.getSelectedItem();
        String[] tenreal = tenKH.split("-");
        String tenNV = Auth.isTen();
        String tenPT = (String) cboPhuongThuc.getSelectedItem();
        String tenKM = (String) cboKM.getSelectedItem();
        int tt = 0;
        return new HoaDon(0, tenNV, tenreal[0], tenPT, tenKM, 0, null, tt);
    }

    void loadDataSPCT() {
        model = (DefaultTableModel) tblChiTietSP.getModel();
        model.setRowCount(0);
        dsSPCT = serviceSPCT.getAll();
        for (SanPhamChiTiet ct : dsSPCT) {
            if (ct.getTrangThai() == 0) {
                model.addRow(new Object[]{
                    ct.getIDChiTietSanPham(),
                    ct.getTensp(),
                    ct.getThuonghieu(),
                    ct.getNhacungcap(),
                    ct.getMauSac(),
                    ct.getChatLieu(),
                    ct.getKieuDang(),
                    ct.getPhongcach(),
                    ct.getKichco(),
                    ct.getMoTa(),
                    ct.getGiaBan(),
                    ct.getSoLuong()
                });
            }
        }
    }

    void loadDataHDCT(int id) {
        model = (DefaultTableModel) tblCTHD.getModel();
        model.setRowCount(0);
        dsCT = serviceCT.getSelect(id);
        for (ChiTietHoaDon ct : dsCT) {
            model.addRow(new Object[]{
                dsCT.indexOf(ct) + 1,
                ct.getIDCTsanPham(),
                ct.getTenSanPham(),
                +ct.getSoLuong(),
                formatter.format(ct.getDonGia()),
                formatter.format(ct.getDonGia() * ct.getSoLuong())
            });
        }
    }

    public boolean validateThemNhanh() {
        if (txtTenKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên khách hàng !");
            return false;
        }
        if (txtSDTKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu số điện thoại");
            return false;
        } else if (!txtSDTKH.getText().trim().matches("\\d+") || txtSDTKH.getText().trim().length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ !");
            return false;
        }
        for (KhachHang khachHang : dsKH) {
            if (khachHang.getSoDienThoai().equalsIgnoreCase(txtSDTKH.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại trong hệ thống !");
                return false;
            }
        }
        return true;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.close();
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        jPanel9.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 190));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                Logger.getLogger(BanHangForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (result != null) {
                int rowHD = tblHoaDonCho.getSelectedRow();
                if (rowHD >= 0) {
                    String trangThai = (String) tblHoaDonCho.getValueAt(rowHD, 6);
                    if (trangThai.equalsIgnoreCase("Chưa thanh toán") && !trangThai.isEmpty()) {
                        int rowSP;
                        for (int i = 0; i < dsSPCT.size(); i++) {
                            if (dsSPCT.get(i).getIDChiTietSanPham().equalsIgnoreCase(result.toString())) {
                                rowSP = i;
                            } else {
                                rowSP = -1;
                            }
                            if (rowSP >= 0) {
                                int slsp = (int) tblChiTietSP.getValueAt(rowSP, 11);
                                if (rowHD >= 0) {
                                    if (rowSP >= 0) {
                                        String sl = JOptionPane.showInputDialog("Chọn số lượng sản phẩm bạn muốn mua :");
                                        if (sl.trim() != null && sl.matches("^[1-9]\\d*$")) {
                                            int SL = Integer.parseInt(sl);
                                            if (SL <= slsp) {
                                                String idCTSP = dsSPCT.get(rowSP).getIDChiTietSanPham();
                                                int idHD = dsHD.get(rowHD).getIDHoaDon();
                                                try {
                                                    Connection conn = JDBCHelper.getConnection();
                                                    PreparedStatement stm = conn.prepareStatement("EXEC BanHang ?,?,?");
                                                    stm.setInt(1, idHD);
                                                    stm.setString(2, idCTSP);
                                                    stm.setInt(3, SL);
                                                    stm.executeUpdate();
                                                    stm.close();
                                                    loadDataHDCT(idHD);
                                                    loadDataSPCT();
                                                    lblTongTien.setText(dsHD.get(rowHD).getTongTien() + "");
                                                    lblTienPhaiTra.setText(dsHD.get(rowHD).getTongTien() + "");
                                                    try {
                                                        Connection conn1 = JDBCHelper.getConnection();
                                                        PreparedStatement stm1 = conn1.prepareStatement("EXEC TongTien ?,?");
                                                        stm1.setInt(1, idHD);
                                                        stm1.setString(2, dsHD.get(rowHD).getKhuyenmai());
                                                        stm1.executeUpdate();
                                                        stm1.close();
                                                        dsHD = serviceHD.getSelect(0);
                                                        tblHoaDonCho.setValueAt(dsHD.get(rowHD).getTongTien(), rowHD, 5);
                                                        lblTongTien.setText(dsHD.get(rowHD).getTongTien() + "");
                                                        lblTienPhaiTra.setText(dsHD.get(rowHD).getTongTien() + "");
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            } else {
                                                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ !");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không hợp lệ");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Chỉ được thêm sản phẩm vào hoá đơn chưa thanh toán");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn cần nhập sản phẩm !");
                }
            }
        } while (true);

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboTenKH = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboKM = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTienTra = new javax.swing.JLabel();
        txtTienDua = new javax.swing.JTextField();
        lblTienPhaiTra = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cboPhuongThuc = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JTextField();
        btnThemNhanh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();

        setBorder(null);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeiconified(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Khách hàng");

        cboTenKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKHActionPerformed(evt);
            }
        });

        jButton6.setText("TẠO HĐ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setText("Discount");

        cboKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Tên nhân viên");

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 0, 0));
        lblNhanVien.setText("Nhân viên");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboKM, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 250, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Tổng tiền");

        lblTongTien.setText("VND");

        jLabel10.setText("Tiền khách phải trả");

        jLabel11.setText("Tiền khách đưa");

        jLabel12.setText("Tiền thừa trả khách");

        lblTienTra.setText("VND");

        txtTienDua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienDuaFocusLost(evt);
            }
        });
        txtTienDua.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTienDuaPropertyChange(evt);
            }
        });
        txtTienDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienDuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienDuaKeyTyped(evt);
            }
        });

        lblTienPhaiTra.setText("VND");

        jButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("THANH TOÁN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setText("Phương thức");

        cboPhuongThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setText("Nhập tiền");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("HUỶ HOÁ ĐƠN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("IN HOÁ ĐƠN");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(25, 25, 25)
                                            .addComponent(lblTongTien))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblTienTra))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblTienPhaiTra))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(31, 31, 31)
                                            .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton4)))
                                    .addComponent(jLabel11))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTienPhaiTra))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTienTra))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 250, 370));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setText("Tên khách hàng");

        jLabel7.setText("Số điện thoại");

        btnThemNhanh.setText("Thêm nhanh");
        btnThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKH))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemNhanh)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 120));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 270, 680));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QR CODE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 190));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 300, 220));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HĐ", "KHÁCH HÀNG", "NHÂN VIÊN", "NGÀY TẠO", "KHUYẾN MÃI", "TỔNG TIỀN", "TRẠNG THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.getTableHeader().setReorderingAllowed(false);
        tblHoaDonCho.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseDragged(evt);
            }
        });
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseEntered(evt);
            }
        });
        jScrollPane8.setViewportView(tblHoaDonCho);
        if (tblHoaDonCho.getColumnModel().getColumnCount() > 0) {
            tblHoaDonCho.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(4).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(5).setResizable(false);
            tblHoaDonCho.getColumnModel().getColumn(6).setResizable(false);
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán", "Đã huỷ" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("TRẠNG THÁI HOÁ ĐƠN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 220));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ SP", "TÊN SP", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTHD.getTableHeader().setReorderingAllowed(false);
        tblCTHD.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblCTHDMouseDragged(evt);
            }
        });
        jScrollPane9.setViewportView(tblCTHD);
        if (tblCTHD.getColumnModel().getColumnCount() > 0) {
            tblCTHD.getColumnModel().getColumn(0).setResizable(false);
            tblCTHD.getColumnModel().getColumn(1).setResizable(false);
            tblCTHD.getColumnModel().getColumn(2).setResizable(false);
            tblCTHD.getColumnModel().getColumn(3).setResizable(false);
            tblCTHD.getColumnModel().getColumn(4).setResizable(false);
            tblCTHD.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton5.setText("XOÁ SP");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 920, 190));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Brands", "Nguồn", "Màu sắc", "Chất liệu", "Kiểu dáng", "Phong cách", "Kích cỡ", "Mô tả", "Giá bán", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSP.getTableHeader().setReorderingAllowed(false);
        tblChiTietSP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseDragged(evt);
            }
        });
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblChiTietSP);
        if (tblChiTietSP.getColumnModel().getColumnCount() > 0) {
            tblChiTietSP.getColumnModel().getColumn(0).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(1).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(2).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(3).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(4).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(5).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(6).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(7).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(8).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(9).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(10).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(11).setResizable(false);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 920, 270));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (cboTenKH.getSelectedItem() == null || cboTenKH.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Thiếu khách hàng !");
        } else if (cboKM.getSelectedItem() == null || cboKM.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Thiếu voucher !");
        } else if (lblNhanVien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu nhân viên !");
        } else {
            jComboBox1.setSelectedIndex(0);
            HoaDon hd = getFormDataHD();
            if (serviceHD.insert(hd)) {
                loadDataHD();
                JOptionPane.showMessageDialog(this, "OK");
                int row = tblHoaDonCho.getRowCount();
                tblHoaDonCho.getSelectionModel().setSelectionInterval(row - 1, row - 1);
                tblHoaDonCho.scrollRectToVisible(new Rectangle(tblHoaDonCho.getCellRect(row - 1, 0, true)));
            } else {
                JOptionPane.showMessageDialog(this, "Không thành công !");
            }
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void cboTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTenKHActionPerformed

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        // TODO add your handling code here
        int rowHD = tblHoaDonCho.getSelectedRow();
        if (rowHD >= 0) {
            String trangThai = (String) tblHoaDonCho.getValueAt(rowHD, 6);
            if (trangThai.equalsIgnoreCase("Chưa thanh toán") && !trangThai.isEmpty()) {
                int rowSP = tblChiTietSP.getSelectedRow();
                int slsp = (int) tblChiTietSP.getValueAt(rowSP, 11);
                if (rowHD >= 0) {
                    if (rowSP >= 0) {
                        String sl = JOptionPane.showInputDialog("Chọn số lượng sản phẩm bạn muốn mua :");
                        if (sl != null) {
                            if (sl.matches("^[1-9]\\d*$")) {
                                int SL = Integer.parseInt(sl.trim());
                                if (SL <= slsp) {
                                    String idCTSP = (String) tblChiTietSP.getValueAt(rowSP, 0);
                                    int idHD = dsHD.get(rowHD).getIDHoaDon();
                                    try {
                                        Connection conn = JDBCHelper.getConnection();
                                        PreparedStatement stm = conn.prepareStatement("EXEC BanHang ?,?,?");
                                        stm.setInt(1, idHD);
                                        stm.setString(2, idCTSP);
                                        stm.setInt(3, SL);
                                        stm.executeUpdate();
                                        stm.close();
                                        loadDataHDCT(idHD);
                                        loadDataSPCT();

                                        lblTongTien.setText(dsHD.get(rowHD).getTongTien() + "");
                                        lblTienPhaiTra.setText(dsHD.get(rowHD).getTongTien() + "");
                                        try {
                                            Connection conn1 = JDBCHelper.getConnection();
                                            PreparedStatement stm1 = conn1.prepareStatement("EXEC TongTien ?,?");
                                            stm1.setInt(1, idHD);
                                            stm1.setString(2, dsHD.get(rowHD).getKhuyenmai());
                                            stm1.executeUpdate();
                                            stm1.close();
                                            dsHD = serviceHD.getSelect(0);
                                            tblHoaDonCho.setValueAt(dsHD.get(rowHD).getTongTien(), rowHD, 5);
                                            lblTongTien.setText(dsHD.get(rowHD).getTongTien() + "");
                                            lblTienPhaiTra.setText(dsHD.get(rowHD).getTongTien() + "");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ !");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không hợp lệ !");
                            }

                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chỉ được thêm sản phẩm vào hoá đơn chưa thanh toán ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn cần thêm sản phẩm");
        }
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDonCho.getSelectedRow();
        int rowCount = tblHoaDonCho.getSelectedRowCount();
        if (rowCount > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
        } else {
            if (row >= 0) {
                int id = (int) tblHoaDonCho.getValueAt(row, 0);
                System.out.println(id);
                loadDataHDCT(id);
                cboTenKH.setSelectedItem(tblHoaDonCho.getValueAt(row, 1));
                cboKM.setSelectedItem(tblHoaDonCho.getValueAt(row, 4));
                lblTongTien.setText(tblHoaDonCho.getValueAt(row, 5) + "");
                lblTienPhaiTra.setText(tblHoaDonCho.getValueAt(row, 5) + "");
            }
        }
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void txtTienDuaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTienDuaPropertyChange
        // TODO add your handling code here:


    }//GEN-LAST:event_txtTienDuaPropertyChange

    private void txtTienDuaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienDuaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDuaFocusLost

    private void txtTienDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienDuaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienDuaKeyPressed

    private void txtTienDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienDuaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienDuaKeyReleased

    private void txtTienDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienDuaKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienDuaKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Comming soon !");
//        if (!txtTienDua.getText().isEmpty()) {
//            float tienTra = Float.valueOf(formatter1.format(lblTienPhaiTra.getText()));
//            float tienNhap = Float.valueOf(formatter1.format(lblTienTra.getText()));
//            if (tienNhap >= tienTra) {
//                lblTienTra.setText(tienNhap - tienTra + "");
//            } else {
//                JOptionPane.showMessageDialog(this, "Tiền nhập vào phải lớn hơn tổng tiền !");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa");
//        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = tblHoaDonCho.getSelectedRow();
        if (row >= 0) {
            if (tblHoaDonCho.getValueAt(row, 6) == "Đã thanh toán" || tblHoaDonCho.getValueAt(row, 6) == "Đã huỷ") {
                JOptionPane.showMessageDialog(this, "Không thể thanh toán hoá đơn này !");
            } else {

                int id = (int) tblHoaDonCho.getValueAt(row, 0);
                int rowHDCT = tblCTHD.getRowCount();
                if (rowHDCT >= 1) {
                    if (serviceHD.thanhToan(id)) {
                        dsHD.remove(tblHoaDonCho.getValueAt(row, 0));
                        loadDataHD();
                        model = (DefaultTableModel) tblCTHD.getModel();
                        model.setRowCount(0);
                        lblTongTien.setText("0");
                        lblTienPhaiTra.setText("0");
                        txtTienDua.setText("");
                        lblTienTra.setText("");
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công !");
                        JOptionPane.showConfirmDialog(this, "Bạn có muốn in hoá đơn ?");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể thanh toán hoá đơn không có sản phẩm !");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn cần thanh toán !");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:   
        int rowHD = tblHoaDonCho.getSelectedRow();
        if (rowHD >= 0) {
            if (tblHoaDonCho.getValueAt(rowHD, 6) == "Đã thanh toán" || tblHoaDonCho.getValueAt(rowHD, 6) == "Đã huỷ") {
                JOptionPane.showMessageDialog(this, "Không thể xoá sản phẩm trong hoá đơn này !");
            } else {
                int rowCT = tblCTHD.getSelectedRow();
                if (rowCT >= 0) {
                    int idCT = dsCT.get(rowCT).getIDChiTietHoaDon();
                    int idHD = dsCT.get(rowCT).getIDhoaDon();
                    String idSP = dsCT.get(rowCT).getIDCTsanPham();
                    if (serviceCT.delete1SP(idCT, idSP)) {
                        loadDataHDCT(idHD);
                        loadDataSPCT();
                        try {
                            Connection conn1 = JDBCHelper.getConnection();
                            PreparedStatement stm1 = conn1.prepareStatement("EXEC TongTien ?,?");
                            stm1.setInt(1, idHD);
                            stm1.setString(2, dsHD.get(rowHD).getKhuyenmai());
                            stm1.executeUpdate();
                            stm1.close();
                            dsHD = serviceHD.getSelect(0);
                            tblHoaDonCho.setValueAt(dsHD.get(rowHD).getTongTien(), rowHD, 5);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        lblTongTien.setText(dsHD.get(rowHD).getTongTien() + "");
                        lblTienPhaiTra.setText(dsHD.get(rowHD).getTongTien() + "");
                        JOptionPane.showMessageDialog(this, "Xoá thành công !");

                    } else {
                        JOptionPane.showMessageDialog(this, "Xoá thất bại");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá !");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn trước khi xoá sản phẩm !");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int rowHD = tblHoaDonCho.getSelectedRow();
        if (rowHD >= 0) {
            if (tblHoaDonCho.getValueAt(rowHD, 6) == "Đã thanh toán" || tblHoaDonCho.getValueAt(rowHD, 6) == "Đã huỷ") {
                JOptionPane.showMessageDialog(this, "Không thể huỷ hoá đơn này !");
            } else {
                if (tblCTHD.getRowCount() == 0) {
                    try {
                        Connection conn = JDBCHelper.getConnection();
                        PreparedStatement stm = conn.prepareStatement("update hoadon set trangthai = ? where idhoadon = ?");
                        stm.setInt(1, 2);
                        stm.setInt(2, dsHD.get(rowHD).getIDHoaDon());
                        stm.executeUpdate();
                        conn.close();
                        stm.close();
                        loadDataHD();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng xoá hết sản phẩm trong giỏ hàng để huỷ hoá đơn !");
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int cbott = jComboBox1.getSelectedIndex();
        dsHD = serviceHD.getAll();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        model1 = (DefaultTableModel) tblCTHD.getModel();
        String tt;
        if (cbott == 0) {
            model1.setRowCount(0);
            for (HoaDon hd : dsHD) {
                if (hd.getTrangThai() == 0) {
                    if (hd.getTrangThai() == 0) {
                        tt = "Chưa thanh toán";
                    } else if (hd.getTrangThai() == 1) {
                        tt = "Đã thanh toán";
                    } else {
                        tt = "Đã huỷ";
                    }
                    model.addRow(new Object[]{
                        hd.getIDHoaDon(),
                        hd.getKhachHang(),
                        hd.getNhanVien(),
                        hd.getNgayTao(),
                        hd.getKhuyenmai(),
                        formatter.format(hd.getTongTien()),
                        tt
                    });
                }
            }
        } else if (cbott == 1) {
            model1.setRowCount(0);
            for (HoaDon hd : dsHD) {
                if (hd.getTrangThai() == 1) {
                    if (hd.getTrangThai() == 0) {
                        tt = "Chưa thanh toán";
                    } else if (hd.getTrangThai() == 1) {
                        tt = "Đã thanh toán";
                    } else {
                        tt = "Đã huỷ";
                    }
                    model.addRow(new Object[]{
                        hd.getIDHoaDon(),
                        hd.getKhachHang(),
                        hd.getNhanVien(),
                        hd.getNgayTao(),
                        hd.getKhuyenmai(),
                        formatter.format(hd.getTongTien()),
                        tt
                    });
                }
            }
        } else {
            for (HoaDon hd : dsHD) {
                model1.setRowCount(0);
                if (hd.getTrangThai() == 2) {
                    if (hd.getTrangThai() == 0) {
                        tt = "Chưa thanh toán";
                    } else if (hd.getTrangThai() == 1) {
                        tt = "Đã thanh toán";
                    } else {
                        tt = "Đã huỷ";
                    }
                    model.addRow(new Object[]{
                        hd.getIDHoaDon(),
                        hd.getKhachHang(),
                        hd.getNhanVien(),
                        hd.getNgayTao(),
                        hd.getKhuyenmai(),
                        formatter.format(hd.getTongTien()),
                        tt
                    });
                }
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Comming soon !");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameDeactivated

    private void formInternalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeiconified
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameDeiconified

    private void btnThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhActionPerformed
        // TODO add your handling code here:
        if (validateThemNhanh()) {
            String ten = txtTenKH.getText().trim();
            String sdt = txtSDTKH.getText().trim();
            if (serviceKH.insertNhanh(ten, sdt)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
                initComboBoxKH();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_btnThemNhanhActionPerformed

    private void tblHoaDonChoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseDragged
        // TODO add your handling code here:
        int rowCount = tblHoaDonCho.getSelectedRowCount();
        if (rowCount > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblHoaDonCho.clearSelection();
        }
    }//GEN-LAST:event_tblHoaDonChoMouseDragged

    private void tblHoaDonChoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonChoMouseEntered

    private void tblChiTietSPMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseDragged
        // TODO add your handling code here:
        int rowCount = tblChiTietSP.getSelectedRowCount();
        if (rowCount > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblChiTietSP.clearSelection();
        }
    }//GEN-LAST:event_tblChiTietSPMouseDragged

    private void tblCTHDMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHDMouseDragged
        // TODO add your handling code here:
        int rowCount = tblCTHD.getSelectedRowCount();
        if (rowCount > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblCTHD.clearSelection();
        }
    }//GEN-LAST:event_tblCTHDMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemNhanh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboKM;
    private javax.swing.JComboBox<String> cboPhuongThuc;
    private javax.swing.JComboBox<String> cboTenKH;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblTienPhaiTra;
    private javax.swing.JLabel lblTienTra;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienDua;
    // End of variables declaration//GEN-END:variables
}
