package com.fabricio.rase.application.dto;

public record WorkerResultsResponse(
        String workerId,
        int totalShiftsWorked,
        int finalFatigue,
        boolean isFatigued
)
{}