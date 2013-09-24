package org.eluder.score.tables.service.repository;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.eluder.score.tables.api.SlugDocument;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SlugRepositoryImpl extends AbstractCustomRepository implements SlugRepository {
    
    @Override
    public <T extends SlugDocument> T findOneByIdOrSlug(final String idOrSlug, final Class<T> entityClass) {
        return findOneByIdOrSlug(idOrSlug, entityClass, null);
    }
    
    @Override
    public <T extends SlugDocument> T findOneByIdOrSlug(final String idOrSlug, final Class<T> entityClass, final Collection<String> fields) {
        String fieldName = (ObjectId.isValid(idOrSlug) ? "id" : "slug");
        Query query = Query.query(Criteria.where(fieldName).is(idOrSlug));
        if (fields != null) {
            Field field = query.fields();
            if (fields.isEmpty()) {
                field.include("id");
            }
            for (String f : fields) {
                field = field.include(f);
            }
        }
        return mongoOperations.findOne(query, entityClass);
    }
    
    @Override
    public <T extends SlugDocument> String findIdBySlug(final String slug, final Class<T> entityClass) {
        Query query = Query.query(Criteria.where("slug").is(slug));
        query.fields();
        T document = mongoOperations.findOne(query, entityClass);
        return (document == null ? null : document.getId());
    }
}
