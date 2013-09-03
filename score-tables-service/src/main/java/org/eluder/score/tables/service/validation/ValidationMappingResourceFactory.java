package org.eluder.score.tables.service.validation;

import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class ValidationMappingResourceFactory {

    private static final String HEADER = 
            "<constraint-mappings " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xsi:schemaLocation=\"http://jboss.org/xml/ns/javax/validation/mapping validation-mapping-1.0.xsd\" " +
                "xmlns=\"http://jboss.org/xml/ns/javax/validation/mapping\">";
    private static final String FOOTER =
            "</constraint-mappings>";
    private static final String MAPPING =
            "<constraint-definition annotation=\"%s\">" +
                "<validated-by include-existing-validators=\"false\">" +
                    "<value>%s</value>" +
                "</validated-by>" +
            "</constraint-definition>";
    
    public static class ValidationMapping<A extends Annotation, T extends ConstraintValidator<A, ?>> {
        private final Class<A> annotation;
        private final Class<T> validator;
        
        protected ValidationMapping(final Class<A> annotation, final Class<T> validator) {
            this.annotation = annotation;
            this.validator = validator;
        }
        
        public Class<A> getAnnotation() {
            return annotation;
        }
        
        public Class<T> getValidator() {
            return validator;
        }

        public static <A extends Annotation, T extends ConstraintValidator<A, ?>> ValidationMapping<A, T> create(final Class<A> annotation, final Class<T> validator) {
            return new ValidationMapping<>(annotation, validator);
        }
    }
    
    private final List<ValidationMapping<?, ?>> mappings = new ArrayList<>();
    
    public ValidationMappingResourceFactory add(final ValidationMapping<?, ?>... mappings) {
        for (ValidationMapping<?, ?> mapping : mappings) {
            this.mappings.add(mapping);
        }
        return this;
    }
    
    public Resource create() {
        StringBuilder definition = new StringBuilder();
        definition.append(HEADER).append("\n");
        for (ValidationMapping<?, ?> mapping : mappings) {
            definition.append(String.format(MAPPING, mapping.getAnnotation().getName(), mapping.getValidator().getName())).append("\n");
        }
        definition.append(FOOTER).append("\n");
        return new ByteArrayResource(definition.toString().getBytes(Charset.forName("UTF-8")));
    }
    
}
