package hr.fer.OR.database;

import hr.fer.OR.data.AddBugRequestBody;
import hr.fer.OR.data.Bug;
import hr.fer.OR.data.Subspecies;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface DatabaseManager {

    @Nullable
    String addNewBug(@NonNull AddBugRequestBody addBugRequestBody);

    @Nullable
    String changeBugLifespan(int bugId, @NonNull String newLifespan);

    @Nullable
    String removeBug(int bugId);

    @Nullable
    List<Subspecies> getSubspecies (int bugId);

    @Nullable
    List<Bug> getAllBugs();

    @Nullable
    List<Bug> getBugById(int bugId);

    @Nullable
    List<Bug> getBugByFamily(@NonNull String family);

    @Nullable
    List<Bug> getBugByVenomous();

    @Nullable
    List<Bug> getBugByActiveAtNight();

}
