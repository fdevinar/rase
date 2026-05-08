package com.fabricio.rase.infrastructure;

import com.fabricio.rase.application.ScheduleExecutionResponseMapper;
import com.fabricio.rase.application.ScheduleExecutionService;
import com.fabricio.rase.application.dto.ScheduleExecutionResponse;
import com.fabricio.rase.application.dto.ScheduleRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/schedule")
public class ScheduleController {

    final ScheduleExecutionService executionService;
    final ScheduleExecutionResponseMapper responseMapper;

    // DEPENDENCY INJECTION
    public ScheduleController(ScheduleExecutionService executionService, ScheduleExecutionResponseMapper responseMapper) {
        this.executionService = executionService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/execute")
    public ScheduleExecutionResponse executeSchedule (@Valid @RequestBody ScheduleRequest request) {
        return responseMapper.map(executionService.execute(request));
    }

}
