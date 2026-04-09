package com.fabricio.rase.application;
import com.fabricio.rase.domain.Schedule;

import java.util.List;

public class ExecuteScheduleUseCase {

    private final ScheduleExecutionEngine engine = new Runner();
    private final PolicyEvaluator policyEvaluator = new PolicyEvaluator();
    private final WorkerResultsEvaluator workerEvaluator = new WorkerResultsEvaluator();

    public ExecuteScheduleResult execute(Schedule schedule) {
        ExecutionReport report = engine.run(schedule);
        PolicyResults policyResults = policyEvaluator.evaluate(report);
        List<WorkerResults> workerResults = workerEvaluator.evaluate(schedule);


        return new ExecuteScheduleResult(report,policyResults,workerResults);
    }

}