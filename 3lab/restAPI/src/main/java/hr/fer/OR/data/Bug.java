package hr.fer.OR.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.escalon.hypermedia.hydra.mapping.ContextProvider;
import de.escalon.hypermedia.hydra.mapping.Expose;
import de.escalon.hypermedia.hydra.mapping.Vocab;
import ioinformarics.oss.jackson.module.jsonld.annotation.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

@Vocab("http://example.com/vocab/")
@Expose("person")
public class Bug extends RepresentationModel<Bug> {

    @JsonldId
    private final int bugId;

    @NonNull
    @JsonldProperty("s:name")
    private final String name;

    @NonNull
    private final String wikiHandle;

    @NonNull
    private final String slika;

    @NonNull
    private final String kingdom;

    @NonNull
    private final String family;

    @NonNull
    private final String venomous;

    @NonNull
    private final String usefulness;

    @NonNull
    private final String size;

    @NonNull
    private final String parasite;

    @NonNull
    private final String activeAtNight;

    @NonNull
    private final String lifespan;

    @NonNull
    private final List<Subspecies> subspecies;

    public Bug(
        int bugId,
        @NonNull String name,
        @NonNull String wikiHandle,
        @NonNull String kingdom,
        @NonNull String family,
        @NonNull String venomous,
        @NonNull String usefulness,
        @NonNull String size,
        @NonNull String parasite,
        @NonNull String activeAtNight,
        @NonNull String lifespan,
        @NonNull List<Subspecies> subspecies,
        @NonNull String slika
    ) {
        this.bugId = bugId;
        this.name = name;
        this.wikiHandle = wikiHandle;
        this.kingdom = kingdom;
        this.family = family;
        this.venomous = venomous;
        this.usefulness = usefulness;
        this.size = size;
        this.parasite = parasite;
        this.activeAtNight = activeAtNight;
        this.lifespan = lifespan;
        this.slika = slika;
        this.subspecies = subspecies;
    }

    @JsonProperty("@id")
    public int getBugId() {
        return bugId;
    }

    @Expose("fullName")
    public String getName() {
        return name;
    }

    @NonNull
    public String getWikiHandle() {
        return wikiHandle;
    }

    public String getSlika() { return slika; }

    @NonNull
    public String getKingdom() {
        return kingdom;
    }

    @NonNull
    public String getFamily() {
        return family;
    }

    @NonNull
    public String getVenomous() {
        return venomous;
    }

    @NonNull
    public String getUsefulness() {
        return usefulness;
    }

    @NonNull
    public String getSize() {
        return size;
    }

    @NonNull
    public String getParasite() {
        return parasite;
    }

    @NonNull
    public String getActiveAtNight() {
        return activeAtNight;
    }

    @NonNull
    public String getLifespan() {
        return lifespan;
    }

    @NonNull
    public List<Subspecies> getSubspecies() {
        return subspecies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bug)) return false;
        Bug bug = (Bug) o;
        return getBugId() == bug.getBugId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBugId());
    }
}
