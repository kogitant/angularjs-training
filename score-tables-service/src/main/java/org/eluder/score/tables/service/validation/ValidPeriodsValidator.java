package org.eluder.score.tables.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.api.validation.ValidPeriods;
import org.eluder.score.tables.service.utils.MongoDocumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class ValidPeriodsValidator implements ConstraintValidator<ValidPeriods, Match> {

    @Autowired
    private MongoOperations mongoOperations;
    
    @Override
    public void initialize(final ValidPeriods constraintAnnotation) {
        
    }
    
    @Override
    public boolean isValid(final Match value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Tournament tournament = findTournament(value.getTournamentId());
        int played = value.getPeriods().size();
        int required = tournament.getConfigurations().get(value.getType()).getPeriods();
        if (value.getType().isBestOf()) {
            int requiredWins = required / 2 + 1;
            if (value.getBluePlayerPeriodsWon() != requiredWins && value.getPinkPlayerPeriodsWon() != requiredWins) {
                return false;
            }
        } else if (played != required) {
            return false;
        }
        return true;
    }
    
    private Tournament findTournament(final String tournamentId) {
        MongoDocumentResolver<Tournament> resolver = new MongoDocumentResolver<>(mongoOperations, Tournament.class, "configurations");
        return resolver.apply(tournamentId);
    }
}
