package com.fabricio.rase.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ShiftRequest (
        @NotBlank String shiftId,
        @NotEmpty List<String> workerIds
)
{}
