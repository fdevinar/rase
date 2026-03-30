package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleExecutionResponse;
import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.application.dto.ShiftExecutionResponse;
import com.fabricio.rase.application.dto.ShiftRequest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleExecutionResponseMapperTest {

    @Test
    void map_returnsSuccessfulResponse() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-1", List.of("W-1","W-2"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-1", Collections.singletonList(shiftRequest));
        ScheduleExecutionService executionService = new ScheduleExecutionService();
        ExecuteScheduleResult scheduleResult = executionService.execute(scheduleRequest);
        ScheduleExecutionResponseMapper executionResponseMapper = new ScheduleExecutionResponseMapper();
        ScheduleExecutionResponse calculatedResponse = executionResponseMapper.map(scheduleResult);
        ShiftExecutionResponse shiftResponse = new ShiftExecutionResponse("SH-1",true,null,null);
        ScheduleExecutionResponse expectedResponse = new ScheduleExecutionResponse
                (1,1,0,Collections.singletonList(shiftResponse),"COMPLETED_SUCCESSFULLY","NO_ACTION_NEEDED");
        assertEquals(expectedResponse, calculatedResponse);
    }

    @Test
    void map_returnsFailedResponseWhenNoAssignments() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-1", List.of());
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-1", Collections.singletonList(shiftRequest));
        ScheduleExecutionService executionService = new ScheduleExecutionService();
        ExecuteScheduleResult scheduleResult = executionService.execute(scheduleRequest);
        ScheduleExecutionResponseMapper executionResponseMapper = new ScheduleExecutionResponseMapper();
        ScheduleExecutionResponse calculatedResponse = executionResponseMapper.map(scheduleResult);
        ShiftExecutionResponse shiftResponse = new ShiftExecutionResponse("SH-1",false,"SHIFT_HAS_NO_ASSIGNMENTS","Shift cannot execute because it has no assignments.");
        ScheduleExecutionResponse expectedResponse = new ScheduleExecutionResponse
                (1,0,1,Collections.singletonList(shiftResponse),"FAILED_COMPLETELY","REVIEW_FAILURES");
        assertEquals(expectedResponse, calculatedResponse);
    }


}
