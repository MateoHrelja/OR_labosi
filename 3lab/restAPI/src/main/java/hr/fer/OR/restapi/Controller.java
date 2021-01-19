package hr.fer.OR.restapi;

import hr.fer.OR.data.*;
import hr.fer.OR.database.DatabaseManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.Objects;

import org.fastily.jwiki.core.*;
import org.fastily.jwiki.dwrap.*;

@RestController
public class Controller {

    public DatabaseManager databaseManager;

    public Controller(DatabaseManager databaseManager) {this.databaseManager = databaseManager;}

    @GetMapping("/api/bugs/")
    public ResponseEntity<BugResponse> getBugs() {
        BugResponse bugResponse = databaseManager.getAllBugs();

        if(bugResponse.getStatus() == "OK") {
            for (Bug bug : bugResponse.getBugs()) {
                bug.add(linkTo(methodOn(Controller.class).getBugs()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }
            return new ResponseEntity<>(bugResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/api/bugs/{bugId}")
    public ResponseEntity<BugResponse> getBugId(@PathVariable int bugId) {
        BugResponse bugResponse = databaseManager.getBugById(bugId);

        if(bugResponse.getStatus() == "OK") {
            for (Bug bug : bugResponse.getBugs()) {
                bug.add(linkTo(methodOn(Controller.class).getBugs()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }
            return new ResponseEntity<>(bugResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/bugs")
    public ResponseEntity<BugResponse> getBugFamily(
        @RequestParam("family") String family
    ) {
        BugResponse bugResponse = databaseManager.getBugByFamily(family);

        if(bugResponse.getStatus() == "OK") {
            for (Bug bug : bugResponse.getBugs()) {
                bug.add(linkTo(methodOn(Controller.class).getBugs()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }
            return new ResponseEntity<>(bugResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }


    @GetMapping("/api/bugs/activeatnight")
    public ResponseEntity<BugResponse> getBugNight() {
        BugResponse bugResponse = databaseManager.getBugByActiveAtNight();

        if(bugResponse.getStatus() == "OK") {
            for (Bug bug : bugResponse.getBugs()) {
                bug.add(linkTo(methodOn(Controller.class).getBugs()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }
            return new ResponseEntity<>(bugResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/api/bugs/venomous")
    public ResponseEntity<BugResponse> getBugVenomous() {

        BugResponse bugResponse = databaseManager.getBugByVenomous();

        if(bugResponse.getStatus() == "OK") {
            for (Bug bug : bugResponse.getBugs()) {
                bug.add(linkTo(methodOn(Controller.class).getBugs()).withSelfRel());
                bug.add(linkTo(methodOn(Controller.class).getBugId(bug.getBugId())).withRel("rel"));
            }
            return new ResponseEntity<>(bugResponse, HttpStatus.OK);
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
    public ResponseEntity<BugResponseStatus> editBug(EditBugRequestBody editBugRequestBody,
                                                     @PathVariable int bugId
    ) {

        BugResponseStatus bugResponseStatus = databaseManager.editExistingBug(bugId, editBugRequestBody);

        bugResponseStatus.add(linkTo(methodOn(Controller.class).editBug(editBugRequestBody, bugId)).withSelfRel());
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

    @GetMapping(value = "/api/bugs/{bugId}/picture")
    public ResponseEntity<String> getPicture(
            @PathVariable int bugId
    ) {
        String wikiHandle = databaseManager.getBugById(bugId).getBugs().get(0).getWikiHandle();
        String thumbnail = "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&piprop=original&titles=" + wikiHandle;

        return new ResponseEntity<>(thumbnail, HttpStatus.OK);
    }

}
