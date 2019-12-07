package fr.univlorraine.gheintz.RESTinpeace.util;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class FullExcelGenerator extends ExcelGenerator {

    @Override
    protected String[] getColumns() {
        return new String[]{"Id", "First name", "Last name", "Birth name", "Date of birth", "Date of death", "Longitude", "Latitude", "Epitaph"};
    }

    @Override
    protected void fillRow(Row row, Grave grave) {
        row.createCell(0).setCellValue(grave.getId());
        row.createCell(1).setCellValue(grave.getFirstName());
        row.createCell(2).setCellValue(grave.getLastName());
        row.createCell(3).setCellValue(grave.getBirthName());

        Cell dobCell = row.createCell(4);
        dobCell.setCellValue(grave.getDateOfBirth());
        dobCell.setCellStyle(dateCellStyle);

        Cell dodCell = row.createCell(5);
        dodCell.setCellValue(grave.getDateOfDeath());
        dodCell.setCellStyle(dateCellStyle);

        row.createCell(6).setCellValue(grave.getLongitude());
        row.createCell(7).setCellValue(grave.getLatitude());

        Cell epitaphCell = row.createCell(8);
        epitaphCell.setCellValue(grave.getEpitaph());
        epitaphCell.setCellStyle(italicCellStyle);
    }
}
