package org.eluder.score.tables.service.validation;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.eluder.score.tables.api.BaseDocument;
import org.eluder.score.tables.api.validation.ExistingReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

public class ExistingReferenceValidator implements ConstraintValidator<ExistingReference, String> {

    @Autowired
    private MongoOperations mongoOperations;
    
    private Class<? extends BaseDocument> type;
    
    @Override
    public void initialize(final ExistingReference constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            Query query = query(where("id").is(value));
            query.fields();
            return (mongoOperations.findOne(query, type) != null);
        }
    }
}
