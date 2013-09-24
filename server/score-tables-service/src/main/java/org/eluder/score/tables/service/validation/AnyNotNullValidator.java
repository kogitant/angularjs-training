package org.eluder.score.tables.service.validation;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.eluder.score.tables.api.validation.AnyNotNull;
import org.springframework.util.ReflectionUtils;

public class AnyNotNullValidator implements ConstraintValidator<AnyNotNull, Object> {

    private String[] fieldNames;
    
    @Override
    public void initialize(final AnyNotNull constraintAnnotation) {
        this.fieldNames = constraintAnnotation.value();
    }
    
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Class<?> type = value.getClass();
        for (String fieldName : fieldNames) {
            Field field = ReflectionUtils.findField(type, fieldName);
            field.setAccessible(true);
            if (ReflectionUtils.getField(field, value) != null) {
                return true;
            }
        }
        return false;
    }
    
}
