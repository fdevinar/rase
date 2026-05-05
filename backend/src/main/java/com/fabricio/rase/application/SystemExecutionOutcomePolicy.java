package com.fabricio.rase.application;

public class SystemExecutionOutcomePolicy {

    public enum SystemExecutionOutcome {
        SUCCESS,
        FAILURE
    }

    public SystemExecutionOutcome evaluate (ExecutionReport report) {
        if (report.hasFailures()) {
            return SystemExecutionOutcome.FAILURE;
        } else return SystemExecutionOutcome.SUCCESS;
    }

}
