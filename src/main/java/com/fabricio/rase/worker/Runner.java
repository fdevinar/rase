package com.fabricio.rase.worker;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private final Schedule plannedSchedule;

    public Runner(Schedule plannedSchedule) {
        this.plannedSchedule = plannedSchedule;
    }

    public List<String> executeSchedule() {
        List<String> testReport = new ArrayList<>();
        for (Shift currentShift : plannedSchedule.scheduledShifts()) {
            try {
                currentShift.execute();
                testReport.add("Shift " + currentShift.getId() + " executed successfully.");
            // TODO: REFACTOR TO COVER MORE TESTING
            } catch (IllegalStateException e) {
                testReport.add("Shift " + currentShift.getId() + " failed.");
            }
        }
        return testReport;

    }



}
