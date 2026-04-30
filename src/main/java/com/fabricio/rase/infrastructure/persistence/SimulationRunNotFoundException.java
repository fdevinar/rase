package com.fabricio.rase.infrastructure.persistence;

public class SimulationRunNotFoundException extends RuntimeException {
    public SimulationRunNotFoundException() {
        super("Simulation run not found.");
    }
}
