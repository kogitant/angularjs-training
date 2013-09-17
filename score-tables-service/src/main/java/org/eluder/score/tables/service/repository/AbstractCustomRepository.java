package org.eluder.score.tables.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public abstract class AbstractCustomRepository {

    @Autowired
    protected MongoOperations mongoOperations;
    
    
}
