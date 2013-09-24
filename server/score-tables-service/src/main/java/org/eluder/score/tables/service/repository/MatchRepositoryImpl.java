package org.eluder.score.tables.service.repository;

import static org.eluder.score.tables.service.repository.criteria.CollectionCriteria.isIn;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepositoryImpl extends AbstractCustomRepository implements MatchRepositoryCustom {
    
    @Override
    public Iterable<Match> find(final BasicQuery query) {
        Query q = filters(new Query(), query).with(query.getSort());
        return mongoOperations.find(q, Match.class);
    }

    private Query filters(final Query query, final BasicQuery filters) {
        if (!filters.getTournamentIds().isEmpty()) {
            query.addCriteria(isIn("tournamentId", filters.getTournamentIds()));
        }
        if (!filters.getPlayerIds().isEmpty()) {
            query.addCriteria(isIn("bluePlayerId", filters.getPlayerIds()).orOperator(isIn("pinkPlayerId", filters.getPlayerIds())));
        }
        return query;
    }
}
