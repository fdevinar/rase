package com.fabricio.rase.infrastructure.persistence;

import java.time.Instant;

public class SimulationRun {

    private final String id;
    private String status;
    private Instant created_at;
    private String input_json;
    private String result_json;


    public SimulationRun(String id) {
        this.id = id;
    }
}
