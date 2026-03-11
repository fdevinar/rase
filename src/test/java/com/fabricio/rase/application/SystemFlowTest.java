package com.fabricio.rase.application;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;
import com.fabricio.rase.domain.Worker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.*;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.*;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.*;

public class SystemFlowTest {

    @Test
    void runSchedule_allShiftsSuccessful_returnsSuccessfulEvaluation() {
        Worker worker = new Worker("W-1");
        Shift shiftA = new Shift("SH-1");
        Shift shiftB = new Shift("SH-2");
        shiftA.assign(worker);
        shiftB.assign(worker);
        Schedule schedule = new Schedule("SC-1", List.of(shiftA,shiftB));
        ScheduleExecutionEngine engine = new Runner();
        ExecutionReport report = engine.run(schedule);
        PolicyEvaluator evaluator = new PolicyEvaluator();
        PolicyResults calculatedResults = evaluator.evaluate(report);
        PolicyResults expectedResults = new PolicyResults(SUCCESS, COMPLETED_SUCCESSFULLY, NO_ACTION_NEEDED);
        assertEquals(expectedResults,calculatedResults);
    }

    @Test
    void runSchedule_partialShiftsFailure_returnsPartialFailureEvaluation() {
        Worker worker = new Worker("W-1");
        Shift shiftA = new Shift("SH-1");
        shiftA.assign(worker);
        Schedule schedule = new Schedule("SC-1", List.of(shiftA,shiftA));
        ScheduleExecutionEngine engine = new Runner();
        ExecutionReport report = engine.run(schedule);
        PolicyEvaluator evaluator = new PolicyEvaluator();
        PolicyResults calculatedResults = evaluator.evaluate(report);
        PolicyResults expectedResults = new PolicyResults(FAILURE, COMPLETED_WITH_FAILURES, REVIEW_FAILURES);
        assertEquals(expectedResults,calculatedResults);
    }

}



//Schedule
//   ↓
//ScheduleExecutionEngine (Runner)
//   ↓
//ExecutionReport
//   ↓
//PolicyEvaluator
//   ↓
//PolicyResults
//
//The test should verify something like:
//
//A schedule with one success + one failure
//
//Runner.run() produces the correct ExecutionReport
//
//PolicyEvaluator.evaluate() produces the correct PolicyResults
//
//So the test proves:
//
//the engine + reporting + policy interpretation work together.
//
//Not a huge test — just a single happy-path + partial failure scenario is enough.
