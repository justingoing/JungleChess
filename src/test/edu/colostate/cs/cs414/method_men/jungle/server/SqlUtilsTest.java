package edu.colostate.cs.cs414.method_men.jungle.server;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SqlUtilsTest {
    private Jdbi jdbi;
    private SqlQueries SQL;

    @BeforeEach
    void init() {
        jdbi = SqlUtils.getJdbi();
        SQL = jdbi.onDemand(SqlQueries.class);
        SQL.deleteUser("jane");
        SQL.deleteUser("connor");
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
        String user = SQL.searchUser("jane");
        if(user!=null) System.out.println(user);
        boolean added = SQL.addUser("jane", "doe");
        System.out.println(added);
        assertTrue(added);
        SQL.deleteUser("jane");
    }

    @Test
    void testSearchUser() {
        SQL.deleteUser("jane");
        SQL.addUser("jane", "doe");
        String user = SQL.searchUser("jane");
        if(user!=null) System.out.println(user);
        assertEquals("jane", user);
        SQL.deleteUser("jane");
    }

    @Test
    void testMatchPassword() {
        SQL.addUser("jane", "doe");
        String user = SQL.searchUserPassword("jane", "doe");
        if(user!=null) System.out.println(user);
        assertEquals("jane", user);
        SQL.deleteUser("jane");
    }

    @Test
    void testDeleteUser() {
        SQL.addUser("jane", "doe");
        String user = SQL.searchUser("jane");
        if(user!=null) System.out.println(user);
        boolean deleted = SQL.deleteUser("jane");
        System.out.println(deleted);
        assertTrue(deleted);
    }


    @Test
    void testAddMatchState() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        System.out.println(user);
        assertTrue(user!=0);
        SQL.deleteMatchState(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }


    @Test
    void testSearchStateMatchState() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        System.out.println(user);
        String state = SQL.searchStateMatchState(user);
        if (state!=null) System.out.println(state);
        assertEquals("123", state);
        SQL.deleteMatchState(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testUpdateMatchState() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        boolean out = SQL.updateMatchState("567", user);
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchState(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testSearchStartDateStateMatchState() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        String state = SQL.searchStartDateMatchState(user);
        if (state!=null) System.out.println(state);
        assertEquals("120420182100", state);
        SQL.deleteMatchState(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testDeleteMatchState() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchState("connor", "jane", "123", "120420182100");
        boolean out = SQL.deleteMatchState(user);
        System.out.println(out);
        assertTrue(out);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testAddMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        System.out.println(user);
        assertTrue(user!=0);
        SQL.deleteMatchRecord(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testSearchIDMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        System.out.println(user);
        assertTrue(user!=0);
        SQL.deleteMatchRecord(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testSearchWinnerMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        Long user2 = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        List<Long> out = SQL.searchWinnerMatchRecord("connor");
        System.out.println(out);
        assertFalse(out.isEmpty());
        SQL.deleteMatchRecord(user);
        SQL.deleteMatchRecord(user2);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testSearchLoserMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        Long user2 = SQL.addMatchRecord("jane", "connor", "120420182000", "120420182100");
        List<Long> out = SQL.searchLoserMatchRecord("connor");
        System.out.println(out);
        assertFalse(out.isEmpty());
        SQL.deleteMatchRecord(user);
        SQL.deleteMatchRecord(user2);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testDeleteMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        boolean out = SQL.deleteMatchRecord(user);
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchRecord(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testAddMatchInvite() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        boolean user = SQL.addMatchInvite("connor", "jane");
        System.out.println(user);
        assertTrue(user);
        SQL.deleteMatchInvite("connor");
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testSearchMatchInviter() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        SQL.addUser("john", "doe");
        SQL.addMatchInvite("connor", "jane");
        SQL.addMatchInvite("connor", "john");
        List<String> user = SQL.searchMatchInviter("connor");
        System.out.println(user);
        assertFalse(user.isEmpty());
        SQL.deleteMatchInvite("connor");
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
        SQL.deleteUser("john");
    }

    @Test
    void testSearchMatchInvitee() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        SQL.addUser("john", "doe");
        SQL.addMatchInvite("john", "jane");
        SQL.addMatchInvite("connor", "jane");
        SQL.addMatchInvite("connor", "john");
        List<String> user = SQL.searchMatchInvitee("jane");
        System.out.println(user);
        assertFalse(user.isEmpty());
        SQL.deleteMatchInvite("connor");
        SQL.deleteMatchInvite("john");
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
        SQL.deleteUser("john");
    }

    @Test
    void testDeleteMatchInvite() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        SQL.addMatchInvite("connor", "jane");
        boolean out = SQL.deleteMatchInvite("connor");
        System.out.println(out);
        assertTrue(out);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testDeleteWinnerMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        boolean out = SQL.deleteWinnerMatchRecord("connor");
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchRecord(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

    @Test
    void testDeleteLoserMatchRecord() {
        SQL.addUser("connor", "password");
        SQL.addUser("jane", "doe");
        Long user = SQL.addMatchRecord("connor", "jane", "120420182000", "120420182100");
        boolean out = SQL.deleteLoserMatchRecord("jane");
        System.out.println(out);
        assertTrue(out);
        SQL.deleteMatchRecord(user);
        SQL.deleteUser("connor");
        SQL.deleteUser("jane");
    }

}
