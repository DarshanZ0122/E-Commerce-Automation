package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {
    private static Workbook workbook;
    private static Sheet sheet;

    // Load Excel file
    public static void loadExcel(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            workbook = WorkbookFactory.create(file);
            System.out.println("✅ Excel file loaded successfully from: " + filePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath + " due to " + e.getMessage());
        }
    }

    // Get row count
    public static int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found.");
        }
        return sheet.getLastRowNum() + 1;
    }

    // Get column count
    public static int getColumnCount(String sheetName, int rowNum) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found.");
        }
        return sheet.getRow(rowNum).getLastCellNum();
    }

    // Get cell data
    public static String getCellData(String sheetName, int rowNum, int colNum) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found.");
        }
        return sheet.getRow(rowNum).getCell(colNum).toString();
    }
}
