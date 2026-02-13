package com.fabricio.rase.application;

public class UserSuggestedActionPolicy
{
    public enum UserSuggestedAction {
        NO_ACTION_NEEDED,
        REVIEW_FAILURES,
    }

    public UserSuggestedAction evaluate (ExecutionReport report) {
        if (report.totalShifts() == report.successfulShifts()) {
            return UserSuggestedAction.NO_ACTION_NEEDED;
        }
        else {
            return UserSuggestedAction.REVIEW_FAILURES;
        }
    }

}



