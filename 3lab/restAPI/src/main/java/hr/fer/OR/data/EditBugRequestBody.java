package hr.fer.OR.data;

import org.springframework.lang.NonNull;

public class EditBugRequestBody {

    @NonNull
    private final String name;

    @NonNull
    private final String wikiHandle;

    public EditBugRequestBody(@NonNull String name, @NonNull String wikiHandle) {
        this.name = name;
        this.wikiHandle = wikiHandle;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getWikiHandle() {
        return wikiHandle;
    }
}
