package hr.fer.OR.data;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

public class Bug {

    private final int bugId;

    @NonNull
    private final String name;

    @NonNull
    private final String wikiHandle;

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
        @NonNull List<Subspecies> subspecies
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
        this.subspecies = subspecies;
    }

    public int getBugId() {
        return bugId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getWikiHandle() {
        return wikiHandle;
    }

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
