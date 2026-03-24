package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.domain.Schedule;

public class ScheduleExecutionService {

    private final ScheduleMapper scheduleMapper = new ScheduleMapper();
    private final ExecuteScheduleUseCase useCase = new ExecuteScheduleUseCase();

    public ExecuteScheduleResult execute (ScheduleRequest request) {
        Schedule schedule = scheduleMapper.map(request);
        return useCase.execute(schedule);
    }

}


