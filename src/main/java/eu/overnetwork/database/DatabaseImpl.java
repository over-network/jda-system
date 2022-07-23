package eu.overnetwork.database;

import eu.overnetwork.core.Main;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseImpl implements Database {

    private static final Dotenv dotenv = Main.dotenv;

    private static DatabaseImpl databaseImpl;

    private Connection connection;

    private PreparedStatement createUserStatement;

    private DatabaseImpl() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:overnetwork.sqlite" /*+ dotenv.get("DATABASE_URL")/*, dotenv.get("DATABASE_USER"), dotenv.get("DATABASE_PASSWORD")*/);
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS `members` (" +
                    "`guild_id` VARCHAR NOT NULL," +
                    "`member_id` VARCHAR NOT NULL," +
                    "`member_rank` INT DEFAULT 0," +
                    "`member_joined` DATETIME NOT NULL DEFAULT 'CURRENT_TIMESTAMP()'," +
                    "PRIMARY KEY (`guild_id`,`member_id`));");
            createUserStatement = connection.prepareStatement("INSERT INTO members (\"guild_id\", \"member_id\") VALUES (?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getDatabase() {
        if (databaseImpl == null) {
            databaseImpl = new DatabaseImpl();
        }
        return databaseImpl;
    }

    public void createUser(String guildId, String userId) {
        try {
            createUserStatement.clearParameters();
            createUserStatement.setString(1, guildId);
            createUserStatement.setString(2, userId);
            createUserStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String guildId, String userId) {
        try {
            connection.createStatement().execute("DELETE FROM members WHERE guild_id = \"" + guildId + "\" AND user_id = \"" + userId + "\";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRank(String guildId, String userId, int rank) {

    }

    public int getRank(String guildId, String userId) {
        return 0;
    }

    public LocalDateTime getJoinTime(String guildId, String userId) {
        return null;
    }

}
