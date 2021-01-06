package hr.fer.OR.restapi;

import hr.fer.OR.data.AddBugRequestBody;
import hr.fer.OR.data.Bug;
import hr.fer.OR.data.BugResponseStatus;
import hr.fer.OR.database.DatabaseManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;
import java.util.Objects;

@RestController
public class Controller {

    public DatabaseManager databaseManager;

    public Controller(DatabaseManager databaseManager) {this.databaseManager = databaseManager;}

    @GetMapping("/api/bugs/")
    public ResponseEntity<List<Bug>> getBugs() {
        var objects = databaseManager.getAllBugs();

        if (!objects.isEmpty()) {
            for (Bug bug : objects) {
                bug.add(linkTo(methodOn(Controller.class).getBugs()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }
            return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/api/bugs/{bugId}")
    public ResponseEntity<List<Bug>> getBugId(@PathVariable int bugId) {
        var objects = databaseManager.getBugById(bugId);

        if (!objects.isEmpty()) {

            for (Bug bug : objects) {
                bug.add(linkTo(methodOn(Controller.class).getBugId(bugId)).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).deleteBug(bugId)).withRel("rel"));
            }

            return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/bugs")
    public ResponseEntity<List<Bug>> getBugFamily(
        @RequestParam("family") String family
    ) {
        var objects = databaseManager.getBugByFamily(family);

        if (!objects.isEmpty()) {

            for (Bug bug : objects) {
                bug.add(linkTo(methodOn(Controller.class).getBugFamily(family)).withSelfRel());
            }

            return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }


    @GetMapping("/api/bugs/activeatnight")
    public ResponseEntity<List<Bug>> getBugNight() {
        var objects = databaseManager.getBugByActiveAtNight();

        if (!objects.isEmpty()) {

            for (Bug bug : objects) {
                bug.add(linkTo(methodOn(Controller.class).getBugNight()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }

            return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/api/bugs/venomous")
    public ResponseEntity<List<Bug>> getBugVenomous() {
        var objects = databaseManager.getBugByVenomous();

        if (!objects.isEmpty()) {

            for (Bug bug : objects) {
                bug.add(linkTo(methodOn(Controller.class).getBugVenomous()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }

            return new ResponseEntity<>(Objects.requireNonNullElseGet(objects, List::of), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @DeleteMapping("/api/bugs/delete/{bugId}")
    public ResponseEntity<BugResponseStatus> deleteBug(@PathVariable int bugId) {

        BugResponseStatus bugResponseStatus = databaseManager.removeBug(bugId);

        bugResponseStatus.add(linkTo(methodOn(Controller.class).deleteBug(bugId)).withSelfRel());
        bugResponseStatus.add(linkTo(methodOn(Controller.class).getBugs()).withRel("rel"));
        if (bugResponseStatus.getMessage().equals("Success")) {
            return new ResponseEntity<>(bugResponseStatus, HttpStatus.OK);
        }
        return new ResponseEntity<>(bugResponseStatus, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/bugs/update/{bugId}")
    public ResponseEntity<BugResponseStatus> changeLifespan(
        @PathVariable int bugId,
        @RequestParam String lifespan
    ) {

        BugResponseStatus bugResponseStatus = databaseManager.changeBugLifespan(bugId, lifespan);

        bugResponseStatus.add(linkTo(methodOn(Controller.class).changeLifespan(bugId, lifespan)).withSelfRel());
        bugResponseStatus.add(linkTo(methodOn(Controller.class).getBugId(bugId)).withRel("rel"));
        if (bugResponseStatus.getMessage().equals("Success")) {
            return new ResponseEntity<>(bugResponseStatus, HttpStatus.OK);
        }
        return new ResponseEntity<>(bugResponseStatus, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/bugs/add")
    public ResponseEntity<BugResponseStatus> addBug(AddBugRequestBody addBugRequestBody) {

        BugResponseStatus bugResponseStatus = databaseManager.addNewBug(addBugRequestBody);

        bugResponseStatus.add(linkTo(methodOn(Controller.class).addBug(addBugRequestBody)).withSelfRel());
        bugResponseStatus.add(linkTo(methodOn(Controller.class).getBugs()).withRel("rel"));
        if (bugResponseStatus.getMessage().equals("Success")) {
            return new ResponseEntity<>(bugResponseStatus, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(bugResponseStatus, HttpStatus.BAD_REQUEST);
    }

}
