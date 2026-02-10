package com.fabricio.rase.application;
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
        List<String> messages = new ArrayList<>();
        for (Shift currentShift : plannedSchedule.scheduledShifts()) {
            totalShifts += 1;
            try {
                currentShift.execute();
                successfulShifts += 1;
                messages.add("Shift " + currentShift.getId() + " executed successfully.");
            } catch (IllegalStateException e) {
                failedShifts += 1;
                messages.add("Shift " + currentShift.getId() + " failed.");
            }
        }
        return new ExecutionReport(totalShifts,successfulShifts,failedShifts,messages);
    }

}
