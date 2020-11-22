/*
 * Copyright 2020 FlowSquad GmbH
 */

package de.hsaugsburg.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Diese Exception sollte geworfen werden, wenn ein Objekt nicht aus der Datenbank geladen werden kann.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(final String message) {
        super(message);
    }

    public ObjectNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
