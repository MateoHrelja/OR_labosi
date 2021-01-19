package hr.fer.OR.data;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

public class BugResponseStatus extends RepresentationModel<Bug> {

    @NonNull
    public String message;

    public BugResponseStatus(String message) { this.message = message; }

    public String getMessage() {
        return this.message;
    }

}
