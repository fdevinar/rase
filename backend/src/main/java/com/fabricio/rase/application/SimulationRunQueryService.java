package com.fabricio.rase.application;

import com.fabricio.rase.infrastructure.persistence.SimulationRun;
import com.fabricio.rase.infrastructure.persistence.SimulationRunNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationRunQueryService {

    private final SimulationRunRepository repository;

    public SimulationRunQueryService(SimulationRunRepository repository) {
        this.repository = repository;
    }

    public List<SimulationRun> getAllRuns() {
        return repository.findAll();
    }

    public SimulationRun getRunById(Long id) {
        return repository.findById(id)
                .orElseThrow(SimulationRunNotFoundException::new);
    }

}
