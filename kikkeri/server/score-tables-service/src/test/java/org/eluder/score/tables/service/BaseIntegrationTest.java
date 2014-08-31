package org.eluder.score.tables.service;

import java.util.HashMap;
import java.util.Map;

import org.eluder.score.tables.service.BaseIntegrationTest.IntegrationContextInitializer;
import org.eluder.score.tables.service.configuration.ServiceConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        initializers = { IntegrationContextInitializer.class },
        classes = { ServiceConfiguration.class }
)
public abstract class BaseIntegrationTest {
    
    private static final String DATABASE = "score-tables-it";
    
    @Autowired
    protected MongoOperations mongoOperations;
    
    @Autowired
    private Mongo mongo;
    
    @Before
    @After
    public void clean() {
        mongo.dropDatabase(DATABASE);
    }
    
    private static Map<String, Object> properties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("mongo.db", DATABASE);
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
