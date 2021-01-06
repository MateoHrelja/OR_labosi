package hr.fer.OR.restapi;

import hr.fer.OR.data.AddBugRequestBody;
import hr.fer.OR.data.Bug;
import hr.fer.OR.database.DatabaseManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
public class Controller {

    public DatabaseManager databaseManager;

    public Controller(DatabaseManager databaseManager) {this.databaseManager = databaseManager;}

    @GetMapping("/api/bugs/")
    public ResponseEntity<List<Bug>> getBugs() {
        var objects = databaseManager.getAllBugs();
        return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
    }

    @GetMapping("/api/bugs/{bugId}")
    public ResponseEntity<List<Bug>> getBugId(@PathVariable int bugId) {
        var objects = databaseManager.getBugById(bugId);
        return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
    }

    @GetMapping("/api/bugs")
    public ResponseEntity<List<Bug>> getBugFamily(
        @RequestParam("family") String family
    ) {
        var objects = databaseManager.getBugByFamily(family);
        return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
    }


    @GetMapping("/api/bugs/activeatnight")
    public ResponseEntity<List<Bug>> getBugNight() {
        var objects = databaseManager.getBugByActiveAtNight();
        return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
    }

    @GetMapping("/api/bugs/venomous")
    public ResponseEntity<List<Bug>> getBugVenomous() {
        var objects = databaseManager.getBugByVenomous();
        return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
    }

    @DeleteMapping("/api/bugs/delete/{bugId}")
    public ResponseEntity<String> deleteBug(@PathVariable int bugId) {
        String status = databaseManager.removeBug(bugId);
        if (status.equals("Success")) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/bugs/update/{bugId}")
    public ResponseEntity<String> changeLifespan(
        @PathVariable int bugId,
        @RequestParam String lifespan
    ) {
        String status = databaseManager.changeBugLifespan(bugId, lifespan);
        if (status.equals("Success")) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/bugs/add")
    public ResponseEntity<String> addBug(AddBugRequestBody addBugRequestBody) {
        String status = databaseManager.addNewBug(addBugRequestBody);
        if (status.equals("Success")) {
            return new ResponseEntity<>(status, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }

}
