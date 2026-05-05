package com.fabricio.rase.application.dto;

public record ShiftExecutionResponse (
    String shiftId,
    boolean successful,
    String failureType,
    String failureMessage
)
{}
