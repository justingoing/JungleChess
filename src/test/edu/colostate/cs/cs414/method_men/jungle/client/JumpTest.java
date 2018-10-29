package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpTest {

    @Test
    void constructor() {
        Jump test = new Jump();
        assertTrue(test.getAttribute() == '.');
    }
}
