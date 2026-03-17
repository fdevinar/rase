package com.fabricio.rase.application.dto;

import java.util.List;

public record ShiftRequest (
        String shiftId,
        List<String> workerIds
)
{}
