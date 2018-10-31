package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpTest {
    Jump testJump;

    @BeforeEach
    void init() {
        testJump = new Jump();
    }

    @Test
    void constructor() {
        assertEquals('.', testJump.getAttribute());
    }
}
