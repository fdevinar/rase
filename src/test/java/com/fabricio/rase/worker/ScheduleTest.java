package com.fabricio.rase.worker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScheduleTest {

    @Test
    void createsSchedule() {
        Shift shift = new Shift("SH-1");
        Shift shiftB = new Shift("SH-2");
        List<Shift> shiftList = List.of(shift, shiftB);
        Schedule schedule = new Schedule("SC-1", shiftList);
    }

}
