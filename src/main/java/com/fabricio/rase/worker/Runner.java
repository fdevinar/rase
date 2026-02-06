package com.fabricio.rase.worker;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private final Schedule plannedSchedule;

    public Runner(String id, Schedule plannedSchedule) {
        this.plannedSchedule = plannedSchedule;
    }

    // TODO: CREATE EXCEPTION CLASS FOR SHIFT ERROR AND LOG
    public List<String> executeSchedule() {
        List<String> testReport = new ArrayList<>();
        for (Shift currentShift : plannedSchedule.scheduledShifts()) {
            currentShift.execute();
        }
        return testReport;
    }



}
