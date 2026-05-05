package com.fabricio.rase.application;

import java.util.List;

public record ExecutionReport(
        int totalShifts,
        int successfulShifts,
        int failedShifts,
        List<ShiftResult> results
)
{
    public boolean hasFailures() {
        return failedShifts > 0;
    }
    public boolean isFullySuccessful() {
        return totalShifts == successfulShifts;
    }
    public boolean isCompletelyFailed() {
        return totalShifts == failedShifts;
    }
}
