package edu.colostate.cs.cs414.method_men.jungle.server;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SqlUtilsTest {
    Jdbi jdbi;

    @BeforeEach
    void init() {
        jdbi = SqlUtils.getJdbi();

    }

    @Test
    void testHandle() {
        jdbi.withHandle(h -> {
                    List<String> name = h.createQuery("select * from user").mapTo(String.class).list();
                    System.out.println(name);
                    return name;
                }
        );
    }
}
