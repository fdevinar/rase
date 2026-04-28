package com.fabricio.rase.infrastructure.persistence;

import com.fabricio.rase.application.SimulationRunRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<SimulationRun> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<SimulationRun> findById(Long id) {
        return jpaRepository.findById(id);
    }

}
