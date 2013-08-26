package org.eluder.score.tables.service.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.service.repository.criteria.KeywordCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {
    
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Player findBySearchName(final String name) {
        Assert.notNull(name, "The given name must not be null!");
        return mongoOperations.findOne(query(where("search.name").is(name)), Player.class);
    }
    
    @Override
    public List<Player> findBySearchNameKeywords(final String name) {
        Assert.notNull(name, "The given name must not be null!");
        return mongoOperations.find(query(new KeywordCriteria("search.nameKeywords", name)), Player.class);
    }
}
