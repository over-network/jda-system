package eu.overnetwork.database;

import java.time.LocalDateTime;

public interface Database {
    void createUser(String guildId, String userId);
    void deleteUser(String guildId, String userId);
    void setRank(String guildId, String userId, int rank);

    int getRank(String guildId, String userId);
    LocalDateTime getJoinTime(String guildId, String userId);
}
