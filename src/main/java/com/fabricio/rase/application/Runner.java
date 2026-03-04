package com.fabricio.rase.application;
import com.fabricio.rase.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.fabricio.rase.application.FailureType.*;

public class Runner implements ScheduleExecutionEngine {

//    private final Schedule plannedSchedule;
//    public Runner(Schedule plannedSchedule) {
//        this.plannedSchedule = plannedSchedule;
//    }

    private FailureType map (DomainException ex) {
        if (ex instanceof WorkerTooFatiguedException) return WORKER_TOO_FATIGUED;
        if (ex instanceof WorkerAlreadyAssignedToShiftException) return WORKER_ALREADY_ASSIGNED;
        if (ex instanceof ShiftHasNoAssignmentsException) return SHIFT_HAS_NO_ASSIGNMENTS;
        if (ex instanceof ShiftAlreadyExecutedException) return SHIFT_ALREADY_EXECUTED;
        return UNKNOWN_DOMAIN_ERROR;
    }

    public ExecutionReport run(Schedule plannedSchedule) {
        int totalShifts = 0;
        int successfulShifts = 0;
        int failedShifts = 0;
        List<ShiftResult> results = new ArrayList<>();
        for (Shift currentShift : plannedSchedule.scheduledShifts()) {
            totalShifts += 1;
            try {
                currentShift.execute();
                successfulShifts += 1;
                ShiftResult result = new ShiftResult(currentShift.getId(), true, null, null);
                results.add(result);
            } catch (DomainException e) {
                failedShifts += 1;

                ShiftResult result = new ShiftResult(currentShift.getId(), false, map(e), e.getMessage());
                results.add(result);
            }
        }
        return new ExecutionReport(totalShifts,successfulShifts,failedShifts,results);
    }

}
