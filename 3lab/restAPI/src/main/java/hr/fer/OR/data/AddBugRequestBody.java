package hr.fer.OR.data;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

public class AddBugRequestBody {

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
    private final List<String> subspeciesNames;

    public AddBugRequestBody(
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
            @NonNull List<String> subspeciesNames
    ) {
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
        this.subspeciesNames = subspeciesNames;
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
    public List<String> getSubspeciesNames() {
        return subspeciesNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddBugRequestBody)) return false;
        AddBugRequestBody that = (AddBugRequestBody) o;
        return getName().equals(that.getName()) &&
                getWikiHandle().equals(that.getWikiHandle()) &&
                getKingdom().equals(that.getKingdom()) &&
                getFamily().equals(that.getFamily()) &&
                getVenomous().equals(that.getVenomous()) &&
                getUsefulness().equals(that.getUsefulness()) &&
                getSize().equals(that.getSize()) &&
                getParasite().equals(that.getParasite()) &&
                getActiveAtNight().equals(that.getActiveAtNight()) &&
                getLifespan().equals(that.getLifespan()) &&
                getSubspeciesNames().equals(that.getSubspeciesNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getWikiHandle(), getKingdom(), getFamily(), getVenomous(), getUsefulness(), getSize(), getParasite(), getActiveAtNight(), getLifespan(), getSubspeciesNames());
    }
}
