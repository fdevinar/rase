package com.fabricio.rase.application;

public record ShiftResult (
        String shiftId,
        boolean successful,
        String failureType,
        String failureMessage
){}