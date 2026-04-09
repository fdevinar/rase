package com.fabricio.rase.application;

import java.util.List;

public record ExecuteScheduleResult(
    ExecutionReport executionReport,
    PolicyResults policyResults,
    List<WorkerResults> workerResults
)
{}
