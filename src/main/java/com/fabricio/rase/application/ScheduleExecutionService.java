package com.fabricio.rase.application;

import com.fabricio.rase.application.dto.ScheduleRequest;
import com.fabricio.rase.domain.DomainException;
import com.fabricio.rase.domain.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fabricio.rase.application.SystemExecutionOutcomePolicy.SystemExecutionOutcome.FAILURE;
import static com.fabricio.rase.application.UserExecutionOutcomePolicy.UserExecutionOutcome.FAILED_COMPLETELY;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.FIX_INPUT_DATA;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.REVIEW_FAILURES;

@Service
public class ScheduleExecutionService {

    private final ScheduleMapper scheduleMapper = new ScheduleMapper();
    private final ExecuteScheduleUseCase useCase = new ExecuteScheduleUseCase();

    public ExecuteScheduleResult execute (ScheduleRequest request) {
        try {
            Schedule schedule = scheduleMapper.map(request);
            return useCase.execute(schedule);
        } catch (DomainException e) {
            ExecutionReport globalFailureReport = new ExecutionReport(0,0,0, List.of());
            PolicyResults globalFailureResults = new PolicyResults(FAILURE,FAILED_COMPLETELY,FIX_INPUT_DATA);
            return new ExecuteScheduleResult(globalFailureReport,globalFailureResults);
        }
    }

}