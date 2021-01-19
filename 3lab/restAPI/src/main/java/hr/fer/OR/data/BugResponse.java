package hr.fer.OR.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.escalon.hypermedia.hydra.mapping.Expose;
import de.escalon.hypermedia.hydra.mapping.Vocab;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldNamespace;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldResource;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import jdk.jfr.ContentType;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

import java.util.List;

public class BugResponse extends RepresentationModel<BugResponse> {

    @NonNull
    public String context;

    @NonNull
    public String status;

    @NonNull
    public String message;

    @NonNull
    public List<Bug> bugs;

    @JsonCreator
    public BugResponse(
            @JsonProperty("@context") String context,
            @JsonProperty("message") String message,
            @JsonProperty("status") String status,
            @JsonProperty("response") List<Bug> bugs)
    {
        this.context = context;
        this.message = message;
        this.status = status;
        this.bugs = bugs;
    }

    public String getContext() {
        return this.context;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public List<Bug> getBugs() {
        return this.bugs;
    }

}
