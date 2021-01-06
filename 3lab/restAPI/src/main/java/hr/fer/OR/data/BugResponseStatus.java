package hr.fer.OR.data;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

public class BugResponseStatus extends RepresentationModel<Bug> {

    @NonNull
    private final String message;

    public BugResponseStatus(String message) {this.message = message;}

    @NonNull
    public String getMessage() {
        return message;
    }
}
