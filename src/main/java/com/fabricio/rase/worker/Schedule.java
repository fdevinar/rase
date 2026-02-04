package com.fabricio.rase.worker;

import java.util.List;

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


}
