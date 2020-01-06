package fr.univlorraine.gheintz.RESTinpeace.controller;

import fr.univlorraine.gheintz.RESTinpeace.dao.GraveRepository;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import fr.univlorraine.gheintz.RESTinpeace.model.LightGrave;
import fr.univlorraine.gheintz.RESTinpeace.util.CsvGenerator;
import fr.univlorraine.gheintz.RESTinpeace.util.ExcelGenerator;
import fr.univlorraine.gheintz.RESTinpeace.util.FullExcelGenerator;
import fr.univlorraine.gheintz.RESTinpeace.util.LightExcelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExportController {

    @Autowired
    private GraveRepository graveRepository;

    @GetMapping(value = "/export/xlsx")
    public ResponseEntity<InputStreamResource> exportXLSX(@RequestParam(defaultValue = "full") String type) throws IOException {

        if (!type.equals("full") && !type.equals("light")) {
            throw new IllegalArgumentException("Please provide a valid export type (full or light)");
        }

        List<Grave> graves = (List<Grave>) graveRepository.findAll();

        ExcelGenerator excelGenerator;
        if (type.equals("light")) {
            excelGenerator = new LightExcelGenerator();
        } else {
            excelGenerator = new FullExcelGenerator();
        }
        ByteArrayInputStream in = excelGenerator.generate(graves);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=graves.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/export/csv")
    public void exportCSV(HttpServletResponse response, @RequestParam(defaultValue = "full") String type) throws Exception {

        if (!type.equals("full") && !type.equals("light")) {
            throw new IllegalArgumentException("Please provide a valid export type (full or light)");
        }

        //set file name and content type
        String filename = "graves.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //write all graves to csv file
        List<Grave> graves = (List<Grave>) graveRepository.findAll();

        if (type.equals("full")) {
            CsvGenerator<Grave> generator = new CsvGenerator<>();
            generator.getWriter(response).write(graves);
        } else {
            CsvGenerator<LightGrave> generator = new CsvGenerator<>();
            ArrayList<LightGrave> lightGraves = new ArrayList<LightGrave>();
            for (Grave grave : graves) {
                lightGraves.add(new LightGrave(grave));
            }
            generator.getWriter(response).write(lightGraves);
        }
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}