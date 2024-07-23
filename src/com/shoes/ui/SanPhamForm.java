/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.shoes.ui;

import com.shoes.dao.ChatLieuDAO;
import com.shoes.dao.KichCoDAO;
import com.shoes.dao.KieuDangDAO;
import com.shoes.dao.MauSacDAO;
import com.shoes.dao.NhaCungCapDAO;
import com.shoes.dao.PhongCachDAO;
import com.shoes.dao.SanPhamChiTietDAO;
import com.shoes.dao.SanPhamDAO;
import com.shoes.dao.ShowdataSanPham;
import com.shoes.dao.ThuongHieuDAO;
import com.shoes.model.ChatLieu;
import com.shoes.model.PhongCach;
import com.shoes.model.KieuDang;
import com.shoes.model.MauSac;
import com.shoes.model.NhaCungCap;
import com.shoes.model.SanPham;
import com.shoes.model.SanPhamChiTiet;
import com.shoes.model.ThuongHieu;
import com.shoes.model.KichCo;
import java.awt.Rectangle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class SanPhamForm extends javax.swing.JInternalFrame {

    NumberFormat formatter = new DecimalFormat("#,###.###");
    NumberFormat formatter1 = new DecimalFormat("#.##");
    DefaultTableModel model;
    MauSacDAO serviceMS = new MauSacDAO();
    List<MauSac> dsMS = new ArrayList<>();
    KieuDangDAO serviceKD = new KieuDangDAO();
    List<KieuDang> dsKD = new ArrayList<>();
    KichCoDAO serviceKC = new KichCoDAO();
    List<KichCo> dsKC = new ArrayList<>();
    ChatLieuDAO serviceCL = new ChatLieuDAO();
    List<ChatLieu> dsCL = new ArrayList<>();
    PhongCachDAO servicePC = new PhongCachDAO();
    List<PhongCach> dsPC = new ArrayList<>();
    ThuongHieuDAO serviceTH = new ThuongHieuDAO();
    List<ThuongHieu> dsTH = new ArrayList<>();
    NhaCungCapDAO serviceNCC = new NhaCungCapDAO();
    List<NhaCungCap> dsNCC = new ArrayList<>();
    SanPhamDAO serviceSP = new SanPhamDAO();
    List<SanPham> dsSP = new ArrayList<>();
    SanPhamChiTietDAO serviceSPCT = new SanPhamChiTietDAO();
    List<SanPhamChiTiet> dsSPCT = new ArrayList<>();
    ShowdataSanPham showdataSanPham = new ShowdataSanPham();

    /**
     * Creates new form SanPhamForm
     */
    public SanPhamForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadDataMS();
        loadDataKD();
        loadDataKC();
        loadDataCL();
        loadDataPC();
        loadDataTH();
        loadDataNCC();
        loadDataSP();
        loadDataSPCT();
        initComboBoxTH();
        initComboBoxNCC();
        initComboBoxMS();
        initComboBoxCL();
        initComboBoxKD();
        initComboBoxPC();
        initComboBoxKC();
        initComboBoxLCL();
        initComboBoxLMS();
        initComboBoxLTH();
        initComboBoxLKD();
    }

    public void initComboBoxTH() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (ThuongHieu th : dsTH) {
            if (th.getTrangThai() == 0) {
                cbo.addElement(th.getTenThuongHieu());
            }
        }
        cboTH.setModel(cbo);
    }

    public void initComboBoxLTH() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        cbo.addElement("All");
        for (ThuongHieu th : dsTH) {
            if (th.getTrangThai() == 0) {
                cbo.addElement(th.getTenThuongHieu());
            }
        }
        CBOLocTH.setModel(cbo);
    }

    public void initComboBoxNCC() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (NhaCungCap ncc : dsNCC) {
            if (ncc.getTrangThai() == 0) {
                cbo.addElement(ncc.getTenNhaCungCap());
            }
        }
        cboNCC.setModel(cbo);
    }

    public void initComboBoxMS() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (MauSac m : dsMS) {
            if (m.getTrangThai() == 0) {
                cbo.addElement(m.getTenMauSac());
            }
        }
        cboMS.setModel(cbo);
    }

    public void initComboBoxLMS() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        cbo.addElement("All");
        for (MauSac m : dsMS) {
            if (m.getTrangThai() == 0) {
                cbo.addElement(m.getTenMauSac());
            }
        }
        cboLocMS.setModel(cbo);
    }

    public void initComboBoxCL() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (ChatLieu cl : dsCL) {
            if (cl.getTrangThai() == 0) {
                cbo.addElement(cl.getTenChatLieu());
            }
        }
        cboCL.setModel(cbo);
    }

    public void initComboBoxLCL() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        cbo.addElement("All");
        for (ChatLieu cl : dsCL) {
            if (cl.getTrangThai() == 0) {
                cbo.addElement(cl.getTenChatLieu());
            }
        }
        cboLocCL.setModel(cbo);
    }
    public void initComboBoxLKD() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        cbo.addElement("All");
        for (KieuDang kd : dsKD) {
            if (kd.getTrangThai() == 0) {
                cbo.addElement(kd.getTenKieuDang());
            }
        }
        cboLKD.setModel(cbo);
    }

    public void initComboBoxKD() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (KieuDang kd : dsKD) {
            if (kd.getTrangThai() == 0) {
                cbo.addElement(kd.getTenKieuDang());
            }
        }
        cboKieuDang.setModel(cbo);
    }

    public void initComboBoxPC() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (PhongCach pc : dsPC) {
            if (pc.getTrangThai() == 0) {
                cbo.addElement(pc.getTenPhongCach());
            }
        }
        cboPC.setModel(cbo);
    }

    public void initComboBoxKC() {
        DefaultComboBoxModel<String> cbo = new DefaultComboBoxModel<>();
        for (KichCo kc : dsKC) {
            if (kc.getTrangThai() == 0) {
                cbo.addElement(kc.getTenKichCo());
            }
        }
        cboKC.setModel(cbo);
    }

    void loadDataMS() {
        model = (DefaultTableModel) tblMauSac.getModel();
        model.setRowCount(0);
        dsMS = serviceMS.getAll();
        for (MauSac m : dsMS) {
            model.addRow(new Object[]{
                m.getIDMauSac(),
                m.getTenMauSac(),
                m.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    MauSac getFormMS() {
        String id = txtMaMau.getText();
        String ten = txtTenMau.getText();
        int tt;
        if (rdoHDMS.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new MauSac(id, ten, tt);
    }

    void loadDataKD() {
        model = (DefaultTableModel) tblKieuDang.getModel();
        model.setRowCount(0);
        dsKD = serviceKD.getAll();
        for (KieuDang k : dsKD) {
            model.addRow(new Object[]{
                k.getIDKieuDang(),
                k.getTenKieuDang(),
                k.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    KieuDang getFormKD() {
        String id = txtMaKD.getText();
        String ten = txtTenKD.getText();
        int tt;
        if (rdoHDKD.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new KieuDang(id, ten, tt);
    }

    void loadDataKC() {
        model = (DefaultTableModel) tblKichCo.getModel();
        model.setRowCount(0);
        dsKC = serviceKC.getAll();
        for (KichCo kc : dsKC) {
            model.addRow(new Object[]{
                kc.getIDKichCo(),
                kc.getTenKichCo(),
                kc.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    KichCo getFormKC() {
        String id = txtMaKc.getText();
        String ten = txtTenKC.getText();
        int tt;
        if (rdoHDT.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new KichCo(id, ten, tt);
    }

    void loadDataCL() {
        model = (DefaultTableModel) tblChatLieu.getModel();
        model.setRowCount(0);
        dsCL = serviceCL.getAll();
        for (ChatLieu cl : dsCL) {
            model.addRow(new Object[]{
                cl.getIDChatLieu(),
                cl.getTenChatLieu(),
                cl.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    ChatLieu getFormCL() {
        String id = txtMaCL.getText();
        String ten = txtTenCl.getText();
        int tt;
        if (rdoHDCL.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new ChatLieu(id, ten, tt);
    }

    void loadDataPC() {
        model = (DefaultTableModel) tblPhongCach.getModel();
        model.setRowCount(0);
        dsPC = servicePC.getAll();
        for (PhongCach pc : dsPC) {
            model.addRow(new Object[]{
                pc.getIDPhongCach(),
                pc.getTenPhongCach(),
                pc.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    PhongCach getFormPC() {
        String id = txtMaPC.getText();
        String ten = txtTePC.getText();
        int tt;
        if (rdoHDGC.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new PhongCach(id, ten, tt);
    }

    void loadDataTH() {
        model = (DefaultTableModel) tblThuongHieu.getModel();
        model.setRowCount(0);
        dsTH = serviceTH.getAll();
        for (ThuongHieu t : dsTH) {
            model.addRow(new Object[]{
                t.getIDThuongHieu(),
                t.getTenThuongHieu(),
                t.getMota(),
                t.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    ThuongHieu getFormTH() {
        String id = txtMaTH.getText();
        String ten = txtTenTH.getText();
        String mota = txtMota.getText();
        int tt;
        if (rdoHDTH.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new ThuongHieu(id, ten, mota, tt);
    }

    void loadDataNCC() {
        model = (DefaultTableModel) tblNhaCungCap.getModel();
        model.setRowCount(0);
        dsNCC = serviceNCC.getAll();
        for (NhaCungCap ncc : dsNCC) {
            model.addRow(new Object[]{
                ncc.getIDNhaCungCap(),
                ncc.getTenNhaCungCap(),
                ncc.getDiaChi(),
                ncc.getSoDT(),
                ncc.getEmail(),
                ncc.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    NhaCungCap getFormNCC() {
        String id = txtMaNCC.getText();
        String ten = txtTenNCC.getText();
        String dc = txtDC.getText();
        String sodt = txtSoDT.getText();
        String mail = txtEmail.getText();
        int tt;
        if (rdoHDNCC.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new NhaCungCap(id, ten, dc, sodt, mail, tt);
    }

    void loadDataSP() {
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        dsSP = serviceSP.getAll();
        for (SanPham sp : dsSP) {
            model.addRow(new Object[]{
                sp.getIDSanPham(),
                sp.getTenSanPham(),
                sp.getThuongHieu(),
                sp.getNhaCungCap(),
                sp.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    SanPham getFormSP() {
        String id = txtMaSP.getText();
        String ten = txtTenSP.getText();
        String th = (String) cboTH.getSelectedItem();
        String ncc = (String) cboNCC.getSelectedItem();
        int tt;
        if (rdoSPHD.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new SanPham(id, th, ncc, ten, tt);
    }

    void loadDataSPCT() {
        model = (DefaultTableModel) tblChiTietSP.getModel();
        model.setRowCount(0);
        dsSPCT = serviceSPCT.getAll();
        for (SanPhamChiTiet ct : dsSPCT) {
            model.addRow(new Object[]{
                ct.getIDChiTietSanPham(),
                ct.getIDsanPham(),
                ct.getTensp(),
                ct.getThuonghieu(),
                ct.getNhacungcap(),
                ct.getMauSac(),
                ct.getChatLieu(),
                ct.getKieuDang(),
                ct.getPhongcach(),
                ct.getKichco(),
                ct.getMoTa(),
                formatter.format(ct.getGiaBan()),
                ct.getSoLuong(),
                ct.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động !"
            });
        }
    }

    SanPhamChiTiet getFormSPCT() {
        String idsp2 = MaSP2.getText();
        String idspct = txtMaCTSP.getText();
        String ms = (String) cboMS.getSelectedItem();
        String cl = (String) cboCL.getSelectedItem();
        String kd = (String) cboKieuDang.getSelectedItem();
        String pc = (String) cboPC.getSelectedItem();
        String kc = (String) cboKC.getSelectedItem();
        String mt = txtMoTaCTSP.getText();
        float gia = Float.valueOf(txtGia.getText());
        int sl = Integer.valueOf(txtSL.getText());
        int tt;
        if (rdoHDSPCT.isSelected()) {
            tt = 0;
        } else {
            tt = 1;
        }
        return new SanPhamChiTiet(idspct, idsp2, null, null, null, ms, cl, kd, pc, kc, mt, gia, sl, tt);
    }

    public boolean checkValidateSP() {
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã sản phẩm !");
            return false;
        }
        String maString = txtMaCTSP.getText().trim();
        boolean ktm = false;
        for (SanPham sp : dsSP) {
            if (maString.equals(sp.getIDSanPham())) {
                ktm = true;
                break;
            }
        }
        if (ktm) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại trong danh sách!");
            return false;
        }
        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên sản phẩm !");
            return false;
        }
        return true;
    }

    public boolean checkValidateSPCT() {
        dsSPCT = serviceSPCT.getAll();
        if (txtMaCTSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã sản phẩm !");
            return false;
        }
        String maString = txtMaCTSP.getText().trim();
        boolean ktm = false;
        for (SanPhamChiTiet spct : dsSPCT) {
            if (maString.equals(spct.getIDChiTietSanPham())) {
                ktm = true;
                break;
            }
        }
        if (ktm) {
            JOptionPane.showMessageDialog(this, "Mã chi tiết sản phẩm đã tồn tại trong danh sách!");
            return false;
        }
        if (txtSL.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu số lượng sản phẩm !");
            return false;
        }
        if (!txtSL.getText().trim().matches("^[1-9][0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 1");
            return false;
        }
        if (txtMoTaCTSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mô tả sản phẩm !");
            return false;
        }
        if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu giá sản phẩm !");
            return false;
        }
        if (!txtGia.getText().matches("^[1-9][0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Giá sản phẩm phải lớn hơn 0");
            return false;
        }
        return true;
    }

    public boolean checkValidateUpdateSPCT() {
        dsSPCT = serviceSPCT.getAll();
        if (txtMaCTSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã sản phẩm !");
            return false;
        }
        if (txtSL.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu số lượng sản phẩm !");
            return false;
        }
        if (!txtSL.getText().matches("^[1-9][0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 1");
            return false;
        }
        if (txtMoTaCTSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mô tả sản phẩm !");
            return false;
        }
        if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu giá sản phẩm !");
            return false;
        }
        if (!txtGia.getText().matches("^[1-9][0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Giá sản phẩm phải lớn hơn 0");
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
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        CBOLocTH = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboLocCL = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboLocMS = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cboLKD = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        cboKC = new javax.swing.JComboBox<>();
        cboPC = new javax.swing.JComboBox<>();
        cboKieuDang = new javax.swing.JComboBox<>();
        cboCL = new javax.swing.JComboBox<>();
        cboMS = new javax.swing.JComboBox<>();
        txtSL = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtMoTaCTSP = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        rdoHDSPCT = new javax.swing.JRadioButton();
        rdoKHDSPCT = new javax.swing.JRadioButton();
        jLabel54 = new javax.swing.JLabel();
        txtMaCTSP = new javax.swing.JTextField();
        MaSP2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboTH = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboNCC = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        SPKHD = new javax.swing.JRadioButton();
        jLabel53 = new javax.swing.JLabel();
        rdoSPHD = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtMaKc = new javax.swing.JTextField();
        txtTenKC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rdoHDT = new javax.swing.JRadioButton();
        rdoKoHDT = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKichCo = new javax.swing.JTable();
        btnThemTrong = new javax.swing.JButton();
        btnSuaTrong = new javax.swing.JButton();
        btnResetTrong = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        rdoHDCL = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        txtTenCl = new javax.swing.JTextField();
        rdoKHDCL = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        txtMaCL = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        btnClearCL = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rdoHDGC = new javax.swing.JRadioButton();
        rdoKHDGC = new javax.swing.JRadioButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPhongCach = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        txtMaPC = new javax.swing.JTextField();
        txtTePC = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTenKD = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKieuDang = new javax.swing.JTable();
        txtMaKD = new javax.swing.JTextField();
        rdoHDKD = new javax.swing.JRadioButton();
        rdoKHDKD = new javax.swing.JRadioButton();
        btnSuaKD = new javax.swing.JButton();
        btnThemKD = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        rdoKHDMS = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        txtTenMau = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        rdoHDMS = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        txtMaMau = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        rdoHDTH = new javax.swing.JRadioButton();
        rdoKHDTH = new javax.swing.JRadioButton();
        txtTenTH = new javax.swing.JTextField();
        txtMaTH = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblThuongHieu = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        txtMota = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        rdoHDNCC = new javax.swing.JRadioButton();
        rdoKHDNCC = new javax.swing.JRadioButton();
        txtTenNCC = new javax.swing.JTextField();
        txtMaNCC = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNhaCungCap = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        txtDC = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setBackground(new java.awt.Color(0, 204, 204));

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 710));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Thương Hiệu");

        CBOLocTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thuong Hieu A", "Thuong Hieu B", "Thuong Hieu C", " " }));
        CBOLocTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBOLocTHActionPerformed(evt);
            }
        });

        jButton1.setText("Xuất File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SPCT", "Mã sản phẩm", "Tên sản phẩm", "Thương hiệu", "Nhà cung cấp", "Màu sắc", "Chất liệu", "Kiểu dáng", "Phong cách", "Kích cỡ", "Mô tả", "Giá bán", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
        jScrollPane9.setViewportView(tblChiTietSP);
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
            tblChiTietSP.getColumnModel().getColumn(12).setResizable(false);
            tblChiTietSP.getColumnModel().getColumn(13).setResizable(false);
        }

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/shoes/img/Search.png"))); // NOI18N
        jLabel2.setText("Tìm Kiếm");

        jLabel7.setText("Chất Liệu");

        cboLocCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chất liệu 1", "Chất liệu 2" }));
        cboLocCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocCLActionPerformed(evt);
            }
        });

        jLabel8.setText("Màu Sắc");

        cboLocMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Màu sắc 1", "Màu sắc 2", "Màu sắc 3" }));
        cboLocMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocMSActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel9.setText("Kiểu dáng");

        cboLKD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLKDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CBOLocTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocMS, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocCL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(cboLKD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addGap(113, 113, 113))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CBOLocTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboLocMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboLKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnReset))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel11);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel31.setText("Mã sản phẩm");

        jLabel32.setText("Màu sắc");

        jLabel33.setText("Chất liệu");

        jLabel44.setText("Kiểu dáng");

        jLabel45.setText("Phong cách");

        jLabel46.setText("Kích cỡ");

        cboKC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKCActionPerformed(evt);
            }
        });

        cboPC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel47.setText("Số lượng");

        jLabel49.setText("Mô tả");

        txtMoTaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaCTSPActionPerformed(evt);
            }
        });

        jLabel50.setText("Giá bán");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        jButton17.setText("Thêm");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Sửa");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton26.setText("Làm mới");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel52.setText("Trạng thái");

        buttonGroup10.add(rdoHDSPCT);
        rdoHDSPCT.setSelected(true);
        rdoHDSPCT.setText("Hoạt động");

        buttonGroup10.add(rdoKHDSPCT);
        rdoKHDSPCT.setText("Không hoạt động");

        jLabel54.setText("Mã chi tiết sp");

        MaSP2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MaSP2.setForeground(new java.awt.Color(255, 51, 51));
        MaSP2.setText("SP001");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46)
                            .addComponent(jLabel44)
                            .addComponent(jLabel33)
                            .addComponent(jLabel54)
                            .addComponent(jLabel31))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MaSP2)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaCTSP)
                                    .addComponent(cboMS, 0, 370, Short.MAX_VALUE)
                                    .addComponent(cboCL, 0, 370, Short.MAX_VALUE)
                                    .addComponent(cboKieuDang, 0, 370, Short.MAX_VALUE)
                                    .addComponent(cboPC, 0, 370, Short.MAX_VALUE)
                                    .addComponent(cboKC, 0, 370, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel49)
                                            .addComponent(jLabel52))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(rdoHDSPCT)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoKHDSPCT))
                                            .addComponent(txtMoTaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addGap(26, 26, 26)
                                        .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(MaSP2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(txtMoTaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(rdoHDSPCT)
                        .addComponent(rdoKHDSPCT))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(cboPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lí sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã sản phẩm");

        jLabel3.setText("Tên sản phẩm");

        jLabel4.setText("Thương hiệu");

        cboTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thương hiệu A", "Thương hiệu B", "Thương hiệu C" }));
        cboTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTHActionPerformed(evt);
            }
        });

        jLabel5.setText("Nhà cung cấp");

        cboNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà cung cấp A", "Nhà cung cấp B", "Nhà cung cấp C" }));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Thương hiệu", "Nhà cung cấp", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseDragged(evt);
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("Sửa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Làm mới");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        buttonGroup8.add(SPKHD);
        SPKHD.setText("Không hoạt động");

        jLabel53.setText("Trạng thái");

        buttonGroup8.add(rdoSPHD);
        rdoSPHD.setSelected(true);
        rdoSPHD.setText("Hoạt động");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(1, 1, 1))
                        .addComponent(jLabel3))
                    .addComponent(jLabel53))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTH, 0, 300, Short.MAX_VALUE)
                            .addComponent(txtTenSP)
                            .addComponent(txtMaSP)
                            .addComponent(cboNCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdoSPHD)
                        .addGap(18, 18, 18)
                        .addComponent(SPKHD)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jLabel3)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53)
                        .addComponent(rdoSPHD))
                    .addComponent(SPKHD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cập nhật", jPanel14);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 680));

        jTabbedPane2.addTab("Thông tin chi tiết", jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kích cỡ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel14.setText("Mã kích cỡ");

        jLabel16.setText("Tên kích cỡ");

        jLabel18.setText("Trạng thái");

        buttonGroup1.add(rdoHDT);
        rdoHDT.setSelected(true);
        rdoHDT.setText("Hoạt động");

        buttonGroup1.add(rdoKoHDT);
        rdoKoHDT.setText("Không hoạt động");

        tblKichCo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã kích cỡ", "Tên kích cỡ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKichCo.getTableHeader().setReorderingAllowed(false);
        tblKichCo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblKichCoMouseDragged(evt);
            }
        });
        tblKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichCoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKichCo);
        if (tblKichCo.getColumnModel().getColumnCount() > 0) {
            tblKichCo.getColumnModel().getColumn(0).setResizable(false);
            tblKichCo.getColumnModel().getColumn(1).setResizable(false);
            tblKichCo.getColumnModel().getColumn(2).setResizable(false);
        }

        btnThemTrong.setText("Thêm");
        btnThemTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTrongActionPerformed(evt);
            }
        });

        btnSuaTrong.setText("Sửa");
        btnSuaTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTrongActionPerformed(evt);
            }
        });

        btnResetTrong.setText("Làm mới");
        btnResetTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTrongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnThemTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSuaTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKc)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rdoHDT)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKoHDT)
                                .addGap(0, 82, Short.MAX_VALUE))
                            .addComponent(txtTenKC))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMaKc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTenKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(rdoHDT)
                    .addComponent(rdoKoHDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTrong)
                    .addComponent(btnSuaTrong)
                    .addComponent(btnResetTrong))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel6);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chất liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel27.setText("Trạng thái");

        buttonGroup2.add(rdoHDCL);
        rdoHDCL.setSelected(true);
        rdoHDCL.setText("Hoạt động");

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã chất liệu", "Tên chất liệu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChatLieu.getTableHeader().setReorderingAllowed(false);
        tblChatLieu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseDragged(evt);
            }
        });
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblChatLieu);
        if (tblChatLieu.getColumnModel().getColumnCount() > 0) {
            tblChatLieu.getColumnModel().getColumn(0).setResizable(false);
            tblChatLieu.getColumnModel().getColumn(1).setResizable(false);
            tblChatLieu.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel26.setText("Tên chất liệu");

        buttonGroup2.add(rdoKHDCL);
        rdoKHDCL.setText("Không hoạt động");

        jLabel25.setText("Mã chất liệu");

        jButton8.setText("Sửa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnClearCL.setText("Làm mới");
        btnClearCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCLActionPerformed(evt);
            }
        });

        jButton9.setText("Thêm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(20, 20, 20)
                        .addComponent(txtMaCL))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(rdoHDCL)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHDCL)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenCl)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(btnClearCL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtMaCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTenCl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(rdoHDCL)
                    .addComponent(rdoKHDCL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton8)
                    .addComponent(btnClearCL))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phong cách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel28.setText("Mã phong cách");

        jLabel30.setText("Trạng thái");

        buttonGroup3.add(rdoHDGC);
        rdoHDGC.setSelected(true);
        rdoHDGC.setText("Hoạt động");

        buttonGroup3.add(rdoKHDGC);
        rdoKHDGC.setText("Không hoạt động");

        tblPhongCach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã phong cách", "Tên phong cách", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhongCach.getTableHeader().setReorderingAllowed(false);
        tblPhongCach.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblPhongCachMouseDragged(evt);
            }
        });
        tblPhongCach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhongCachMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblPhongCach);
        if (tblPhongCach.getColumnModel().getColumnCount() > 0) {
            tblPhongCach.getColumnModel().getColumn(0).setResizable(false);
            tblPhongCach.getColumnModel().getColumn(1).setResizable(false);
            tblPhongCach.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel29.setText("Tên phong cách");

        jButton12.setText("Thêm");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton11.setText("Sửa");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setText("Làm mới");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPC)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(rdoHDGC)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHDGC)
                                .addGap(0, 57, Short.MAX_VALUE))
                            .addComponent(txtTePC))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtMaPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtTePC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(rdoHDGC)
                    .addComponent(rdoKHDGC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton11)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel9);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kiểu dáng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel21.setText("Trạng thái");

        jLabel20.setText("Tên kiểu dáng");

        jLabel19.setText("Mã kiểu dáng");

        tblKieuDang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã kiểu dáng", "Tên kiểu dáng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKieuDang.getTableHeader().setReorderingAllowed(false);
        tblKieuDang.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblKieuDangMouseDragged(evt);
            }
        });
        tblKieuDang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKieuDangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKieuDang);
        if (tblKieuDang.getColumnModel().getColumnCount() > 0) {
            tblKieuDang.getColumnModel().getColumn(0).setResizable(false);
            tblKieuDang.getColumnModel().getColumn(1).setResizable(false);
            tblKieuDang.getColumnModel().getColumn(2).setResizable(false);
        }

        buttonGroup4.add(rdoHDKD);
        rdoHDKD.setSelected(true);
        rdoHDKD.setText("Hoạt động");

        buttonGroup4.add(rdoKHDKD);
        rdoKHDKD.setText("Không hoạt động");

        btnSuaKD.setText("Sửa");
        btnSuaKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKDActionPerformed(evt);
            }
        });

        btnThemKD.setText("Thêm");
        btnThemKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKDActionPerformed(evt);
            }
        });

        jButton19.setText("Làm mới");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(20, 20, 20)
                        .addComponent(txtMaKD))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdoHDKD)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHDKD)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenKD)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnThemKD, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSuaKD, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMaKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTenKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(rdoHDKD)
                    .addComponent(rdoKHDKD))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKD)
                    .addComponent(btnSuaKD)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel7);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Màu sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        buttonGroup5.add(rdoKHDMS);
        rdoKHDMS.setText("Không hoạt động");

        jLabel22.setText("Mã màu sắc");

        jLabel23.setText("Tên màu sắc");

        jLabel24.setText("Trạng thái");

        buttonGroup5.add(rdoHDMS);
        rdoHDMS.setSelected(true);
        rdoHDMS.setText("Hoạt động");

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã màu sắc", "Tên màu sắc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSac.getTableHeader().setReorderingAllowed(false);
        tblMauSac.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblMauSacMouseDragged(evt);
            }
        });
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMauSac);
        if (tblMauSac.getColumnModel().getColumnCount() > 0) {
            tblMauSac.getColumnModel().getColumn(0).setResizable(false);
            tblMauSac.getColumnModel().getColumn(1).setResizable(false);
            tblMauSac.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton16.setText("Làm mới");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton14.setText("Thêm");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Sửa");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(20, 20, 20)
                        .addComponent(txtMaMau))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(rdoHDMS)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHDMS)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenMau)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtMaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(rdoHDMS)
                    .addComponent(rdoKHDMS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel10);

        jTabbedPane2.addTab("Thuộc tính sản phẩm", jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thương hiệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel34.setText("Mã thương hiệu");

        jLabel35.setText("Tên thương hiệu");

        jLabel36.setText("Trạng thái");

        buttonGroup6.add(rdoHDTH);
        rdoHDTH.setText("Hoạt động");

        buttonGroup6.add(rdoKHDTH);
        rdoKHDTH.setText("Không hoạt động");

        jButton20.setText("Thêm");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Sửa");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Làm mới");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã thương hiệu", "Tên thương hiệu", "Mô tả", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuongHieu.getTableHeader().setReorderingAllowed(false);
        tblThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblThuongHieu);
        if (tblThuongHieu.getColumnModel().getColumnCount() > 0) {
            tblThuongHieu.getColumnModel().getColumn(0).setResizable(false);
            tblThuongHieu.getColumnModel().getColumn(1).setResizable(false);
            tblThuongHieu.getColumnModel().getColumn(2).setResizable(false);
            tblThuongHieu.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel40.setText("Mô tả");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(20, 20, 20)
                        .addComponent(txtMaTH))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel40))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenTH)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(rdoHDTH)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHDTH)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtMota))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtMaTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtTenTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(rdoHDTH)
                    .addComponent(rdoKHDTH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton20)
                    .addComponent(jButton22))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        jPanel3.add(jPanel12);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel37.setText("Mã cung cấp");

        jLabel38.setText("Tên NCC");

        jLabel39.setText("Trạng thái");

        buttonGroup7.add(rdoHDNCC);
        rdoHDNCC.setText("Hoạt động");

        buttonGroup7.add(rdoKHDNCC);
        rdoKHDNCC.setText("Không hoạt động");

        jButton23.setText("Thêm");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText("Sửa");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setText("Làm mới");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Email", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhaCungCap.getTableHeader().setReorderingAllowed(false);
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblNhaCungCap);
        if (tblNhaCungCap.getColumnModel().getColumnCount() > 0) {
            tblNhaCungCap.getColumnModel().getColumn(0).setResizable(false);
            tblNhaCungCap.getColumnModel().getColumn(1).setResizable(false);
            tblNhaCungCap.getColumnModel().getColumn(2).setResizable(false);
            tblNhaCungCap.getColumnModel().getColumn(3).setResizable(false);
            tblNhaCungCap.getColumnModel().getColumn(4).setResizable(false);
            tblNhaCungCap.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel41.setText("Địa chỉ");

        jLabel42.setText("Số ĐT");

        jLabel43.setText("Email");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .addComponent(txtTenNCC)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(49, 49, 49)
                        .addComponent(txtDC))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(54, 54, 54)
                        .addComponent(txtSoDT))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel39))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(rdoHDNCC)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHDNCC)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtEmail))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(rdoHDNCC)
                    .addComponent(rdoKHDNCC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23)
                    .addComponent(jButton24)
                    .addComponent(jButton25))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel13);

        jTabbedPane2.addTab("Quản lí nguồn cung", jPanel3);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        MauSac m = getFormMS();
        if (serviceMS.insert(m)) {
            loadDataMS();
            initComboBoxMS();
            initComboBoxLMS();
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại !");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        if (txtMaMau.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã !");
        } else if (txtTenMau.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên !");
        } else {
            MauSac m = getFormMS();
            if (serviceMS.update(m)) {
                loadDataMS();
                initComboBoxMS();
                initComboBoxLMS();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnThemKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKDActionPerformed
        // TODO add your handling code here:
        if (txtMaKD.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã !");
        } else if (txtTenKD.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên !");
        } else {
            KieuDang k = getFormKD();
            if (serviceKD.insert(k)) {
                loadDataKD();
                initComboBoxKD();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_btnThemKDActionPerformed

    private void btnSuaKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKDActionPerformed
        // TODO add your handling code here:
        if (txtMaKD.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã !");
        } else if (txtTenKD.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên !");
        } else {
            KieuDang k = getFormKD();
            if (serviceKD.update(k)) {
                loadDataKD();
                initComboBoxKD();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_btnSuaKDActionPerformed

    private void btnThemTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTrongActionPerformed
        // TODO add your handling code here:
        if (txtMaKc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenKC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            KichCo kc = getFormKC();
            if (serviceKC.insert(kc)) {
                loadDataKC();
                initComboBoxKC();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_btnThemTrongActionPerformed

    private void btnSuaTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTrongActionPerformed
        // TODO add your handling code here:

        if (txtMaKc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenKC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            KichCo kc = getFormKC();
            if (serviceKC.update(kc)) {
                loadDataKC();
                initComboBoxKC();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_btnSuaTrongActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (txtMaCL.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenCl.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            ChatLieu cl = getFormCL();
            if (serviceCL.insert(cl)) {
                loadDataCL();
                initComboBoxCL();
                initComboBoxLCL();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

        if (txtMaCL.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenCl.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            ChatLieu cl = getFormCL();
            if (serviceCL.update(cl)) {
                loadDataCL();
                initComboBoxCL();
                initComboBoxLCL();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if (txtMaPC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTePC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            PhongCach pc = getFormPC();
            if (servicePC.insert(pc)) {
                loadDataPC();
                initComboBoxPC();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if (txtMaPC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTePC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            PhongCach pc = getFormPC();
            if (servicePC.update(pc)) {
                loadDataPC();
                initComboBoxPC();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        if (txtMaTH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenTH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            ThuongHieu t = getFormTH();
            if (serviceTH.insert(t)) {
                loadDataTH();
                initComboBoxTH();
                initComboBoxLTH();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:

        if (txtMaTH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenTH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            ThuongHieu t = getFormTH();
            if (serviceTH.update(t)) {
                loadDataTH();
                initComboBoxTH();
                initComboBoxLTH();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:

        if (txtMaNCC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenNCC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            NhaCungCap ncc = getFormNCC();
            if (serviceNCC.insert(ncc)) {
                loadDataNCC();
                initComboBoxNCC();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        if (txtMaNCC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã");
        } else if (txtTenNCC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên");
        } else {
            NhaCungCap ncc = getFormNCC();
            if (serviceNCC.update(ncc)) {
                loadDataNCC();
                initComboBoxNCC();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void txtMoTaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaCTSPActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SanPham sp = getFormSP();
        if (checkValidateSP()) {
            if (serviceSP.insert(sp)) {
                loadDataSP();
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String ma = txtMaSP.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã !");
        } else if (txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên sản phẩm");
        } else {
            SanPham sp = getFormSP();
            if (serviceSP.update(sp)) {
                loadDataSP();
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!");
            }
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        if (checkValidateSPCT()) {
            SanPhamChiTiet ct = getFormSPCT();
            if (serviceSPCT.insert(ct)) {
                loadDataSPCT();
                jTabbedPane1.setSelectedIndex(0);
                int row = tblChiTietSP.getRowCount();
                tblChiTietSP.getSelectionModel().setSelectionInterval(row - 1, row - 1);
                tblChiTietSP.scrollRectToVisible(new Rectangle(tblChiTietSP.getCellRect(row - 1, 0, true)));
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        if (row >= 0) {
            txtMaSP.setText(dsSP.get(row).getIDSanPham());
            txtTenSP.setText(dsSP.get(row).getTenSanPham());
            cboTH.setSelectedItem(dsSP.get(row).getThuongHieu());
            cboNCC.setSelectedItem(dsSP.get(row).getNhaCungCap());
            txtMaSP.setText(dsSP.get(row).getIDSanPham());
            if (dsSP.get(row).getTrangThai() == 0) {
                rdoSPHD.setSelected(true);
            } else {
                SPKHD.setSelected(true);
            }
            MaSP2.setText(dsSP.get(row).getIDSanPham());

        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        if (checkValidateUpdateSPCT()) {
            SanPhamChiTiet ct = getFormSPCT();
            if (serviceSPCT.update(ct)) {
                loadDataSPCT();

                for (int i = 0; i < dsSPCT.size(); i++) {
                    if (tblChiTietSP.getValueAt(i, 0).equals(ct.getIDChiTietSanPham())) {
                        jTabbedPane1.setSelectedIndex(0);
                        tblChiTietSP.getSelectionModel().setSelectionInterval(i, i);
                        tblChiTietSP.scrollRectToVisible(new Rectangle(tblChiTietSP.getCellRect(i, 0, true)));
                    } else {
                        jTabbedPane1.setSelectedIndex(0);
                    }

                }
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        // TODO add your handling code here
        int row = tblChiTietSP.getSelectedRow();
        if (row >= 0) {
            txtMaSP.setText(dsSPCT.get(row).getIDsanPham());
            txtTenSP.setText(dsSPCT.get(row).getTensp());
            cboTH.setSelectedItem(dsSPCT.get(row).getThuonghieu());
            cboNCC.setSelectedItem(dsSPCT.get(row).getNhacungcap());
            MaSP2.setText(dsSPCT.get(row).getIDsanPham());
            txtMaCTSP.setText(dsSPCT.get(row).getIDChiTietSanPham());
            cboMS.setSelectedItem(dsSPCT.get(row).getMauSac());
            cboCL.setSelectedItem(dsSPCT.get(row).getChatLieu());
            cboKieuDang.setSelectedItem(dsSPCT.get(row).getKieuDang());
            cboPC.setSelectedItem(dsSPCT.get(row).getPhongcach());
            cboKC.setSelectedItem(dsSPCT.get(row).getKichco());
            txtSL.setText(dsSPCT.get(row).getSoLuong() + "");
            txtMoTaCTSP.setText(dsSPCT.get(row).getMoTa() + "");
            txtGia.setText(formatter1.format(dsSPCT.get(row).getGiaBan()) + "");
            if (dsSPCT.get(row).getTrangThai() == 0) {
                rdoHDSPCT.setSelected(true);
            } else {
                rdoKHDSPCT.setSelected(true);
            }
            jTabbedPane1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void tblKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichCoMouseClicked
        int row = tblKichCo.getSelectedRow();
        if (row >= 0) {
            txtMaKc.setText(dsKC.get(row).getIDKichCo());
            txtTenKC.setText(dsKC.get(row).getTenKichCo());
            if (dsKC.get(row).getTrangThai() == 0) {
                rdoHDT.setSelected(true);
            } else {
                rdoKoHDT.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblKichCoMouseClicked

    private void btnResetTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTrongActionPerformed
        // TODO add your handling code here:
        txtMaKc.setText("");
        txtTenKC.setText("");
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_btnResetTrongActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        int row = tblChatLieu.getSelectedRow();
        if (row >= 0) {
            txtMaCL.setText(dsCL.get(row).getIDChatLieu());
            txtTenCl.setText(dsCL.get(row).getTenChatLieu());
            if (dsCL.get(row).getTrangThai() == 0) {
                rdoHDCL.setSelected(true);
            } else {
                rdoKHDCL.setSelected(true);
            }
        }


    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void btnClearCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCLActionPerformed
        // TODO add your handling code here:
        txtMaCL.setText("");
        txtTenCl.setText("");
        buttonGroup2.clearSelection();
    }//GEN-LAST:event_btnClearCLActionPerformed

    private void tblPhongCachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongCachMouseClicked
        int row = tblPhongCach.getSelectedRow();
        if (row >= 0) {
            txtMaPC.setText(dsPC.get(row).getIDPhongCach());
            txtTePC.setText(dsPC.get(row).getTenPhongCach());
            if (dsPC.get(row).getTrangThai() == 0) {
                rdoHDGC.setSelected(true);
            } else {
                rdoKHDGC.setSelected(true);
            }
        } else {
        }
    }//GEN-LAST:event_tblPhongCachMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        txtMaPC.setText("");
        txtTePC.setText("");
        buttonGroup3.clearSelection();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void tblKieuDangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKieuDangMouseClicked
        // TODO add your handling code here:
        int row = tblKieuDang.getSelectedRow();
        if (row >= 0) {
            txtMaKD.setText(dsKD.get(row).getIDKieuDang());
            txtTenKD.setText(dsKD.get(row).getTenKieuDang());
            if (dsKD.get(row).getTrangThai() == 0) {
                rdoHDKD.setSelected(true);
            } else {
                rdoKHDKD.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblKieuDangMouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        txtMaKD.setText("");
        txtTenKD.setText("");
        buttonGroup4.clearSelection();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        txtMaMau.setText("");
        txtTenMau.setText("");
        buttonGroup5.clearSelection();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        int row = tblMauSac.getSelectedRow();
        if (row >= 0) {
            txtMaMau.setText(dsMS.get(row).getIDMauSac());
            txtTenMau.setText(dsMS.get(row).getTenMauSac());
            if (dsMS.get(row).getTrangThai() == 0) {
                rdoHDMS.setSelected(true);
            } else {
                rdoKHDMS.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void tblThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuMouseClicked
        // TODO add your handling code here:
        int row = tblThuongHieu.getSelectedRow();
        if (row >= 0) {
            txtMaTH.setText(dsTH.get(row).getIDThuongHieu());
            txtTenTH.setText(dsTH.get(row).getTenThuongHieu());
            txtMota.setText(dsTH.get(row).getMota());
            if (dsTH.get(row).getTrangThai() == 0) {
                rdoHDTH.setSelected(true);
            } else {
                rdoKHDTH.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblThuongHieuMouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        txtMaTH.setText("");
        txtTenTH.setText("");
        txtMota.setText("");
        buttonGroup6.clearSelection();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void tblNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseClicked
        // TODO add your handling code here:
        int row = tblNhaCungCap.getSelectedRow();
        if (row >= 0) {
            txtMaNCC.setText(dsNCC.get(row).getIDNhaCungCap());
            txtTenNCC.setText(dsNCC.get(row).getTenNhaCungCap());
            txtDC.setText(dsNCC.get(row).getDiaChi());
            txtSoDT.setText(dsNCC.get(row).getSoDT());
            txtEmail.setText(dsNCC.get(row).getEmail());
            if (dsNCC.get(row).getTrangThai() == 0) {
                rdoHDNCC.setSelected(true);
            } else {
                rdoKHDNCC.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblNhaCungCapMouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        txtMaNCC.setText("");
        txtTenNCC.setText("");
        txtDC.setText("");
        txtSoDT.setText("");
        txtEmail.setText("");
        buttonGroup7.clearSelection();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void cboKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKCActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        txtMaSP.setText("");
        txtTenSP.setText("");
        buttonGroup8.clearSelection();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        txtMaCTSP.setText("");
        txtMoTaCTSP.setText("");
        txtGia.setText("");
        txtSL.setText("");
        buttonGroup10.clearSelection();
        buttonGroup9.clearSelection();

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Sản phẩm");
            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("IDChitietsanpham");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("ID Sản Phẩm");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên Sản Phẩm");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Thương hiệu");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Nhà cung cấp");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Màu Sắc");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Chất Liệu");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Kiểu dáng");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Phong cách");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Kích cỡ");

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Mô Tả");

            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Giá Bán");

            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Số Lượng");

            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("Trạng Thái");
            int rowIndex = 5;
            ArrayList<SanPhamChiTiet> list = (ArrayList<SanPhamChiTiet>) showdataSanPham.showSPCT(title);
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < dsSPCT.size(); i++) {
                    SanPhamChiTiet sanPham = dsSPCT.get(i);
                    row = spreadsheet.createRow(4 + i);

                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue(sanPham.getIDChiTietSanPham());

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(sanPham.getIDsanPham());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(sanPham.getTensp());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(sanPham.getThuonghieu());

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(sanPham.getNhacungcap());

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(sanPham.getMauSac());

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(sanPham.getChatLieu());

                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(sanPham.getKieuDang());

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(sanPham.getPhongcach());

                    cell = row.createCell(9, CellType.STRING);
                    cell.setCellValue(sanPham.getKichco());
                    cell = row.createCell(10, CellType.STRING);

                    cell.setCellValue(sanPham.getMoTa());
                    cell = row.createCell(11, CellType.STRING);
                    cell.setCellValue(sanPham.getGiaBan());

                    cell = row.createCell(12, CellType.STRING);
                    cell.setCellValue(sanPham.getSoLuong());

                    cell = row.createCell(13, CellType.STRING);
                    cell.setCellValue(sanPham.getTrangThai());
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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String TimKiem = txtTimKiem.getText().trim();
        if (TimKiem.equals("")) {
            loadDataSPCT();
        } else {
            SanPhamChiTietDAO sanPhamChiTietDAO = new SanPhamChiTietDAO();
            dsSPCT = sanPhamChiTietDAO.TimKiemgetAll(TimKiem);
            model = (DefaultTableModel) tblChiTietSP.getModel();
            model.setRowCount(0);
            for (SanPhamChiTiet ct : dsSPCT) {
                model.addRow(new Object[]{
                    ct.getIDChiTietSanPham(),
                    ct.getIDsanPham(),
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
                    ct.getSoLuong(),
                    ct.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động !"
                });
            }
        }

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTHActionPerformed

    private void CBOLocTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBOLocTHActionPerformed
        String TH = (String) CBOLocTH.getSelectedItem();
        if (TH.equalsIgnoreCase("All")) {
            loadDataSPCT();
        } else {
            SanPhamChiTietDAO spct = new SanPhamChiTietDAO();
            dsSPCT = spct.THgetAll(TH);
            model = (DefaultTableModel) tblChiTietSP.getModel();
            model.setRowCount(0);

            for (SanPhamChiTiet chiTiet : dsSPCT) {
                model.addRow(new Object[]{chiTiet.getIDChiTietSanPham(),
                    chiTiet.getIDsanPham(),
                    chiTiet.getTensp(),
                    chiTiet.getThuonghieu(),
                    chiTiet.getNhacungcap(),
                    chiTiet.getMauSac(),
                    chiTiet.getChatLieu(),
                    chiTiet.getKieuDang(),
                    chiTiet.getPhongcach(),
                    chiTiet.getKichco(),
                    chiTiet.getMoTa(),
                    chiTiet.getGiaBan(),
                    chiTiet.getSoLuong(),
                    chiTiet.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động !"
                });
            }
        }

    }//GEN-LAST:event_CBOLocTHActionPerformed

    private void cboLocCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocCLActionPerformed
        // TODO add your handling code here:
        String CL = (String) cboLocCL.getSelectedItem();
        if (CL.equalsIgnoreCase("All")) {
            loadDataSPCT();
        } else {
            SanPhamChiTietDAO spct = new SanPhamChiTietDAO();
            dsSPCT = spct.CLgetAll(CL);
            model = (DefaultTableModel) tblChiTietSP.getModel();
            model.setRowCount(0);

            for (SanPhamChiTiet chiTiet : dsSPCT) {
                model.addRow(new Object[]{chiTiet.getIDChiTietSanPham(),
                    chiTiet.getIDsanPham(),
                    chiTiet.getTensp(),
                    chiTiet.getThuonghieu(),
                    chiTiet.getNhacungcap(),
                    chiTiet.getMauSac(),
                    chiTiet.getChatLieu(),
                    chiTiet.getKieuDang(),
                    chiTiet.getPhongcach(),
                    chiTiet.getKichco(),
                    chiTiet.getMoTa(),
                    chiTiet.getGiaBan(),
                    chiTiet.getSoLuong(),
                    chiTiet.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động !"
                });
            }
        }

    }//GEN-LAST:event_cboLocCLActionPerformed

    private void cboLocMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocMSActionPerformed
        // TODO add your handling code here:
        String MS = (String) cboLocMS.getSelectedItem();
        if (MS.equalsIgnoreCase("All")) {
            loadDataSPCT();

        } else {
            SanPhamChiTietDAO spct = new SanPhamChiTietDAO();
            dsSPCT = spct.MSgetAll(MS);
            model = (DefaultTableModel) tblChiTietSP.getModel();
            model.setRowCount(0);

            for (SanPhamChiTiet chiTiet : dsSPCT) {
                model.addRow(new Object[]{chiTiet.getIDChiTietSanPham(),
                    chiTiet.getIDsanPham(),
                    chiTiet.getTensp(),
                    chiTiet.getThuonghieu(),
                    chiTiet.getNhacungcap(),
                    chiTiet.getMauSac(),
                    chiTiet.getChatLieu(),
                    chiTiet.getKieuDang(),
                    chiTiet.getPhongcach(),
                    chiTiet.getKichco(),
                    chiTiet.getMoTa(),
                    chiTiet.getGiaBan(),
                    chiTiet.getSoLuong(),
                    chiTiet.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động !"
                });
            }
        }

    }//GEN-LAST:event_cboLocMSActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        loadDataSPCT();

    }//GEN-LAST:event_btnResetActionPerformed

    private void cboLKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLKDActionPerformed
        // TODO add your handling code here:
        String KD = (String) cboLKD.getSelectedItem();
        if (KD.equalsIgnoreCase("All")) {
            loadDataSPCT();

        } else {
            SanPhamChiTietDAO spct = new SanPhamChiTietDAO();
            dsSPCT = spct.KDgetAll(KD);
            model = (DefaultTableModel) tblChiTietSP.getModel();
            model.setRowCount(0);

            for (SanPhamChiTiet chiTiet : dsSPCT) {
                model.addRow(new Object[]{chiTiet.getIDChiTietSanPham(),
                    chiTiet.getIDsanPham(),
                    chiTiet.getTensp(),
                    chiTiet.getThuonghieu(),
                    chiTiet.getNhacungcap(),
                    chiTiet.getMauSac(),
                    chiTiet.getChatLieu(),
                    chiTiet.getKieuDang(),
                    chiTiet.getPhongcach(),
                    chiTiet.getKichco(),
                    chiTiet.getMoTa(),
                    chiTiet.getGiaBan(),
                    chiTiet.getSoLuong(),
                    chiTiet.getTrangThai() == 0 ? "Hoạt động" : "Không hoạt động !"
                });
            }
        }
    }//GEN-LAST:event_cboLKDActionPerformed

    private void tblSanPhamMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseDragged
        // TODO add your handling code here:
        int rowCount = tblSanPham.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblSanPham.clearSelection();
        }
    }//GEN-LAST:event_tblSanPhamMouseDragged

    private void tblChiTietSPMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseDragged
        // TODO add your handling code here:
        int rowCount = tblChiTietSP.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblChiTietSP.clearSelection();
        }
    }//GEN-LAST:event_tblChiTietSPMouseDragged

    private void tblKichCoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichCoMouseDragged
        // TODO add your handling code here:
        int rowCount = tblKichCo.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblKichCo.clearSelection();
        }
    }//GEN-LAST:event_tblKichCoMouseDragged

    private void tblChatLieuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseDragged
        // TODO add your handling code here:
        int rowCount = tblChatLieu.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblChatLieu.clearSelection();
        }
    }//GEN-LAST:event_tblChatLieuMouseDragged

    private void tblPhongCachMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongCachMouseDragged
        // TODO add your handling code here:
        int rowCount = tblPhongCach.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblPhongCach.clearSelection();
        }
    }//GEN-LAST:event_tblPhongCachMouseDragged

    private void tblKieuDangMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKieuDangMouseDragged
        // TODO add your handling code here:
        int rowCount = tblKieuDang.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblKieuDang.clearSelection();
        }
    }//GEN-LAST:event_tblKieuDangMouseDragged

    private void tblMauSacMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseDragged
        // TODO add your handling code here:
        int rowCount = tblMauSac.getSelectedRowCount();
        if (rowCount >1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 dòng !");
            tblMauSac.clearSelection();
        }
    }//GEN-LAST:event_tblMauSacMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBOLocTH;
    private javax.swing.JLabel MaSP2;
    private javax.swing.JRadioButton SPKHD;
    private javax.swing.JButton btnClearCL;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetTrong;
    private javax.swing.JButton btnSuaKD;
    private javax.swing.JButton btnSuaTrong;
    private javax.swing.JButton btnThemKD;
    private javax.swing.JButton btnThemTrong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JComboBox<String> cboCL;
    private javax.swing.JComboBox<String> cboKC;
    private javax.swing.JComboBox<String> cboKieuDang;
    private javax.swing.JComboBox<String> cboLKD;
    private javax.swing.JComboBox<String> cboLocCL;
    private javax.swing.JComboBox<String> cboLocMS;
    private javax.swing.JComboBox<String> cboMS;
    private javax.swing.JComboBox<String> cboNCC;
    private javax.swing.JComboBox<String> cboPC;
    private javax.swing.JComboBox<String> cboTH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rdoHDCL;
    private javax.swing.JRadioButton rdoHDGC;
    private javax.swing.JRadioButton rdoHDKD;
    private javax.swing.JRadioButton rdoHDMS;
    private javax.swing.JRadioButton rdoHDNCC;
    private javax.swing.JRadioButton rdoHDSPCT;
    private javax.swing.JRadioButton rdoHDT;
    private javax.swing.JRadioButton rdoHDTH;
    private javax.swing.JRadioButton rdoKHDCL;
    private javax.swing.JRadioButton rdoKHDGC;
    private javax.swing.JRadioButton rdoKHDKD;
    private javax.swing.JRadioButton rdoKHDMS;
    private javax.swing.JRadioButton rdoKHDNCC;
    private javax.swing.JRadioButton rdoKHDSPCT;
    private javax.swing.JRadioButton rdoKHDTH;
    private javax.swing.JRadioButton rdoKoHDT;
    private javax.swing.JRadioButton rdoSPHD;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblKichCo;
    private javax.swing.JTable tblKieuDang;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblNhaCungCap;
    private javax.swing.JTable tblPhongCach;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaCL;
    private javax.swing.JTextField txtMaCTSP;
    private javax.swing.JTextField txtMaKD;
    private javax.swing.JTextField txtMaKc;
    private javax.swing.JTextField txtMaMau;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaPC;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaTH;
    private javax.swing.JTextField txtMoTaCTSP;
    private javax.swing.JTextField txtMota;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTePC;
    private javax.swing.JTextField txtTenCl;
    private javax.swing.JTextField txtTenKC;
    private javax.swing.JTextField txtTenKD;
    private javax.swing.JTextField txtTenMau;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
