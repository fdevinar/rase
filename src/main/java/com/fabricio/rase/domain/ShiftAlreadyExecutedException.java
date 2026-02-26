package com.fabricio.rase.domain;

public class ShiftAlreadyExecutedException extends DomainException {
    public ShiftAlreadyExecutedException() {
        super("Shift already executed");
    }
}
