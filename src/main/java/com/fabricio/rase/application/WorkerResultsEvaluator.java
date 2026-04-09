package com.fabricio.rase.application;

import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.domain.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerResultsEvaluator {

    public List<WorkerResults> evaluate(Schedule schedule) {
        List<WorkerResults> workerResultsList = new ArrayList<>();

        for (Worker worker : schedule.getWorkers()) {
            workerResultsList.add(
                    new WorkerResults(
                            worker.getId(),
                            worker.getTotalShiftsWorked(),
                            worker.getFatigue(),
                            !worker.canWork()
                    )
            );
        }
        return workerResultsList;
    }
}
