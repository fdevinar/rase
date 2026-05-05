package com.fabricio.rase.infrastructure;


import com.fabricio.rase.application.SimulationRunQueryService;
import com.fabricio.rase.infrastructure.persistence.SimulationRun;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runs")
public class SimulationRunController {

    final SimulationRunQueryService queryService;

    // DEPENDENCY INJECTION
    public SimulationRunController(SimulationRunQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public List<SimulationRun> getAllRuns() {
        return queryService.getAllRuns();
    }

    @GetMapping("/{id}")
    public SimulationRun getRunById(@PathVariable Long id) {
        return queryService.getRunById(id);
    }



}
