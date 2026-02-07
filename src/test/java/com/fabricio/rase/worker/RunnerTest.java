package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RunnerTest {

    @Test
    void executeSchedule_returnsExpectedReport() {
        Worker worker = new Worker("W-1");
        Worker workerB = new Worker("W-1B");
        Worker workerC = new Worker("W-1C");
        Worker workerD = new Worker("W-1D");
        Shift shift = new Shift("SH-1");
        Shift shiftB = new Shift("SH-1B");
        shift.assign(worker);
        shift.assign(workerB);
        shiftB.assign(workerC);
        shiftB.assign(workerD);
        Schedule schedule = new Schedule("SC-1", List.of(shift, shiftB));
        Runner runner = new Runner("R-1", schedule);
        List<String> returnedReport = runner.executeSchedule();
        List<String> expectedReport = new ArrayList<>
                (List.of("Shift SH-1 executed successfully.", "Shift SH-1B executed successfully."));
        assertEquals(expectedReport, returnedReport);
    }

}
