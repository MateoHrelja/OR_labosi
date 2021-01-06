package hr.fer.OR;

import hr.fer.OR.database.DatabaseManager;
import hr.fer.OR.database.DatabaseManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    @Bean
    public DatabaseManager provideDatabaseManager() {
        return new DatabaseManagerImpl(provideDatabaseConnection());
    }

    @Bean
    public Connection provideDatabaseConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/kukci",
                    "postgres",
                    "bazepodataka"
            );
            conn.setAutoCommit(false);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
