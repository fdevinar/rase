package com.fabricio.rase.worker;

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
    void workshift_WorkerCannotWorkWhenExhausted() {
        Worker worker = new Worker("W-1");
        while (true) {
            try {
                worker.workShift();
            } catch (IllegalStateException e) {
                break;
            }
        }
        assertThrows(IllegalStateException.class, worker::workShift);
    }
    @Test
    void rest_WorkerCannotRestWhenRested() {
        Worker worker = new Worker("W-2");
        assertThrows(IllegalStateException.class, worker::rest);
    }
    @Test
    void rest_WorkerDecreasesFatigue() {
        Worker worker = new Worker("W-3");
        worker.workShift();
        assertDoesNotThrow(worker::rest);
    }

}
