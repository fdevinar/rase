package com.fabricio.rase.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShiftTest {

    @Test
    void assign_cannotAssignNullWorker() {
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
    void assign_cannotAssignSameWorkerTwice() {
        Shift shift = new Shift("S-3");
        Worker worker = new Worker("W-3");
        assertDoesNotThrow(() -> shift.assign(worker));
        assertThrows(IllegalArgumentException.class, () -> shift.assign(worker));
    }
    @Test
    void assign_cannotAssignWorkersWithSameId() {
        Shift shift = new Shift("S-4");
        Worker worker = new Worker("W-4");
        Worker workerSameId = new Worker("W-4");
        assertDoesNotThrow(() -> shift.assign(worker));
        assertThrows(IllegalArgumentException.class, () -> shift.assign(workerSameId));
    }
    @Test
    void execute_cannotExecuteWhenAssignmentsIsEmpty() {
        Shift shift = new Shift("S-5");
        assertThrows(IllegalArgumentException.class, shift::execute);
    }
    @Test
    void execute_cannotExecuteSameShiftTwice() {
        Shift shift = new Shift("S-6");
        Worker worker = new Worker("W-6");
        assertDoesNotThrow(() -> shift.assign(worker));
        assertDoesNotThrow(shift::execute);
        assertThrows(IllegalStateException.class, shift::execute);
    }
    @Test
    void execute_executesShift() {
        Shift shift = new Shift("S-7");
        Worker worker = new Worker ("W-7");
        Worker workerB = new Worker ("W-7B");
        assertDoesNotThrow(() -> shift.assign(worker));
        assertDoesNotThrow(() -> shift.assign(workerB));
        assertDoesNotThrow(shift::execute);
    }
    @Test
    void execute_cannotExecuteShiftWhenWorkerIsFatigued() {
        Shift shift = new Shift("S-8");
        Worker worker = new Worker("W-8");
        Worker workerB = Worker.of("W-8B",100);
        assertDoesNotThrow(() -> shift.assign(worker));
        assertDoesNotThrow(() -> shift.assign(workerB));
        assertThrows(IllegalStateException.class, shift::execute);
    }
    @Test
    void execute_doesntChangeWorkerStateWhenExecutionFails() {
        Shift shift = new Shift("S-9");
        int maxFatigue = Worker.getMaxFatigue();
        Worker worker = Worker.of("W-9", maxFatigue - 10);
        Worker workerB = Worker.of("W-9B",maxFatigue);
        assertDoesNotThrow(() -> shift.assign(worker));
        assertDoesNotThrow(() -> shift.assign(workerB));
        assertThrows(IllegalStateException.class, shift::execute);
        assertTrue(worker.canWork());
    }

}
