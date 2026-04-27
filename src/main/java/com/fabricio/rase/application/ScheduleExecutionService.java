package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.domain.DomainException;
import com.fabricio.rase.domain.Schedule;
import com.fabricio.rase.infrastructure.persistence.SimulationRun;
import com.fabricio.rase.infrastructure.persistence.SimulationStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.FAILURE;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.FAILED_COMPLETELY;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.FIX_INPUT_DATA;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.REVIEW_FAILURES;

@Service
public class ScheduleExecutionService {

    private final ScheduleMapper scheduleMapper = new ScheduleMapper();
    private final ExecuteScheduleUseCase useCase = new ExecuteScheduleUseCase();
    private final SimulationRunRepository repository;
    private final ObjectMapper objectMapper;

//    TODO: USE JACKSON - OBJECT MAPPER TO LOG REQ AND RES

    // DEPENDENCY INJECTION
    public ScheduleExecutionService(SimulationRunRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public ExecuteScheduleResult execute (ScheduleRequest request) {
        try {
            Schedule schedule = scheduleMapper.map(request);
            ExecuteScheduleResult scheduleResult = useCase.execute(schedule);
            // BUILD SIMULATION RUN
            SimulationRun simulation = new SimulationRun(
                    Instant.now(),
                    SimulationStatus.SUCCESS,
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(scheduleResult)
            );
            repository.save(simulation);
            return scheduleResult;
        } catch (DomainException e) {
            ExecutionReport globalFailureReport = new ExecutionReport(0,0,0, List.of());
            PolicyResults globalFailureResults = new PolicyResults(FAILURE,FAILED_COMPLETELY,FIX_INPUT_DATA);
            // BUILD SIMULATION RUN
            SimulationRun simulation = new SimulationRun(
                    Instant.now(),
                    SimulationStatus.FAILURE,
                    "FAILURE",
                    "FAILURE"
            );
            repository.save(simulation);
            return new ExecuteScheduleResult(globalFailureReport,globalFailureResults,List.of());
        }
//        HANDLE OBJECT MAPPER FAILURE
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
