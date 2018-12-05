package edu.colostate.cs.cs414.method_men.jungle.server;

import org.jdbi.v3.core.mapper.*;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchStateRow {
    private Long ID;
    private String User1;
    private String User2;
    private String State;
    private String Start_Date;

    public MatchStateRow(Long id, String user1, String user2, String state, String start_Date) {
        this.ID = id;
        this.User1 = user1;
        this.User2 = user2;
        this.State = state;
        this.Start_Date = start_Date;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUser1() {
        return User1;
    }

    public void setUser1(String user1) {
        User1 = user1;
    }

    public String getUser2() {
        return User2;
    }

    public void setUser2(String user2) {
        User2 = user2;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }

    public static class MatchStateRowMapper implements RowMapper<MatchStateRow> {

        @Override
        public MatchStateRow map(ResultSet rs, StatementContext ctx) throws SQLException {
            Long ID = rs.getLong("ID");
            String User1 = rs.getString("User1");
            String User2 = rs.getString("User2");
            String State = rs.getString("State");
            String Start_Date = rs.getString("Start_Date");
            return new MatchStateRow(ID, User1, User2, State, Start_Date);
        }

    }
}