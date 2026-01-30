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
    @Test
    void assign_throwsWhenSameWorkerInstanceAssignedTwice() {
        Shift shift = new Shift("S-3");
        Worker worker = new Worker("W-3");
        assertDoesNotThrow(() -> shift.assign(worker));
        assertThrows(IllegalArgumentException.class, () -> shift.assign(worker));
    }
    @Test
    void assign_throwsDifferentWorkerInstancesShareSameId() {
        Shift shift = new Shift("S-4");
        Worker worker = new Worker("W-4");
        Worker workerSameId = new Worker("W-4");
        assertDoesNotThrow(() -> shift.assign(worker));
        assertThrows(IllegalArgumentException.class, () -> shift.assign(workerSameId));
    }

}
