package com.fabricio.rase.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

public class ScheduleTest {

    @Test
    void cannotCreateScheduleWithNullId() {
        Shift shift = new Shift("SH-2");
        assertThrows(IllegalArgumentException.class,
                () -> new Schedule(null,List.of(shift)));
    }
    @Test
    void cannotCreateScheduleWithBlankId() {
        Shift shift = new Shift("SH-3");
        assertThrows(IllegalArgumentException.class,
                () -> new Schedule("",List.of(shift)));
    }
    @Test
    void cannotCreateScheduleWithNullShiftList() {
        assertThrows(IllegalArgumentException.class,
                () -> new Schedule("S-4", null));
    }
    @Test
    void ScheduleMaintainsOrderOfShifts() {
        Shift shift = new Shift("SH-5");
        Shift shiftB = new Shift("SH-5B");
        List<Shift> shiftList = List.of(shift, shiftB);
        Schedule schedule = new Schedule("SC-5", shiftList);
        assertEquals(shiftList, schedule.scheduledShifts());
    }
    @Test
    void cannotChangeSchedule() {
        Shift shift = new Shift("SH-6");
        Shift shiftB = new Shift("SH-6B");
        List<Shift> shiftList = List.of(shift, shiftB);
        Schedule schedule = new Schedule("SC-6", shiftList);
        Shift shiftC = new Shift("SH-6C");
        assertThrows(UnsupportedOperationException.class,
                () -> schedule.scheduledShifts().add(shiftC));
    }
    @Test
    void getWorkers_getsCorrectWorkers() {
        Shift shift = new Shift("SH-7");
        Worker worker = new Worker("W-7");
        shift.assign(worker);
        Schedule schedule = new Schedule("SC-7", List.of(shift));
        assertEquals(Set.of(worker),schedule.getWorkers());
    }
    @Test
    void getWorkers_doesntGetDuplicateWorkers() {
        Shift shift = new Shift("SH-8");
        Shift shiftB = new Shift("SH-8B");
        Worker worker = new Worker("W-8");
        shift.assign(worker);
        shiftB.assign(worker);
        Schedule schedule = new Schedule("SC-8", List.of(shift,shiftB));
        assertEquals(Set.of(worker),schedule.getWorkers());
    }

}
