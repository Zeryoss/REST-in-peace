package fr.univlorraine.gheintz.RESTinpeace.controller;

import fr.univlorraine.gheintz.RESTinpeace.dao.GraveDAO;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GraveController {

    @Autowired
    private GraveDAO graveDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Grave> all = graveDAO.findAll();

        StringBuilder sb = new StringBuilder();

        sb.append("<b>Welcome to \"REST in peace\", the first cemetery management REST API</b><br><br>");

        all.forEach(p -> sb.append(p + "<br>"));

        return sb.toString();
    }

    /*---Add new grave---*/
    @PostMapping("/grave")
    public ResponseEntity<?> save(@RequestBody Grave grave) {

        graveDAO.save(grave);
        return ResponseEntity.ok().body("New Grave has been saved with ID:" + grave.getId());
    }

    /*---Get a grave by id---*/
    @GetMapping("/grave/{id}")
    public ResponseEntity<Grave> get(@PathVariable("id") long id) {
        Optional<Grave> grave = graveDAO.findById(id);
        if (grave.isPresent()) {
            return ResponseEntity.ok().body(grave.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*---get all graves---*/
    @GetMapping("/grave")
    public ResponseEntity<Iterable<Grave>> list() {
        return ResponseEntity.ok().body(graveDAO.findAll());
    }

    /*---Update a grave by id---*/
    @PutMapping("/grave/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Grave updatedGrave) {

        if (!graveDAO.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        updatedGrave.setId(id);
        graveDAO.save(updatedGrave);

        return ResponseEntity.ok().body("Grave has been updated successfully.");
    }

    /*---Delete a grave by id---*/
    @DeleteMapping("/grave/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        graveDAO.deleteById(id);
        return ResponseEntity.ok().body("Grave has been deleted successfully.");
    }

}