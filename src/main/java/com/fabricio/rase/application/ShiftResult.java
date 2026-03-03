package com.fabricio.rase.application;

public record ShiftResult (
        String shiftId,
        boolean successful,
        FailureType failureType,
        String failureMessage
){}