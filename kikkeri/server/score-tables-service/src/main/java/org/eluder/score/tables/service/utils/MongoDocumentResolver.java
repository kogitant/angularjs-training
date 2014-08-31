package org.eluder.score.tables.service.utils;

import org.eluder.score.tables.api.BaseDocument;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class MongoDocumentResolver<D extends BaseDocument> implements Function<String, D> {
    
    private final MongoOperations mongoOperations;
    private final Class<D> documentType;
    private final String[] fields;
    private final LoadingCache<String, D> cache;
    
    public MongoDocumentResolver(final MongoOperations mongoOperations, final Class<D> documentType, final String... fields) {
        this.mongoOperations = mongoOperations;
        this.documentType = documentType;
        this.fields = fields;
        this.cache = CacheBuilder.newBuilder().build(new CacheLoader<String, D>() {
            @Override
            public D load(final String key) throws Exception {
                return findDocument(key);
            }
        });
    }

    @Override
    public D apply(final String input) {
        return cache.getUnchecked(input);
    }
    
    private D findDocument(final String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        if (fields != null) {
            for (String field : fields) {
                query.fields().include(field);
            }
        }
        return mongoOperations.findOne(query, documentType);
    }
}
