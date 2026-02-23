package com.fabricio.rase.application;

public class UserExecutionOutcomePolicy {

    public enum UserExecutionOutcome {
        COMPLETED_SUCCESSFULLY,
        COMPLETED_WITH_FAILURES,
        FAILED_COMPLETELY
    }

    public UserExecutionOutcome evaluate (ExecutionReport report) {
        if (report.isFullySuccessful()) {
            return UserExecutionOutcome.COMPLETED_SUCCESSFULLY;
        }
        else if (report.isCompletelyFailed()) {
            return UserExecutionOutcome.FAILED_COMPLETELY;
        }
        else {
            return UserExecutionOutcome.COMPLETED_WITH_FAILURES;
        }
    }

}
