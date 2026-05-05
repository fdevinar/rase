package com.fabricio.rase.application;
import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.application.dto.ShiftRequest;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;
import com.fabricio.rase.domain.Worker;
import com.fabricio.rase.domain.WorkerAlreadyAssignedToShiftException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ScheduleMapperTest {

    @Test
    void mapScheduleRequest_returnsExpectedScheduleWithSuccess() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-1", List.of("W-1","W-2"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-1", Collections.singletonList(shiftRequest));
        ScheduleMapper scheduleMapper = new ScheduleMapper();
        Schedule mappedSchedule = scheduleMapper.map(scheduleRequest);
        Shift expectedShift = new Shift("SH-1");
        Worker worker1 = new Worker("W-1");
        Worker worker2 = new Worker("W-2");
        expectedShift.assign(worker1);
        expectedShift.assign(worker2);
        Schedule expectedSchedule = new Schedule("SC-1",Collections.singletonList(expectedShift));
        assertEquals(expectedSchedule, mappedSchedule);
    }

    @Test
    void mapScheduleRequest_throwsWhenDuplicateWorkerAssigned() {
        ShiftRequest shiftRequest = new ShiftRequest("SH-2", List.of("W-1","W-1"));
        ScheduleRequest scheduleRequest = new ScheduleRequest("SC-2", Collections.singletonList(shiftRequest));
        ScheduleMapper scheduleMapper = new ScheduleMapper();
        assertThrows(WorkerAlreadyAssignedToShiftException.class, () -> scheduleMapper.map(scheduleRequest));
    }

}
