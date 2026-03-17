package com.fabricio.rase.application.dto;

import java.util.List;

public record ScheduleRequest (
    String scheduleId,
    List<ShiftRequest> shifts
)
{}
