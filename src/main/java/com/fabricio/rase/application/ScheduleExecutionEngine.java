package com.fabricio.rase.application;


import com.fabricio.rase.domain.Schedule;

public interface ScheduleExecutionEngine {
    ExecutionReport run (Schedule schedule);
}
