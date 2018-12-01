package edu.colostate.cs.cs414.method_men.jungle.server;



import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SqlQueries {

    @SqlQuery("SELECT Username FROM user WHERE Username=:search;")
    List<String> searchUser(@Bind("search") String search);

    @SqlQuery("SELECT Username FROM user WHERE Username=:search AND Password=:search2;")
    List<String> matchPassword(@Bind("search") String search, @Bind("search2")String search2);

    @SqlUpdate("INSERT INTO user(Username,Password) values(:input, :input2);")
    boolean addUser(@Bind("input") String input, @Bind("input2")String input2);

    @SqlUpdate("DELETE FROM user WHERE Username=:input AND Password=:input2;")
    boolean deleteUser(@Bind("input") String input, @Bind("input2")String input2);

    @SqlQuery("SELECT State FROM match_state WHERE ID=:search;")
    List<String> searchStateMatchState(@Bind("search") Integer search);

    @SqlUpdate("INSERT INTO match_state(User1,User2,State,Start_Date) values(:input,:input2,:input3,:input4);")
    boolean addMatchState(@Bind("input") String input, @Bind("input2")String input2, @Bind("input3") String input3, @Bind("input4")String input4);

    @SqlQuery("SELECT ID FROM match_state WHERE User1=:input AND User2=:input2 AND State=:input3 AND Start_Date=:input4;")
    List<Integer> searchIDMatchState(@Bind("input") String input, @Bind("input2")String input2, @Bind("input3") String input3, @Bind("input4")String input4);

    @SqlUpdate("UPDATE match_state SET State=:input WHERE ID=:input2")
    boolean updateMatchState(@Bind("input") String input, @Bind("input2")Integer input2);

    @SqlQuery("SELECT Start_Date FROM match_state WHERE ID=:search;")
    List<String> searchStartDateMatchState(@Bind("search") Integer search);

    @SqlUpdate("DELETE FROM match_state WHERE ID=:input")
    boolean deleteMatchState(@Bind("input") Integer input);

    @SqlUpdate("INSERT INTO match_record(Winner,Loser,Game_Start,Game_End) values(:input,:input2,:input3,:input4);")
    boolean addMatchRecord(@Bind("input") String input, @Bind("input2")String input2, @Bind("input3") String input3, @Bind("input4")String input4);

    @SqlQuery("SELECT ID from match_record WHERE Winner=:input AND Loser=:input2 AND Game_Start=:input3 AND Game_End=:input4;")
    List<Integer> searchIDMatchRecord(@Bind("input") String input, @Bind("input2")String input2, @Bind("input3") String input3, @Bind("input4")String input4);

    /**
     * Have to write custom serializer for this
     * @param input
     * @return
     */
    @SqlQuery("SELECT * from match_record WHERE Winner=:input;")
    List<String> searchWinnerMatchRecord(@Bind("input") String input);

    @SqlUpdate("DELETE FROM match_record WHERE ID=:input;")
    boolean deleteMatchRecord(@Bind("input") Integer input);

    @SqlUpdate("INSERT INTO match_invite(Inviter,Invitee) values(:input,:input2);")
    boolean addMatchInvite(@Bind("input") String input, @Bind("input2")String input2);

    /**
     * Have to write custom serializer for this
     * @param input
     * @return
     */
    @SqlQuery("SELECT * from match_invite WHERE Inviter=:input;")
    List<String> searchMatchInvite(@Bind("input") String input);

    @SqlUpdate("DELETE FROM match_invite WHERE Inviter=:input;")
    boolean deleteMatchInvite(@Bind("input") String input);

}
