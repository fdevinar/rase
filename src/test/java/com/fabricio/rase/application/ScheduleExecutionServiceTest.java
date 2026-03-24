package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.application.dto.ShiftRequest;
import com.fabricio.rase.domain.WorkerAlreadyAssignedToShiftException;
import org.junit.jupiter.api.Test;
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
        ScheduleExecutionService executionService = new ScheduleExecutionService();
        ExecuteScheduleResult calculatedResults = executionService.execute(scheduleRequest);
        ExecutionReport report = new ExecutionReport(1,1,0, Collections.singletonList(new ShiftResult("SH-1", true, null, null)));
        PolicyResults results = new PolicyResults(SUCCESS, COMPLETED_SUCCESSFULLY, NO_ACTION_NEEDED);
        ExecuteScheduleResult expectedResults = new ExecuteScheduleResult(report, results);
        assertEquals(expectedResults, calculatedResults);
    }

    @Test
    void executeScheduleExecutionService_throwsWhenDuplicateWorkerAssigned() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-2", List.of("W-1","W-1"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-2", Collections.singletonList(shiftRequest));
        ScheduleExecutionService executionService = new ScheduleExecutionService();
        assertThrows(WorkerAlreadyAssignedToShiftException.class, () -> executionService.execute(scheduleRequest));
    }
    // NO UNIFIED RESULT YET , SERVICE THROWS AND RETURNS REPORTS

}
