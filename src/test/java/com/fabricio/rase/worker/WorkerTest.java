package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

    @Test
    void workshift_increasesFatigue() {
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
    void rest_throwsWhenWorkerIsAlreadyRested() {
        Worker worker = new Worker("W-2");
        assertThrows(IllegalStateException.class, worker::rest);
    }
    @Test
    void rest_decreasesFatigue() {
        Worker worker = new Worker("W-3");
        worker.workShift();
        assertDoesNotThrow(worker::rest);
    }

}
