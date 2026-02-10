package com.fabricio.rase.application;

import java.util.List;

public record ExecutionReport(
        int totalShifts,
        int successfulShifts,
        int failedShifts,
        List<String> messages
) {}
