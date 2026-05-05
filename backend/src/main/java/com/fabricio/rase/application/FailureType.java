package com.fabricio.rase.application;

public enum FailureType {
    WORKER_TOO_FATIGUED,
    WORKER_ALREADY_ASSIGNED,
    SHIFT_HAS_NO_ASSIGNMENTS,
    SHIFT_ALREADY_EXECUTED,
    UNKNOWN_DOMAIN_ERROR
}