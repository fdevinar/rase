package com.fabricio.rase.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Schedule(String id, List<Shift> scheduledShifts) {

    public Schedule(String id, List<Shift> scheduledShifts) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Cannot create Schedule with null or blank id");
        }
        if (scheduledShifts == null) {
            throw new IllegalArgumentException("Cannot create Schedule with null shift list");
        }
        this.scheduledShifts = List.copyOf(scheduledShifts);
        this.id = id;
    }

    public Set<Worker> getWorkers() {
        Set<Worker> workerSet = new HashSet<>();
        for (Shift shift : scheduledShifts) {
            workerSet.addAll(shift.getAssignments());
        }
        return workerSet;
    }


}
