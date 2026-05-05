package com.fabricio.rase.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ScheduleRequest (
    @NotBlank String scheduleId,
    @NotEmpty @Valid List<ShiftRequest> shifts
)
{}
