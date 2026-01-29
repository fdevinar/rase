package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShiftTest {

    @Test
    void assign_throwsWhenWorkerIsNull() {
        Shift shift = new Shift("S-1");
        assertThrows(IllegalArgumentException.class, () -> shift.assign(null));
    }
    @Test
    void assign_assignsWorker() {
        Shift shift = new Shift("S-2");
        Worker worker = new Worker("W-2");
        assertDoesNotThrow(() -> shift.assign(worker));
    }


}
