package org.eluder.score.tables.service.configuration;

import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.service.SeriesStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import com.google.common.collect.ImmutableList;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("org.eluder.score.tables.service.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;
    
    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.db", "score-tables");
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        String host = env.getProperty("mongo.host", String.class, "localhost");
        Integer port = env.getProperty("mongo.port", Integer.class, 27017);
        return new MongoClient(host, port);
    }

    @Override
    protected String getMappingBasePackage() {
        return Tournament.class.getPackage().getName();
    }
    
    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(ImmutableList.of(new SeriesStatisticsService.PlayerStatsValueConverter()));
    }
    
    @Bean
    public MongoRepositoryFactory mongoRepositoryFactory() throws Exception {
        return new MongoRepositoryFactory(mongoTemplate());
    }
}
