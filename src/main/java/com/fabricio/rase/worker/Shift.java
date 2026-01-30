package com.fabricio.rase.worker;

import java.util.ArrayList;
import java.util.List;

public class Shift {

    private final String id;
    private final List<Worker> assignments = new ArrayList<>();

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
        for (Worker worker : assignments) {
            worker.workShift();
        }
    }

}
