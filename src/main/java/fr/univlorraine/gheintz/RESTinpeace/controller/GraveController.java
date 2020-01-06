package fr.univlorraine.gheintz.RESTinpeace.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GraveController {

    @Autowired
    private GraveRepository graveRepository;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/grave");
    }

    /*---Add new grave---*/
    @PostMapping("/grave")
    public ResponseEntity<?> save(@RequestBody Grave grave) {

        graveRepository.save(grave);
        return ResponseEntity.ok().body("New Grave has been saved with ID:" + grave.getId());
    }

    /*---get all graves---*/
    @GetMapping("/grave")
    public ResponseEntity<Iterable<Grave>> list(@RequestParam(required = false) String search) {
        if (search == null || search.equals("") || search.equals("\"\"")) {
            return ResponseEntity.ok().body(graveRepository.findAll());
        }
        return ResponseEntity.ok().body(graveRepository.search(search));
    }

    /*---Get a grave by id---*/
    @GetMapping("/grave/{id}")
    public ResponseEntity<Grave> get(@PathVariable("id") long id) {
        Optional<Grave> grave = graveRepository.findById(id);
        if (grave.isPresent()) {
            return ResponseEntity.ok().body(grave.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*---Update a grave by id---*/
    @PutMapping("/grave/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Grave updatedGrave) {

        if (!graveRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        updatedGrave.setId(id);
        graveRepository.save(updatedGrave);

        return ResponseEntity.ok().body("Grave has been updated successfully.");
    }

    /*---Delete a grave by id---*/
    @DeleteMapping("/grave/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        graveRepository.deleteById(id);
        return ResponseEntity.ok().body("Grave has been deleted successfully.");
    }

    @GetMapping(value = "/grave/export/xlsx")
    public ResponseEntity<InputStreamResource> exportXlsx(@RequestParam(defaultValue = "full") String type) throws IOException {

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

    @GetMapping("/grave/export/csv")
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