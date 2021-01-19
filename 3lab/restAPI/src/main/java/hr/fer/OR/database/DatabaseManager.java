package hr.fer.OR.database;

import hr.fer.OR.data.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface DatabaseManager {

    @Nullable
    BugResponseStatus addNewBug(@NonNull AddBugRequestBody addBugRequestBody);

    @Nullable
    BugResponseStatus editExistingBug(int bugId, EditBugRequestBody editBugRequestBody);

    @Nullable
    BugResponseStatus removeBug(int bugId);

    @Nullable
    List<Subspecies> getSubspecies (int bugId);

    @Nullable
    BugResponse getAllBugs();

    @Nullable
    BugResponse getBugById(int bugId);

    @Nullable
    BugResponse getBugByFamily(@NonNull String family);

    @Nullable
    BugResponse getBugByVenomous();

    @Nullable
    BugResponse getBugByActiveAtNight();

}
