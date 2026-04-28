package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleExecutionResponseMapperTest {

    @Test
    void map_returnsSuccessfulResponse() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-1", List.of("W-1","W-2"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-1", Collections.singletonList(shiftRequest));
//        SimulationRunRepository fakeRepository = run -> run;
        FakeSimulationRunRepository fakeRepository = new FakeSimulationRunRepository();
        ObjectMapper objectMapper = new ObjectMapper();
        ScheduleExecutionService executionService = new ScheduleExecutionService(fakeRepository, objectMapper);
        ExecuteScheduleResult scheduleResult = executionService.execute(scheduleRequest);
        ScheduleExecutionResponseMapper executionResponseMapper = new ScheduleExecutionResponseMapper();
        ScheduleExecutionResponse calculatedResponse = executionResponseMapper.map(scheduleResult);
        ShiftExecutionResponse shiftResponse = new ShiftExecutionResponse("SH-1",true,null,null);
        WorkerResultsResponse workerResponse1 = new WorkerResultsResponse("W-1",1,10,false);
        WorkerResultsResponse workerResponse2 = new WorkerResultsResponse("W-2",1,10,false);
        ScheduleExecutionResponse expectedResponse = new ScheduleExecutionResponse
                (1,1,0,Collections.singletonList(shiftResponse),"COMPLETED_SUCCESSFULLY","NO_ACTION_NEEDED",List.of(workerResponse1,workerResponse2));
        assertEquals(expectedResponse, calculatedResponse);
    }

    @Test
    void map_returnsFailedResponseWhenNoAssignments() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-1", List.of());
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-1", Collections.singletonList(shiftRequest));
//        SimulationRunRepository fakeRepository = run -> run;
        FakeSimulationRunRepository fakeRepository = new FakeSimulationRunRepository();
        ObjectMapper objectMapper = new ObjectMapper();
        ScheduleExecutionService executionService = new ScheduleExecutionService(fakeRepository, objectMapper);
        ExecuteScheduleResult scheduleResult = executionService.execute(scheduleRequest);
        ScheduleExecutionResponseMapper executionResponseMapper = new ScheduleExecutionResponseMapper();
        ScheduleExecutionResponse calculatedResponse = executionResponseMapper.map(scheduleResult);
        ShiftExecutionResponse shiftResponse = new ShiftExecutionResponse("SH-1",false,"SHIFT_HAS_NO_ASSIGNMENTS","Shift cannot execute because it has no assignments.");
        ScheduleExecutionResponse expectedResponse = new ScheduleExecutionResponse
                (1,0,1,Collections.singletonList(shiftResponse),"FAILED_COMPLETELY","REVIEW_FAILURES",List.of());
        assertEquals(expectedResponse, calculatedResponse);
    }


}
