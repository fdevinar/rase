package com.fabricio.rase.application;

import com.fabricio.rase.application.SystemExecutionOutcomePolicy.*;
import com.fabricio.rase.application.UserExecutionOutcomePolicy.*;
import com.fabricio.rase.application.UserSuggestedActionPolicy.*;

public record PolicyResults (
    SystemExecutionOutcome systemExecution,
    UserExecutionOutcome userExecution,
    UserSuggestedAction suggestedAction
)
{}