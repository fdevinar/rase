package com.fabricio.rase.application;

public class UserExecutionOutcomePolicy {

    public enum ExecutionOutcome {
        COMPLETED_SUCCESSFULLY,
        COMPLETED_WITH_FAILURES,
        FAILED_COMPLETELY
    }

    public ExecutionOutcome evaluate (ExecutionReport report) {
        if (report.totalShifts() == report.successfulShifts()) {
            return ExecutionOutcome.COMPLETED_SUCCESSFULLY;
        }
        else if (report.totalShifts() == report.failedShifts()) {
            return ExecutionOutcome.FAILED_COMPLETELY;
        }
        else {
            return ExecutionOutcome.COMPLETED_WITH_FAILURES;
        }
    }

}
