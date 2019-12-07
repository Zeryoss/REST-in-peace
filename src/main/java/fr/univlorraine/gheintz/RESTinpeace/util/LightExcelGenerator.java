package fr.univlorraine.gheintz.RESTinpeace.util;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.apache.poi.ss.usermodel.*;

public class LightExcelGenerator extends ExcelGenerator {

    protected String[] getColumns() {
        return new String[]{"First name", "Last name", "Date of birth", "Date of death"};
    }

    protected void fillRow(Row row, Grave grave) {
        row.createCell(0).setCellValue(grave.getFirstName());
        row.createCell(1).setCellValue(grave.getLastName());

        Cell dobCell = row.createCell(2);
        dobCell.setCellValue(grave.getDateOfBirth());
        dobCell.setCellStyle(dateCellStyle);

        Cell dodCell = row.createCell(3);
        dodCell.setCellValue(grave.getDateOfDeath());
        dodCell.setCellStyle(dateCellStyle);
    }
}
