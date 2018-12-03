package edu.colostate.cs.cs414.method_men.jungle.server;



import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SqlQueries {

    /**
     * Adds Username and Password to user table.
     * @param input Username: not null String.
     * @param input2 Password: not null String.
     * @return True if added. False otherwise.
     */
    @SqlUpdate("INSERT INTO user(Username,Password) values(:input, :input2);")
    boolean addUser(@Bind("input") String input, @Bind("input2")String input2);

    /**
     * Adds User1, User2, State, and Start_Date to match_state table field ID is auto generated
     * @param input User1: not null String and must be in user table
     * @param input2 User2: not null String and must be in user table
     * @param input3 State: String
     * @param input4 Start_Date String in format DDMMYYYYTTTT
     * @return ID: Long unique to match_state row
     */
    @SqlUpdate("INSERT INTO match_state(User1,User2,State,Start_Date) values(:input,:input2,:input3,:input4);")
    @GetGeneratedKeys("ID")
    Long addMatchState(@Bind("input") String input, @Bind("input2")String input2, @Bind("input3") String input3, @Bind("input4")String input4);

    /**
     * Adds Winner, Loser, Game_Start, and Game_End to match_record table
     * @param input Winner: not null String and must be in user table
     * @param input2 Loser: not null String and must be in user table
     * @param input3 Game_Start String in format DDMMYYYYTTTT
     * @param input4 Game_End String in format DDMMYYYYTTTT
     * @return ID: Long unique to match_record row
     */
    @SqlUpdate("INSERT INTO match_record(Winner,Loser,Game_Start,Game_End) values(:input,:input2,:input3,:input4);")
    @GetGeneratedKeys("ID")
    Long addMatchRecord(@Bind("input") String input, @Bind("input2")String input2, @Bind("input3") String input3, @Bind("input4")String input4);

    /**
     * Adds Inviter and Invitee to match_invite table
     * @param input Inviter: not null String and must be in user table
     * @param input2 Invitee: not null String and must be in user table
     * @return True if added. False otherwise.
     */
    @SqlUpdate("INSERT INTO match_invite(Inviter,Invitee) values(:input,:input2);")
    boolean addMatchInvite(@Bind("input") String input, @Bind("input2")String input2);

    /**
     * Searches for Username in user table
     * @param search Username String
     * @return String with Username if in table or empty List if not in table
     */
    @SqlQuery("SELECT Username FROM user WHERE Username=:search;")
    String searchUser(@Bind("search") String search);

    /**
     * Searches for exact Username and Password in user table
     * @param search Username: String
     * @param search2 Password: String
     * @return String Username if in table or null if not in table
     */
    @SqlQuery("SELECT Username FROM user WHERE Username=:search AND Password=:search2;")
    String searchUserPassword(@Bind("search") String search, @Bind("search2")String search2);

    /**
     * Searches for match_state by matching ID
     * @param search ID: Long unique in match_state
     * @return String State: String representation of the state of the match in match_state table
     */
    @SqlQuery("SELECT State FROM match_state WHERE ID=:search;")
    String searchStateMatchState(@Bind("search") Long search);


    /**
     * Searches for Start_Date in match_state table by matching ID
     * @param search ID: Long unique
     * @return String Start_Date: not null String in format DDMMYYYYTTTT
     */
    @SqlQuery("SELECT Start_Date FROM match_state WHERE ID=:search;")
    String searchStartDateMatchState(@Bind("search") Long search);

    /**
     * Searches for row in match_record table matching Winner
     * @param input Winner: not null String and must be in user table
     * @return List<Long>: IDs from rows from match_record table with matching Winners
     */
    @SqlQuery("SELECT ID from match_record WHERE Winner=:input;")
    List<Long> searchWinnerMatchRecord(@Bind("input") String input);

    /**
     * Searches for row in match_record table matching Winner
     * @param input Loser: not null String and must be in user table
     * @return List<Long>: IDs from rows from match_record table with matching Winners
     */
    @SqlQuery("SELECT ID from match_record WHERE Winner=:input;")
    List<Long> searchLoserMatchRecord(@Bind("input") String input);

    /**
     * Searches for Inviter and Invitee in match_invite table by matching Inviter
     * @param input Inviter: not null String and must be in user table
     * @return List<String>: Invitees from match_invite table
     */
    @SqlQuery("SELECT Invitee from match_invite WHERE Inviter=:input;")
    List<String> searchMatchInviter(@Bind("input") String input);

    /**
     * Searches for Inviter and Invitee in match_invite table by matching Invitee
     * @param input Invitee: not null String and must be in user table
     * @return List<String>: Inviters from match_invite table
     */
    @SqlQuery("SELECT Inviter from match_invite WHERE Invitee=:input;")
    List<String> searchMatchInvitee(@Bind("input") String input);

    /**
     * Updates State in match_state table matching ID
     * @param input State: String representation of the state of the match in match_state table
     * @param input2 ID: Long unique
     * @return True if updated. False otherwise.
     */
    @SqlUpdate("UPDATE match_state SET State=:input WHERE ID=:input2")
    boolean updateMatchState(@Bind("input") String input, @Bind("input2")Long input2);

    /**
     * Deletes Username from user table matching Username
     * @param input Username: String
     * @return True if deleted. False otherwise.
     */
    @SqlUpdate("DELETE FROM user WHERE Username=:input;")
    boolean deleteUser(@Bind("input") String input);

    /**
     * Deletes match_state row from match_state table matching ID
     * @param input ID: Long unique
     * @return True if deleted. False otherwise.
     */
    @SqlUpdate("DELETE FROM match_state WHERE ID=:input")
    boolean deleteMatchState(@Bind("input") Long input);

    /**
     * Deletes match_record row from match_record table matching ID
     * @param input ID: Long unique
     * @return True if deleted. False otherwise.
     */
    @SqlUpdate("DELETE FROM match_record WHERE ID=:input;")
    boolean deleteMatchRecord(@Bind("input") Long input);

    /**
     * Deletes match_invite row from match_invite table matching Inviter
     * @param input Inviter: not null String and must be in user table
     * @return True if deleted. False otherwise.
     */
    @SqlUpdate("DELETE FROM match_invite WHERE Inviter=:input;")
    boolean deleteMatchInvite(@Bind("input") String input);

    /**
     * Changes Username to deletedUser in match_record rows matching Winner
     * @param input Winner: not null String and must be in user table
     * @return True if deleted. False otherwise.
     */
    @SqlUpdate("UPDATE match_record SET Winner='deletedUser' WHERE Winner=:input")
    boolean deleteWinnerMatchRecord(@Bind("input") String input);

    /**
     * Changes Username to deletedUser in match_record rows matching Loser
     * @param input Loser: not null String and must be in user table
     * @return True if deleted. False otherwise.
     */
    @SqlUpdate("UPDATE match_record SET Loser='deletedUser' WHERE Loser=:input")
    boolean deleteLoserMatchRecord(@Bind("input") String input);

}
