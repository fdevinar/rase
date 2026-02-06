package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RunnerTest {

    @Test
    void executeSchedule() {
       Worker worker = new Worker("W-1");
       Shift shift = new Shift("SH-1");
       shift.assign(worker);
       Schedule schedule = new Schedule("SC-1",List.of(shift));
       Runner runner = new Runner("R-1", schedule);
       assertDoesNotThrow(runner::executeSchedule);



    }

}
