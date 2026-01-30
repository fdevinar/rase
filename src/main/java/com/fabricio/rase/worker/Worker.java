package com.fabricio.rase.worker;

public class Worker {

    private final String id;
    private int fatigue;
    private static final int MAX_FATIGUE = 100;
    private static final int MIN_FATIGUE = 0;
    private static final int FATIGUE_PER_SHIFT = 10;

    public Worker(String id) {
        if ( id == null ||  id.isBlank() ) {
            throw new IllegalArgumentException("Worker id must not be null or blank");
        }
        this.id = id;
        this.fatigue = MIN_FATIGUE;
    }
    public String getId() {
        return id;
    }
    public void workShift() {
        if (fatigue >= MAX_FATIGUE) {
            throw new IllegalStateException("Worker is already exhausted");
        }
        fatigue = Math.min(MAX_FATIGUE, fatigue + FATIGUE_PER_SHIFT);
    }
    public void rest() {
        if (fatigue <= MIN_FATIGUE) {
            throw new IllegalStateException("Worker is already rested");
        }
        fatigue = Math.max(MIN_FATIGUE, fatigue - FATIGUE_PER_SHIFT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;

        Worker worker = (Worker) o;
        return id.equals(worker.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }

}

