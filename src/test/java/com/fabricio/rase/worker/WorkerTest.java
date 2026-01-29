package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

    @Test
    void workshift_increasesFatigue() {
        Worker worker = new Worker("W-1");
        worker.workShift();

        for (int i = 0; i < 9; i++) {
            worker.workShift();
        }

        assertThrows(IllegalStateException.class, worker::workShift);

    }
}
