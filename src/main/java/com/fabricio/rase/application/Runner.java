package com.fabricio.rase.application;
import com.fabricio.rase.domain.DomainException;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private final Schedule plannedSchedule;

    public Runner(Schedule plannedSchedule) {
        this.plannedSchedule = plannedSchedule;
    }

    public ExecutionReport executeSchedule() {
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
                ShiftResult result = new ShiftResult(currentShift.getId(), false, e.getClass().getSimpleName(), e.getMessage());
                results.add(result);
            }
        }
        return new ExecutionReport(totalShifts,successfulShifts,failedShifts,results);
    }

}
