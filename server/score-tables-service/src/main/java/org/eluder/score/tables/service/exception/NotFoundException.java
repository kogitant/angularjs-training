package org.eluder.score.tables.service.exception;

public class NotFoundException extends ValidationException {

    public NotFoundException(final Class<?> type, final String id) {
        super(type.getSimpleName() + " with id " + id + " does not exist");
    }
    
}
