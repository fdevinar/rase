package com.fabricio.rase.application;

import com.fabricio.rase.infrastructure.persistence.SimulationRun;

public interface SimulationRunRepository {
    SimulationRun save (SimulationRun run);
}
