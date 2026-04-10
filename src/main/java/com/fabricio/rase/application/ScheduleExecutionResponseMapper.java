package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleExecutionResponse;
import com.fabricio.rase.application.dto.ShiftExecutionResponse;
import com.fabricio.rase.application.dto.WorkerResultsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleExecutionResponseMapper {

    public ScheduleExecutionResponse map (ExecuteScheduleResult result) {

        ExecutionReport executionReport = result.executionReport();
        PolicyResults policyResults = result.policyResults();

        List<ShiftExecutionResponse> shiftResults = new ArrayList<>();
        for (ShiftResult shiftResult : executionReport.results()) {
            ShiftExecutionResponse shiftResponse =
                    new ShiftExecutionResponse(
                            shiftResult.shiftId(),
                            shiftResult.successful(),
                            shiftResult.failureType() != null ? shiftResult.failureType().toString() : null,
                            shiftResult.failureMessage()
                    );
            shiftResults.add(shiftResponse);
        }

        List<WorkerResultsResponse> workerResults = new ArrayList<>();
        for (WorkerResults worker : result.workerResults()) {
            WorkerResultsResponse workerResponse =
                    new WorkerResultsResponse(
                        worker.workerId(),
                        worker.totalShiftsWorked(),
                        worker.finalFatigue(),
                        worker.isFatigued()
                    );
            workerResults.add(workerResponse);
        }

        return new ScheduleExecutionResponse(
                executionReport.totalShifts(),
                executionReport.successfulShifts(),
                executionReport.failedShifts(),
                shiftResults,
                policyResults.userExecution().toString(),
                policyResults.suggestedAction().toString(),
                workerResults
                );
    }

}

