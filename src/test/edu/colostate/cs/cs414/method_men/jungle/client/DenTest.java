package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DenTest {

    @Test
    void constructor() {
        Den den = new Den();
        Den test = new Den();
        assertTrue(den.getAttribute() == test.getAttribute());
    }
}
