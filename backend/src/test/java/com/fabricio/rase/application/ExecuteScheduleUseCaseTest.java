package com.fabricio.rase.application;

import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;
import com.fabricio.rase.domain.Worker;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.SUCCESS;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.COMPLETED_SUCCESSFULLY;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.NO_ACTION_NEEDED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecuteScheduleUseCaseTest {
    @Test
    void executeScheduleUseCase_reportSuccessful() {
        Worker worker = new Worker("W-1");
        Shift shiftA = new Shift("SH-1");
        shiftA.assign(worker);
        Schedule schedule = new Schedule("SC-1", List.of(shiftA));
        ExecuteScheduleUseCase useCase = new ExecuteScheduleUseCase();
        ExecuteScheduleResult calculatedResults = useCase.execute(schedule);
        ExecutionReport report = new ExecutionReport(1,1,0, Collections.singletonList(new ShiftResult("SH-1", true, null, null)));
        PolicyResults policyResults = new PolicyResults(SUCCESS, COMPLETED_SUCCESSFULLY, NO_ACTION_NEEDED);
        WorkerResults workersResults = new WorkerResults("W-1",1,10,false);
        ExecuteScheduleResult expectedResults = new ExecuteScheduleResult(report, policyResults, Collections.singletonList(workersResults));
        assertEquals(expectedResults,calculatedResults);
    }
}