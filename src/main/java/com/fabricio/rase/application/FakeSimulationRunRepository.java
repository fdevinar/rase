package com.fabricio.rase.application;

import com.fabricio.rase.infrastructure.persistence.SimulationRun;

import java.util.List;
import java.util.Optional;

public class FakeSimulationRunRepository implements SimulationRunRepository {

    @Override
    public SimulationRun save(SimulationRun run) {
        return null;
    }

    @Override
    public List<SimulationRun> findAll() {
        return List.of();
    }

    @Override
    public Optional<SimulationRun> findById(Long id) {
        return Optional.empty();
    }
}
