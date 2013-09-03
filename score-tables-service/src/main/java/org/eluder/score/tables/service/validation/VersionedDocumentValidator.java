package org.eluder.score.tables.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.eluder.score.tables.api.MutableDocument;
import org.eluder.score.tables.api.validation.VersionedDocument;

public class VersionedDocumentValidator implements ConstraintValidator<VersionedDocument, MutableDocument> {

    @Override
    public void initialize(final VersionedDocument constraintAnnotation) {

    }
    
    @Override
    public boolean isValid(final MutableDocument value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return ((value.getId() == null && value.getVersion() == null) ||
                (value.getId() != null && value.getVersion() != null));
    }
}
