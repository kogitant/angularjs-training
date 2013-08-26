package org.eluder.score.tables.service.configuration;

import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.service.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;


public class MongoTest extends BaseIntegrationTest {

    @Autowired
    private MongoOperations operations;
    
    @Test
    public void test() {
        Tournament tournament = new Tournament();
        operations.save(tournament);
    }
    
    
}
