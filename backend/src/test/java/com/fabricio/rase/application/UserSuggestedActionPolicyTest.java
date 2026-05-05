package com.fabricio.rase.application;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.fabricio.rase.application.UserSuggestedActionPolicy.UserSuggestedAction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSuggestedActionPolicyTest {

    @Test
    void evaluate_userSuggestedActionSuccessful() {
        ExecutionReport executionReport = new ExecutionReport(2,2,0, List.of());
        UserSuggestedActionPolicy suggestedAction = new UserSuggestedActionPolicy();
        assertEquals(NO_ACTION_NEEDED,suggestedAction.evaluate(executionReport));
    }
    @Test
    void evaluate_userSuggestedActionFailure() {
        ExecutionReport executionReport = new ExecutionReport(2,1,1,List.of());
        UserSuggestedActionPolicy suggestedAction = new UserSuggestedActionPolicy();
        assertEquals(REVIEW_FAILURES,suggestedAction.evaluate(executionReport));
    }

}