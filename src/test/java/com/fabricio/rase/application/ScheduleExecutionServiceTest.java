package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.application.dto.ShiftRequest;
import com.fabricio.rase.domain.WorkerAlreadyAssignedToShiftException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.FAILURE;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.FAILED_COMPLETELY;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.FIX_INPUT_DATA;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.SUCCESS;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.COMPLETED_SUCCESSFULLY;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.NO_ACTION_NEEDED;

public class ScheduleExecutionServiceTest {

    @Test
    void executeScheduleExecutionService_returnsExpectedScheduleResultsWithSuccess() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-1", List.of("W-1","W-2"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-1", Collections.singletonList(shiftRequest));
        SimulationRunRepository fakeRepository = run -> run;
        ObjectMapper objectMapper = new ObjectMapper();
        ScheduleExecutionService executionService = new ScheduleExecutionService(fakeRepository, objectMapper);
        ExecuteScheduleResult calculatedResults = executionService.execute(scheduleRequest);
        ExecutionReport report = new ExecutionReport(1,1,0, Collections.singletonList(new ShiftResult("SH-1", true, null, null)));
        PolicyResults policyResults = new PolicyResults(SUCCESS, COMPLETED_SUCCESSFULLY, NO_ACTION_NEEDED);
        WorkerResults workerResults1 = new WorkerResults("W-1",1,10,false);
        WorkerResults workerResults2 = new WorkerResults("W-2",1,10,false);
        ExecuteScheduleResult expectedResults = new ExecuteScheduleResult(report, policyResults, List.of(workerResults1,workerResults2));
        assertEquals(expectedResults, calculatedResults);
    }

    @Test
    void executeScheduleExecutionService_throwsWhenDuplicateWorkerAssigned() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-2", List.of("W-1","W-1"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-2", Collections.singletonList(shiftRequest));
        SimulationRunRepository fakeRepository = run -> run;
        ObjectMapper objectMapper = new ObjectMapper();
        ScheduleExecutionService executionService = new ScheduleExecutionService(fakeRepository, objectMapper);
        ExecuteScheduleResult calculatedResults = executionService.execute(scheduleRequest);
        ExecutionReport globalFailureReport = new ExecutionReport(0,0,0, List.of());
        PolicyResults globalFailureResults = new PolicyResults(FAILURE,FAILED_COMPLETELY,FIX_INPUT_DATA);
        ExecuteScheduleResult expectedResults = new ExecuteScheduleResult (globalFailureReport,globalFailureResults,List.of());
        assertEquals(expectedResults,calculatedResults);
    }

}
