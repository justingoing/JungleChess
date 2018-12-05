package edu.colostate.cs.cs414.method_men.jungle.server;

import org.jdbi.v3.core.Jdbi;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SqlUtilsTest {
    private Jdbi jdbi;
    private SqlQueries SQL;


    @BeforeEach
    void init2() {
        jdbi = SqlUtils.getJdbi();
        SQL = jdbi.onDemand(SqlQueries.class);
        if (SQL.searchUser("deletedUser") == null) SQL.addUser("deletedUser", "deletedUser");
        if (SQL.searchUser("connor") == null) SQL.addUser("connor", "carey");
        if (SQL.searchUser("jane") == null) SQL.addUser("jane", "doe");
        if (SQL.searchUser("john") == null) SQL.addUser("john", "doe");
    }

    @Test
    void testHandle() {
        jdbi.withHandle(h -> {
                    List<String> name = h.createQuery("select Username from user").mapTo(String.class).list();
                    System.out.println(name);
                    return name;
                }
        );
    }

    @Test
    void testAddUser() {
        String user = SQL.searchUser("jake");
        if(user==null) {
            boolean added = SQL.addUser("jake", "doe");
            assertTrue(added);
            SQL.deleteUser("jake");
        }
    }

    @Test
    void testSearchUser() {
        String user = SQL.searchUser("jane");
        if(user!=null) System.out.println(user);
        assertEquals("jane", user);
    }

    @Test
    void testMatchPassword() {
        String user = SQL.searchUserPassword("jane", "doe");
        if(user!=null) System.out.println(user);
        assertEquals("jane", user);
    }

    @Test
    void testDeleteUser() {
        boolean deleted = SQL.deleteUser("jane");
        System.out.println(deleted);
        assertTrue(deleted);
    }


    @Test
    void testAddMatchState() {
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        System.out.println(user);
        assertTrue(user!=0);
        SQL.deleteMatchState(user);
    }


    @Test
    void testSearchStateMatchState() {
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        System.out.println(user);
        String state = SQL.searchStateMatchState(user);
        if (state!=null) System.out.println(state);
        assertEquals("123", state);
        SQL.deleteMatchState(user);
    }

    @Test
    void testUpdateMatchState() {
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        boolean out = SQL.updateMatchState("567", user);
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchState(user);
    }

    @Test
    void testSearchStartDateStateMatchState() {
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        String state = SQL.searchStartDateMatchState(user);
        if (state!=null) System.out.println(state);
        assertEquals("120420182100", state);
        SQL.deleteMatchState(user);
    }

    @Test
    void testDeleteMatchState() {
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        boolean out = SQL.deleteMatchState(user);
        System.out.println(out);
        assertTrue(out);
    }

    @Test
    void testAddMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        System.out.println(user);
        assertTrue(user!=0);
        SQL.deleteMatchRecord(user);
    }

    @Test
    void testSearchIDMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        System.out.println(user);
        assertTrue(user!=0);
        SQL.deleteMatchRecord(user);
    }

    @Test
    void testSearchWinnerMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        Long user2 = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        List<Long> out = SQL.searchWinnerMatchRecord("connor");
        System.out.println(out);
        assertFalse(out.isEmpty());
        SQL.deleteMatchRecord(user);
        SQL.deleteMatchRecord(user2);
    }

    @Test
    void testSearchLoserMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        Long user2 = SQL.addMatchRecord("jane", "connor", "120420182000", "120420182100");
        List<Long> out = SQL.searchLoserMatchRecord("connor");
        System.out.println(out);
        assertFalse(out.isEmpty());
        SQL.deleteMatchRecord(user);
        SQL.deleteMatchRecord(user2);
    }

    @Test
    void testDeleteMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        boolean out = SQL.deleteMatchRecord(user);
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchRecord(user);
    }

    @Test
    void testAddMatchInvite() {
        boolean user = SQL.addMatchInvite("connor", "jane");
        System.out.println(user);
        assertTrue(user);
        SQL.deleteMatchInvite("connor");
    }

    @Test
    void testSearchMatchInviter() {
        SQL.addMatchInvite("connor", "jane");
        SQL.addMatchInvite("connor", "john");
        List<String> user = SQL.searchMatchInviter("connor");
        System.out.println(user);
        assertFalse(user.isEmpty());
        SQL.deleteMatchInvite("connor");
    }

    @Test
    void testSearchMatchInvitee() {
        SQL.addMatchInvite("john", "jane");
        SQL.addMatchInvite("connor", "jane");
        SQL.addMatchInvite("connor", "john");
        List<String> user = SQL.searchMatchInvitee("jane");
        System.out.println(user);
        assertFalse(user.isEmpty());
        SQL.deleteMatchInvite("connor");
        SQL.deleteMatchInvite("john");
    }

    @Test
    void testDeleteMatchInvite() {
        SQL.addMatchInvite("connor", "jane");
        boolean out = SQL.deleteMatchInvite("connor");
        System.out.println(out);
        assertTrue(out);
    }

    @Test
    void testDeleteWinnerMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        boolean out = SQL.deleteWinnerMatchRecord("connor");
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchRecord(user);
    }

    @Test
    void testDeleteLoserMatchRecord() {
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        boolean out = SQL.deleteLoserMatchRecord("jane");
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchRecord(user);
    }

    @Test
    void testSearchRowIDMatchState(){
        Long user = SQL.addMatchState("connor", "jane", "120420182000", "120420182100");
        System.out.println(user);
        DBRecord row = SQL.searchRowIDMatchState(user);
        assertTrue(row!=null);
        System.out.println(row);
        SQL.deleteMatchState(user);
    }

    @Test
    void testSearchRowIDMatchRecord(){
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        System.out.println(user);
        DBRecord row = SQL.searchRowIDMatchRecord(user);
        assertTrue(row!=null);
        System.out.println(row);
        SQL.deleteMatchRecord(user);
    }

    @Test
    void testSearchRowUserMatchState(){
        Long user = SQL.addMatchState("connor", "jane", "120420182000", "120420182100");
        Long user2 = SQL.addMatchState("jane", "john", "120420182000", "120420182100");
        System.out.println(user);
        List<DBRecord> row = SQL.searchRowUserMatchState("jane");
        assertTrue(row.size()>=2);
        System.out.println(row.get(0));
        System.out.println(row.get(1));
        SQL.deleteMatchState(user);
        SQL.deleteMatchState(user2);
    }

    @Test
    void testSearchRowUserMatchRecord(){
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        Long user2 = SQL.addMatchRecord("jane", "connor", "120420182000", "120420182100");
        List<DBRecord> row = SQL.searchRowUserMatchRecord("jane");
        assertTrue(row.size()>=2);
        System.out.println(row.get(0));
        System.out.println(row.get(1));
        SQL.deleteMatchRecord(user);
        SQL.deleteMatchRecord(user2);
    }

    @Test
    void testSearchAllUser(){
        List<String> users = SQL.searchAllUsers();
        System.out.println(users);
        assertFalse(users.isEmpty());
    }

}
