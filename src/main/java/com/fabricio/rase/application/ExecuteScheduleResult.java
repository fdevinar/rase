package com.fabricio.rase.application;

public record ExecuteScheduleResult(
    ExecutionReport executionReport,
    PolicyResults policyResults
)
{}
