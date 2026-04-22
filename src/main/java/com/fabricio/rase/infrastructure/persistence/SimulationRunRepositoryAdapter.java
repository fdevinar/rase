package com.fabricio.rase.infrastructure.persistence;

import com.fabricio.rase.application.SimulationRunRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SimulationRunRepositoryAdapter implements SimulationRunRepository {

    final SimulationRunJpaRepository jpaRepository;

    public SimulationRunRepositoryAdapter(SimulationRunJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public SimulationRun save(SimulationRun run) {
        return jpaRepository.save(run);
    }
}
