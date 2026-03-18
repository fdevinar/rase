package com.fabricio.rase.application.dto;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Shift;
import com.fabricio.rase.domain.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleMapper {

    private Worker getOrCreate(String workerId, Map<String, Worker> workerCache) {
        Worker worker = workerCache.get(workerId);
        if (worker == null) {
            worker = new Worker(workerId);
            workerCache.put(workerId, worker);
        }
        return worker;
    }

    public Schedule map(ScheduleRequest scheduleRequest) {
        Map<String, Worker> workerCache = new HashMap<>();
        List<Shift> shifts = new ArrayList<>();
        for (ShiftRequest shiftRequest : scheduleRequest.shifts()) {
            Shift shift = new Shift(shiftRequest.shiftId());
            for (String workerId : shiftRequest.workerIds()) {
                Worker worker = getOrCreate(workerId, workerCache);
                shift.assign(worker);
            }
        shifts.add(shift);
        }
        return new Schedule(scheduleRequest.scheduleId(), shifts);
    }

}