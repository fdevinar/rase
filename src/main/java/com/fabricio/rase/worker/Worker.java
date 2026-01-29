package com.fabricio.rase.worker;

public class Worker {

    private final String id;
    private int fatigue;

    public Worker(String id) {
        if ( id == null ||  id.isBlank() ) {
            throw new IllegalArgumentException("Worker id must not be null or blank");
        }
        this.id = id;
        this.fatigue = 0;
    }

    public String getId() {
        return id;
    }

    public void workShift() {
        if (fatigue >= 100) {
            throw new IllegalStateException("Worker is already exhausted");
        }
        fatigue = Math.min(100, fatigue + 10);
    }

}

