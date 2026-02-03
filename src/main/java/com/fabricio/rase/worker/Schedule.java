package com.fabricio.rase.worker;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private final String id;
    private final List<Shift> scheduledShifts;

    public Schedule(String id, List<Shift> shifts) {
        this.scheduledShifts = List.copyOf(shifts);
        this.id = id;
    }
    public String getId() { return id; }
    public List<Shift> getScheduledShifts() { return scheduledShifts; }

}
