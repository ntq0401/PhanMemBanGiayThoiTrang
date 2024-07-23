/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.utils;

import com.shoes.model.NhanVien;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Admin
 */
public class ExportNV {

    public static final int COLUMN_MA = 0;
    public static final int COLUMN_TEN = 1;
    public static final int COLUMN_NGAYSINH = 2;
    public static final int COLUMN_EMAIL = 3;
    public static final int COLUMN_SDT = 4;
    public static final int COLUMN_GIOITINH = 5;
    public static final int COLUMN_DIACHI = 6;
    public static final int COLUMN_MATKHAU = 7;
    public static final int COLUMN_NGAYTAO = 8;
    public static final int COLUMN_NGAYSUA = 9;
    public static final int COLUMN_TRANGTHAI = 10;
    public static final int COLUMN_HINHANH = 11;
    public static final int COLUMN_CV = 12;

    public static void writeExcel(List<NhanVien> list, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Nhân viên");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;

        for (NhanVien x : list) {
            Row row = sheet.createRow(rowIndex);
            writeBook(x, row);
            rowIndex++;
        }

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {

        CellStyle cellStyle = createStyleHeader(sheet);

        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã nhân viên");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên nhân viên");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới tính");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");
        
        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CV ");
        
        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sinh");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tài khoản");

        cell = row.createCell(9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mật khẩu");

        cell = row.createCell(10);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trạng thái");

    }

    private static void writeBook(NhanVien n, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(0);
        cell.setCellValue(n.getIDNhanVien());

        cell = row.createCell(1);
        cell.setCellValue(n.getTenNhanVien());

        cell = row.createCell(2);
        if (n.getGioiTinh() == 0) {
            cell.setCellValue("Nam");
        } else {
            cell.setCellValue("Nữ");
        }
        

        cell = row.createCell(3);
        cell.setCellValue(n.getEmail());

        cell = row.createCell(4);
        cell.setCellValue(n.getChucVu());

        cell = row.createCell(5);
        cell.setCellValue(n.getNgaySinh());

        cell = row.createCell(6);
        cell.setCellValue(n.getDiaChi());

        cell = row.createCell(7);
        cell.setCellValue(n.getDienThoai());

        cell = row.createCell(8);
        cell.setCellValue(n.getTaiKhoan());

        cell = row.createCell(9);
        cell.setCellValue(n.getMatKhau());
        
        cell = row.createCell(10);
        cell.setCellValue(n.getTrangThai());

        

    }

    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Create CellStyle for header
    private static CellStyle createStyleHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
//        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
