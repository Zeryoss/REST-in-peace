package fr.univlorraine.gheintz.RESTinpeace.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelGenerator {

    protected Workbook workbook;
    protected CreationHelper createHelper;
    protected CellStyle dateCellStyle; // CellStyle for dates
    protected CellStyle italicCellStyle; // CellStyle for epitaph
    protected String[] columns;

    public ExcelGenerator() {
        workbook = new XSSFWorkbook();
        createHelper = workbook.getCreationHelper();

        // CellStyle for dates
        dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.MM.yyy"));

        // CellStyle for epitaph
        italicCellStyle = workbook.createCellStyle();
        Font italicFont = workbook.createFont();
        italicFont.setItalic(true);
        italicCellStyle.setFont(italicFont);

        // Get header columns
        columns = getColumns();
    }

    public ByteArrayInputStream generate(List<Grave> graves) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Graves");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Row for Header
        Row headerRow = sheet.createRow(0);

        // Header
        for (int col = 0; col < columns.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columns[col]);
            cell.setCellStyle(headerCellStyle);
        }

        // Body
        int rowIndex = 1;
        for (Grave grave : graves) {
            Row row = sheet.createRow(rowIndex++);
            fillRow(row, grave);
        }

        // Adjust columns widths
        for (int col = 0; col < columns.length; col++) {
            sheet.autoSizeColumn(col);
        }

        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());

    }

    protected abstract String[] getColumns();

    protected abstract void fillRow(Row row, Grave grave);
}