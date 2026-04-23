package com.fabricio.rase.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "simulation_run")
public class SimulationRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="simulation_status", nullable = false)
    private SimulationStatus status;
    @Column(name="created_at", nullable = false)
    private Instant createdAt;
    @Column(name="input_json", nullable = false)
    private String inputJson;
    @Column(name="result_json", nullable = false)
    private String resultJson;

    // Constructors

    // JPA required default constructor
    protected SimulationRun() {}

    public SimulationRun(
                    Instant createdAt,
                    SimulationStatus status,
                    String inputJson,
                    String resultJson) {
        this.createdAt = createdAt;
        this.status = status;
        this.inputJson = inputJson;
        this.resultJson = resultJson;
    }

    // Getters
    public Long getId() { return id; }
    public Instant getCreatedAt() { return createdAt; }
    public SimulationStatus getStatus() { return status; }
    public String getInputJson() { return inputJson; }
    public String getResultJson() { return resultJson; }

}
