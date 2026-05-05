package com.fabricio.rase.application;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.*;

public class UserExecutionOutcomePolicyTest {

    @Test
    void evaluate_userExecutionOutcomeSuccessful() {
        ExecutionReport executionReport = new ExecutionReport(2,2,0,List.of());
        UserExecutionOutcomePolicy executionOutcome = new UserExecutionOutcomePolicy();
        assertEquals(COMPLETED_SUCCESSFULLY,executionOutcome.evaluate(executionReport));
    }
    @Test
    void evaluate_userExecutionOutcomePartialFailure() {
        ExecutionReport executionReport = new ExecutionReport(2,1,1,List.of());
        UserExecutionOutcomePolicy executionOutcome = new UserExecutionOutcomePolicy();
        assertEquals(COMPLETED_WITH_FAILURES,executionOutcome.evaluate(executionReport));
    }
    @Test
    void evaluate_userExecutionOutcomeTotalFailure() {
        ExecutionReport executionReport = new ExecutionReport(2,0,2,List.of());
        UserExecutionOutcomePolicy executionOutcome = new UserExecutionOutcomePolicy();
        assertEquals(FAILED_COMPLETELY,executionOutcome.evaluate(executionReport));
    }
    @Test
    void evaluate_emptyReport_returnsCompletedSuccessfully() {
        ExecutionReport executionReport = new ExecutionReport(0,0,0,List.of());
        UserExecutionOutcomePolicy executionOutcome = new UserExecutionOutcomePolicy();
        assertEquals(COMPLETED_SUCCESSFULLY,executionOutcome.evaluate(executionReport));
    }

}
