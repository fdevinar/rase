package com.fabricio.rase.domain;

public class WorkerAlreadyAssignedToShiftException extends DomainException {
    public WorkerAlreadyAssignedToShiftException() {
        super("Worker is already assigned to this shift.");
    }
}
