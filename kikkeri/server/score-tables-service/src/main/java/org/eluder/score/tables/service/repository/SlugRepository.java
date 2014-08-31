package org.eluder.score.tables.service.repository;

import java.util.Collection;

import org.eluder.score.tables.api.SlugDocument;

public interface SlugRepository {

    <T extends SlugDocument> T findOneByIdOrSlug(String idOrSlug, Class<T> entityClass);
    
    <T extends SlugDocument> T findOneByIdOrSlug(String idOrSlug, Class<T> entityClass, Collection<String> fields);
    
    <T extends SlugDocument> String findIdBySlug(String slug, Class<T> entityClass);
}
