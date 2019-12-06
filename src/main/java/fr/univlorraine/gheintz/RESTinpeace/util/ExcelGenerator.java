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


public class ExcelGenerator implements SpreadsheetGenerator {

    public ByteArrayInputStream generate(List<Grave> graves, ExportType exportType) throws IOException {
        String[] columns = getColumns(exportType);

        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
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

            int rowIndex = 1;
            for (Grave grave : graves) {
                Row row = sheet.createRow(rowIndex++);
                fillRow(row, grave, workbook, exportType);
            }

            // Adjust columns widths
            for (int col = 0; col < columns.length; col++) {
                sheet.autoSizeColumn(col);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private String[] getColumns(ExportType exportType) {
        switch (exportType) {
            case Light:
                return new String[]{"First name", "Last name", "Date of birth", "Date of death"};
            case Full:
            default:
                return new String[]{"Id", "First name", "Last name", "Birth name", "Date of birth", "Date of death", "Longitude", "Latitude", "Epitaph"};
        }
    }

    private void fillRow(Row row, Grave grave, Workbook workbook, ExportType exportType) {
        CreationHelper createHelper = workbook.getCreationHelper();

        // CellStyle for dates
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.MM.yyy"));

        // CellStyle for epitaph
        CellStyle italicCellStyle = workbook.createCellStyle();
        Font italicFont = workbook.createFont();
        italicFont.setItalic(true);
        italicCellStyle.setFont(italicFont);

        Cell dobCell;
        Cell dodCell;

        switch (exportType) {
            case Light:
                row.createCell(0).setCellValue(grave.getFirstName());
                row.createCell(1).setCellValue(grave.getLastName());

                dobCell = row.createCell(2);
                dobCell.setCellValue(grave.getDateOfBirth());
                dobCell.setCellStyle(dateCellStyle);

                dodCell = row.createCell(3);
                dodCell.setCellValue(grave.getDateOfDeath());
                dodCell.setCellStyle(dateCellStyle);

                break;

            case Full:
            default:
                row.createCell(0).setCellValue(grave.getId());
                row.createCell(1).setCellValue(grave.getFirstName());
                row.createCell(2).setCellValue(grave.getLastName());
                row.createCell(3).setCellValue(grave.getBirthName());

                dobCell = row.createCell(4);
                dobCell.setCellValue(grave.getDateOfBirth());
                dobCell.setCellStyle(dateCellStyle);

                dodCell = row.createCell(5);
                dodCell.setCellValue(grave.getDateOfDeath());
                dodCell.setCellStyle(dateCellStyle);

                row.createCell(6).setCellValue(grave.getLongitude());
                row.createCell(7).setCellValue(grave.getLatitude());

                Cell epitaphCell = row.createCell(8);
                epitaphCell.setCellValue(grave.getEpitaph());
                epitaphCell.setCellStyle(italicCellStyle);
        }

    }
}