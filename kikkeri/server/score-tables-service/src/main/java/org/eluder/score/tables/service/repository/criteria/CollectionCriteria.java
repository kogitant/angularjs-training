package org.eluder.score.tables.service.repository.criteria;

import java.util.Collection;

import org.springframework.data.mongodb.core.query.Criteria;

public class CollectionCriteria extends Criteria {

    public CollectionCriteria(final String key, final Collection<?> values) {
        super(key);
        if (values.size() == 1) {
            is(values.iterator().next());
        } else {
            in(values);
        }
    }
    
    public static CollectionCriteria isIn(final String key, final Collection<?> values) {
        return new CollectionCriteria(key, values);
    }
}
