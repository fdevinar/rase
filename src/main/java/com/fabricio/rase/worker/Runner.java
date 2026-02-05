package com.fabricio.rase.worker;

public class Runner {

    private final Schedule plannedSchedule;

    public Runner(String id, Schedule plannedSchedule) {
        this.plannedSchedule = plannedSchedule;
    }
    public String executeSchedule() {
        String testReport = "";
        for (Shift currentShift : plannedSchedule.scheduledShifts()) {

            // TODO: CREATE TEST REPORT
            try {
                currentShift.execute();
                testReport += 'S';
            } catch(IllegalStateException e) {
                testReport += 'F';
            }
        }

        return testReport;

    }



}
