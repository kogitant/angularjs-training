package org.eluder.score.tables.service.validation;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.eluder.score.tables.api.BaseDocument;
import org.eluder.score.tables.api.SlugDocument;
import org.eluder.score.tables.api.validation.ExistingReference;
import org.eluder.score.tables.service.repository.SlugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.google.common.collect.ImmutableList;

public class ExistingReferenceValidator implements ConstraintValidator<ExistingReference, String> {

    @Autowired
    private MongoOperations mongoOperations;
    
    @Autowired
    private SlugRepository slugRepository;
    
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
            if (SlugDocument.class.isAssignableFrom(type)) {
                return (slugRepository.findOneByIdOrSlug(value, type.asSubclass(SlugDocument.class), ImmutableList.<String>of()) != null);
            } else {
                Query query = query(where("id").is(value));
                query.fields();
                return (mongoOperations.findOne(query, type) != null);
            }
        }
    }
}
