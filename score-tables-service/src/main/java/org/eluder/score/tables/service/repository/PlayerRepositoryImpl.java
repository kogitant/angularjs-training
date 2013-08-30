package org.eluder.score.tables.service.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.repository.criteria.KeywordCriteria;
import org.eluder.score.tables.service.utils.LowerCaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {
    
    @Autowired
    private MongoOperations mongoOperations;
    
    @Override
    public Player findBySearchName(final BasicQuery query) {
        Assert.notNull(query, "The given query must not be null!");
        Assert.notNull(query.getValue(), "The given query value must not be null!");
        Query q = query(where("search.name").is(new LowerCaser().apply(query.getValue())));
        filters(q, query);
        return mongoOperations.findOne(q, Player.class);
    }
    
    @Override
    public Iterable<Player> findBySearchNameKeywords(final BasicQuery query) {
        Assert.notNull(query, "The given query must not be null!");
        Assert.notNull(query.getValue(), "The given query value must not be null!");
        Query q = query(new KeywordCriteria("search.nameKeywords", query.getValue()));
        filters(q, query);
        return mongoOperations.find(q, Player.class);
    }
        
    private Query filters(final Query query, final BasicQuery filters) {
        if (filters.getPlayerIds().size() == 1) {
            query.addCriteria(where("id").is(filters.getPlayerIds().get(0)));
        } else if (filters.getPlayerIds().size() > 1) {
            query.addCriteria(where("id").in(filters.getPlayerIds()));
        }
        return query;
    }
}
