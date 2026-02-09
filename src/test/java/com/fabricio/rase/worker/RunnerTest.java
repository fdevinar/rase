package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RunnerTest {

    @Test
    void executeSchedule_allShiftsExecutable_returnsSuccessfulReport() {
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
        Runner runner = new Runner(schedule);
        List<String> returnedReport = runner.executeSchedule();
        List<String> expectedReport = new ArrayList<>
                (List.of("Shift SH-1 executed successfully.", "Shift SH-1B executed successfully."));
        assertEquals(expectedReport, returnedReport);
    }

    @Test
    void executeSchedule_someShiftsExecutable_returnsPartialFailureReport() {
        Worker worker = new Worker("W-2");
        Worker workerB = new Worker("W-2B");
        Shift shift = new Shift("SH-2");
        shift.assign(worker);
        shift.assign(workerB);
        Schedule schedule = new Schedule("SC-2", List.of(shift, shift));
        Runner runner = new Runner(schedule);
        List<String> returnedReport = runner.executeSchedule();
        List<String> expectedReport = new ArrayList<>
                (List.of("Shift SH-2 executed successfully.", "Shift SH-2 failed."));
        assertEquals(expectedReport, returnedReport);
    }

}
