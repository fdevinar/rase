package com.fabricio.rase.application;
import com.fabricio.rase.domain.Schedule;

import java.util.List;

public class ExecuteScheduleUseCase {

    private final ScheduleExecutionEngine engine = new Runner();
    private final PolicyEvaluator evaluator = new PolicyEvaluator();

//    TODO: build workerResults

    public ExecuteScheduleResult execute(Schedule schedule) {
        ExecutionReport report = engine.run(schedule);
        PolicyResults policyResults = evaluator.evaluate(report);
        return new ExecuteScheduleResult(report,policyResults);
    }

}