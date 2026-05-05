package com.fabricio.rase.application;

public class PolicyEvaluator {

    private final SystemExecutionOutcomePolicy systemPolicy = new SystemExecutionOutcomePolicy();
    private final UserExecutionOutcomePolicy userPolicy = new UserExecutionOutcomePolicy();
    private final UserSuggestedActionPolicy suggestedAction = new UserSuggestedActionPolicy();

    public PolicyResults evaluate(ExecutionReport report) {
        return new PolicyResults(
                systemPolicy.evaluate(report),
                userPolicy.evaluate(report),
                suggestedAction.evaluate(report)
        );
    }

}
