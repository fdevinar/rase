package com.fabricio.rase.domain;

public class ShiftHasNoAssignmentsException extends DomainException {
    public ShiftHasNoAssignmentsException() {
        super("Shift cannot execute because it has no assignments");
    }
}
