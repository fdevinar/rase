package com.fabricio.rase.application;
import com.fabricio.rase.domain.Schedule;

public class ExecuteScheduleUseCase {

    private final ScheduleExecutionEngine engine = new Runner();
    private final PolicyEvaluator evaluator = new PolicyEvaluator();

    public ExecuteScheduleResult execute(Schedule schedule) {
        ExecutionReport report = engine.run(schedule);
        PolicyResults results = evaluator.evaluate(report);
        return new ExecuteScheduleResult(report,results);
    }

}