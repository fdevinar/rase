package com.fabricio.rase.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

    @Test
    void cannotCreateWorkerWithNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Worker(null));
    }
    @Test
    void cannotCreateWorkerWithBlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Worker(""));
    }
    @Test
    void performWork_WorkerCannotWorkWhenExhausted() {
        Worker worker = new Worker("W-1");
        while (worker.canWork()) {
            worker.performWork();
        }
        assertThrows(WorkerTooFatiguedException.class, worker::performWork);
    }
    @Test
    void rest_WorkerCannotRestWhenRested() {
        Worker worker = new Worker("W-2");
        assertThrows(IllegalStateException.class, worker::rest);
    }
    @Test
    void rest_WorkerDecreasesFatigue() {
        Worker worker = new Worker("W-3");
        worker.performWork();
        assertDoesNotThrow(worker::rest);
    }
    @Test
    void performWork_WorkerIncreasesNumberOfShiftsWorked() {
        Worker worker = new Worker("W-4");
        worker.performWork();
        assertEquals(1,worker.getTotalShiftsWorked());
    }
    @Test
    void performWork_WorkerDoesntIncreasesNumberOfShiftsWorkedWhenFatigued() {
        Worker worker = Worker.of("W-5",100);
        assertThrows(WorkerTooFatiguedException.class, worker::performWork);
        assertEquals(0,worker.getTotalShiftsWorked());
    }

}
