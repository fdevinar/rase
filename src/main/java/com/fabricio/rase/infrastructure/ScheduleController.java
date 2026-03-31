package com.fabricio.rase.infrastructure;

import com.fabricio.rase.application.ScheduleExecutionResponseMapper;
import com.fabricio.rase.application.ScheduleExecutionService;
import com.fabricio.rase.application.dto.ScheduleExecutionResponse;
import com.fabricio.rase.application.dto.ScheduleRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    final ScheduleExecutionService executionService;
    final ScheduleExecutionResponseMapper responseMapper;

    public ScheduleController(ScheduleExecutionService executionService, ScheduleExecutionResponseMapper responseMapper) {
        this.executionService = executionService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/execute")
    public ScheduleExecutionResponse executeSchedule (@RequestBody ScheduleRequest request) {
        return responseMapper.map(executionService.execute(request));
    }

}
