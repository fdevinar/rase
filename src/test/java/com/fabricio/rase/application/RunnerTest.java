package com.fabricio.rase.application;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;
import com.fabricio.rase.domain.ShiftAlreadyExecutedException;
import com.fabricio.rase.domain.Worker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
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
        ShiftResult successShiftA = new ShiftResult("SH-1", true, null, null);
        ShiftResult successShiftB = new ShiftResult("SH-1B", true, null, null);
        ExecutionReport returnedReport = runner.executeSchedule();
        ExecutionReport expectedReport = new ExecutionReport
                (2, 2,0, List.of(successShiftA,successShiftB));
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
        ShiftResult successfulShift = new ShiftResult("SH-2", true, null, null);
        ShiftResult failedShift = new ShiftResult("SH-2", false, FailureType.SHIFT_ALREADY_EXECUTED, "Shift already executed.");
        ExecutionReport returnedReport = runner.executeSchedule();
        ExecutionReport expectedReport = new ExecutionReport
                (2, 1,1, List.of(successfulShift, failedShift));
        assertEquals(expectedReport, returnedReport);
    }

}
