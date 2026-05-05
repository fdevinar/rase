package com.fabricio.rase.application;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.*;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.*;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.*;
import static org.junit.jupiter.api.Assertions.*;

public class PolicyEvaluatorTest {

    @Test
    void assertsPolicyEvaluatorReturnsProperResults() {
        ShiftResult successfulShift = new ShiftResult("SH-1", true, null, null);
        ShiftResult failedShift = new ShiftResult("SH-2", false, FailureType.SHIFT_ALREADY_EXECUTED, "Shift already executed.");
        ExecutionReport report = new ExecutionReport
                (2, 1,1, List.of(successfulShift, failedShift));
        PolicyEvaluator evaluator = new PolicyEvaluator();
        PolicyResults calculatedResults = evaluator.evaluate(report);
        PolicyResults expectedResults = new PolicyResults(FAILURE, COMPLETED_WITH_FAILURES, REVIEW_FAILURES);
        assertEquals(expectedResults,calculatedResults);
    }

}
