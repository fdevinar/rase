package com.fabricio.rase.domain;

import java.util.ArrayList;
import java.util.List;

public class Shift {

    private final String id;
    private final List<Worker> assignments = new ArrayList<>();
    private boolean isExecuted = false;

    public Shift(String id) {
        if ( id == null || id.isBlank() ) {
            throw new IllegalArgumentException("Shift id must not be null or blank");
        }
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void assign(Worker worker) {
        if (worker == null) {
            throw new IllegalArgumentException("Worker must not be null");
        }
        if (assignments.contains(worker)) {
            throw new WorkerAlreadyAssignedToShiftException();
        }
        assignments.add(worker);
    }

    public void execute() {
        if (assignments.isEmpty()) {
            throw new ShiftHasNoAssignmentsException();
        }
        if (isExecuted) {
            throw new ShiftAlreadyExecutedException();
        }
        // CHECK IF EVERY WORKER CAN WORK, TO AVOID CHANGING STATE WHEN EXECUTION FAILS
        for (Worker worker : assignments) {
            worker.assertCanWork();
        }
        for (Worker worker : assignments) {
            worker.performWork();
        }
        isExecuted = true;
    }

}
