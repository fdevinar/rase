package com.fabricio.rase.worker;

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
            throw new IllegalArgumentException("Worker is already in assignment");
        }
        assignments.add(worker);
    }

    public void execute() {
        if (assignments.isEmpty()) {
            throw new IllegalArgumentException("Assignment is empty");
        }
        if (isExecuted) {
            throw new IllegalStateException("Shift already executed");
        }
        // DECIDE IF WORK CAN BE PERFORMED
        for (Worker worker : assignments) {
            if (!worker.canWork()) {
                throw new IllegalStateException("Shift contains workers that can't work");
            }
        }
        for (Worker worker : assignments) {
            worker.workShift();
        }
        isExecuted = true;
    }

}
