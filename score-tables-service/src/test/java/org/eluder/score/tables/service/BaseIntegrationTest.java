package org.eluder.score.tables.service;

import java.util.HashMap;
import java.util.Map;

import org.eluder.score.tables.service.BaseIntegrationTest.IntegrationContextInitializer;
import org.eluder.score.tables.service.configuration.ServiceConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        initializers = { IntegrationContextInitializer.class },
        classes = { ServiceConfiguration.class }
)
public abstract class BaseIntegrationTest {
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    protected MongoOperations mongoTemplate;
    
    @Before
    public void init() {
        logger.debug(mongoTemplate.getCollectionNames().toString());
        for (String collection : mongoTemplate.getCollectionNames()) {
            if (!collection.startsWith("system.")) {
                mongoTemplate.dropCollection(collection);
            }
        }
    }
    
    private static Map<String, Object> properties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("mongo.db", "score-tables-it");
        return properties;
    }
    
    public static class IntegrationContextInitializer implements ApplicationContextInitializer<AbstractApplicationContext> {
        @Override
        public void initialize(final AbstractApplicationContext applicationContext) {
            ConfigurableEnvironment env = applicationContext.getEnvironment();
            env.getPropertySources().addFirst(new MapPropertySource("it-tests", properties()));
        }
    }
}
