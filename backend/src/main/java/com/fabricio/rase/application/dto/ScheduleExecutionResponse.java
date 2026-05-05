package com.fabricio.rase.application.dto;

import java.util.List;

public record ScheduleExecutionResponse (
        int totalShifts,
        int successfulShifts,
        int failedShifts,
        List<ShiftExecutionResponse> shiftResults,
        String userExecutionOutcome,
        String userSuggestedAction,
        List<WorkerResultsResponse> workerResults
)
{}
