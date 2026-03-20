package com.fabricio.rase.application;
import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.application.dto.ShiftRequest;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;
import com.fabricio.rase.domain.Worker;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ScheduleMapperTest {

    @Test
    void mapScheduleRequest_returnsExpectedSchedule() {
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



}


//✔ Happy path
//given ScheduleRequest
//→ map()
//→ Schedule
//Verify:
//correct number of shifts
//correct number of workers per shift
//correct IDs

//🔥 Important test (don’t skip this one)
//✔ Identity test
//This is the whole point of your Map.
//same workerId in two shifts
//→ same Worker instance
//Conceptually:
//Shift A → Worker("W-1")
//Shift B → Worker("W-1")
//assert same instance
//This test proves your mapper is correctly building the object graph.