package com.fabricio.rase.application;

import com.fabricio.rase.infrastructure.persistence.SimulationRun;

import java.util.List;
import java.util.Optional;

public interface SimulationRunRepository {
    SimulationRun save (SimulationRun run);
    List<SimulationRun> findAll ();
    Optional<SimulationRun> findById(Long id);
}
