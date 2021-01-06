package hr.fer.OR.database;

import hr.fer.OR.data.AddBugRequestBody;
import hr.fer.OR.data.Bug;
import hr.fer.OR.data.Subspecies;
import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SqlResolve")
public class DatabaseManagerImpl implements DatabaseManager {

    @NonNull
    private final Connection databaseConnection;

    public DatabaseManagerImpl(@NonNull Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String addNewBug(@NonNull AddBugRequestBody addBugRequestBody) {
        String query = "BEGIN TRANSACTION;\n"
                + "INSERT INTO kukci_final VALUES((SELECT MAX(bug_id) FROM kukci_final + 1, '"
                + addBugRequestBody.getName() + "', "
                + addBugRequestBody.getWikiHandle() + "', "
                + addBugRequestBody.getFamily() + "', "
                + addBugRequestBody.getKingdom() + "', "
                + addBugRequestBody.getVenomous() + "', "
                + addBugRequestBody.getUsefulness() + "', "
                + addBugRequestBody.getSize() + "', "
                + addBugRequestBody.getParasite() + "', "
                + addBugRequestBody.getActiveAtNight() + "', "
                + addBugRequestBody.getLifespan() + "); ";
        for (String name : addBugRequestBody.getSubspeciesNames()) {
            query += "INSERT INTO podvrste_final VALUES ('"
                    + name + "', "
                    + "(SELECT MAX(bug_id) FROM kukci_final));";
        }
        query += "COMMIT TRANSACTION;";
        try (Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(query);
            return "Success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failure";
    }

    @Override
    public String changeBugLifespan(int bugId, @NonNull String newBugLifespan) {
        String query = "BEGIN TRANSACTION;\n"
                + "UPDATE kukci_final SET lifespan_m = '"
                + newBugLifespan + "'"
                + "WHERE bug_id = " + bugId + ";"
                + "COMMIT TRANSACTION;";
        try (Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(query);
            return "Success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failure";
    }

    @Override
    public String removeBug(int bugId) {
        String query = "BEGIN TRANSACTION;\n" + "DELETE FROM kukci_final WHERE bug_id = "
                + bugId + ";" + "COMMIT TRANSACTION;";

        try (Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(query);
            return "Success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failure";
    }

    @Override
    public List<Subspecies> getSubspecies (int bugId) {
        String query = "SELECT name FROM podvrste_final WHERE bug_id = " + bugId + ";";
        try (
                Statement stmt = databaseConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            var resultSet = stmt.executeQuery(query);
            var subspecies = new ArrayList<Subspecies>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                subspecies.add(new Subspecies(name));
            }
            return subspecies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Bug> getAllBugs() {
        List<Bug> bugs = new ArrayList<>();
        String query = "SELECT * FROM kukci_final;";

        try (
                Statement stmt = databaseConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("bug_id");
                String name = rs.getString("bug_name");
                String wikiHandle = rs.getString("wikihandle");
                String family = rs.getString("family");
                String kingdom = rs.getString("empire");
                String venomous = rs.getString("venomous");
                String usefulness = rs.getString("usefulness");
                String size_mm = rs.getString("size_mm");
                String parasite = rs.getString("parasite");
                String activeAtNight = rs.getString("activeatnight");
                String lifespan = rs.getString("lifespan_m");
                bugs.add(new Bug(id, name, wikiHandle, family, kingdom, venomous, usefulness, size_mm, parasite, activeAtNight, lifespan, getSubspecies(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugById(int bugId) {
        List<Bug> bugs = new ArrayList<>();
        String query = "SELECT * FROM kukci_final WHERE bug_id = " + bugId + ";";

        try (
                Statement stmt = databaseConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //int id = rs.getInt("bug_id");
                String name = rs.getString("bug_name");
                String wikiHandle = rs.getString("wikihandle");
                String family = rs.getString("family");
                String kingdom = rs.getString("empire");
                String venomous = rs.getString("venomous");
                String usefulness = rs.getString("usefulness");
                String size_mm = rs.getString("size_mm");
                String parasite = rs.getString("parasite");
                String activeAtNight = rs.getString("activeatnight");
                String lifespan = rs.getString("lifespan_m");
                bugs.add(new Bug(bugId, name, wikiHandle, family, kingdom, venomous, usefulness, size_mm, parasite, activeAtNight, lifespan, getSubspecies(bugId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugByFamily(@NonNull String family) {
        List<Bug> bugs = new ArrayList<>();
        String query = "SELECT * FROM kukci_final WHERE family = '" + family + "';";

        try (
                Statement stmt = databaseConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("bug_id");
                String name = rs.getString("bug_name");
                String wikiHandle = rs.getString("wikihandle");
                //String family = rs.getString("family");
                String kingdom = rs.getString("empire");
                String venomous = rs.getString("venomous");
                String usefulness = rs.getString("usefulness");
                String size_mm = rs.getString("size_mm");
                String parasite = rs.getString("parasite");
                String activeAtNight = rs.getString("activeatnight");
                String lifespan = rs.getString("lifespan_m");
                bugs.add(new Bug(id, name, wikiHandle, family, kingdom, venomous, usefulness, size_mm, parasite, activeAtNight, lifespan, getSubspecies(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugByVenomous() {
        List<Bug> bugs = new ArrayList<>();
        String query = "SELECT * FROM kukci_final WHERE venomous = 'Da';";

        try (
                Statement stmt = databaseConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("bug_id");
                String name = rs.getString("bug_name");
                String wikiHandle = rs.getString("wikihandle");
                String family = rs.getString("family");
                String kingdom = rs.getString("empire");
                String venomous = rs.getString("venomous");
                String usefulness = rs.getString("usefulness");
                String size_mm = rs.getString("size_mm");
                String parasite = rs.getString("parasite");
                String activeAtNight = rs.getString("activeatnight");
                String lifespan = rs.getString("lifespan_m");
                bugs.add(new Bug(id, name, wikiHandle, family, kingdom, venomous, usefulness, size_mm, parasite, activeAtNight, lifespan, getSubspecies(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugByActiveAtNight() {
        List<Bug> bugs = new ArrayList<>();
        String query = "SELECT * FROM kukci_final WHERE activeatnight = 'Da';";

        try (
                Statement stmt = databaseConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("bug_id");
                String name = rs.getString("bug_name");
                String wikiHandle = rs.getString("wikihandle");
                String family = rs.getString("family");
                String kingdom = rs.getString("empire");
                String venomous = rs.getString("venomous");
                String usefulness = rs.getString("usefulness");
                String size_mm = rs.getString("size_mm");
                String parasite = rs.getString("parasite");
                String activeAtNight = rs.getString("activeatnight");
                String lifespan = rs.getString("lifespan_m");
                bugs.add(new Bug(id, name, wikiHandle, family, kingdom, venomous, usefulness, size_mm, parasite, activeAtNight, lifespan, getSubspecies(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

}


