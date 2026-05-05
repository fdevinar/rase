package com.fabricio.rase.application;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemExecutionOutcomePolicyTest {

    @Test
    void evaluate_systemExecutionOutcomeSuccessful() {
        ExecutionReport executionReport = new ExecutionReport(2,2,0, List.of());
        SystemExecutionOutcomePolicy executionOutcome = new SystemExecutionOutcomePolicy();
        assertEquals(SUCCESS,executionOutcome.evaluate(executionReport));
    }
    @Test
    void evaluate_systemExecutionOutcomeFailure() {
        ExecutionReport executionReport = new ExecutionReport(2,1,1,List.of());
        SystemExecutionOutcomePolicy executionOutcome = new SystemExecutionOutcomePolicy();
        assertEquals(FAILURE,executionOutcome.evaluate(executionReport));
    }

}