package org.eluder.score.tables.api.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface VersionedDocument {

    String message() default "{validation.constraints.VersionedDocument.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
