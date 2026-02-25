package com.fabricio.rase.domain;

public class WorkerTooFatiguedException extends DomainException {
    public WorkerTooFatiguedException() {
        super("Worker is too fatigued to perform work.");
    }
}
