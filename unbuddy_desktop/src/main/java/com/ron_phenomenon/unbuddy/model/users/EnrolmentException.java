package com.ron_phenomenon.unbuddy.model.users;

/*
Exceptions caused by enrol/disenrol use cases, whose message can be shown to the user
 */

public class EnrolmentException extends RuntimeException {
    public EnrolmentException(final String errorMessage) {
        super(errorMessage);
    }
}
