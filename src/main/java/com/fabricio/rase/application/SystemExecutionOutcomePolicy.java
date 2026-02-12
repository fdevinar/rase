package com.fabricio.rase.application;

public class SystemExecutionOutcomePolicy {

    public enum SystemExecutionOutcome {
        SUCCESS,
        FAILURE
    }

    public SystemExecutionOutcomePolicy.SystemExecutionOutcome evaluate (ExecutionReport report) {
        if (report.totalShifts() == report.successfulShifts()) {
            return SystemExecutionOutcome.SUCCESS;
        } else return SystemExecutionOutcome.FAILURE;
    }

}
