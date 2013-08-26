package org.eluder.score.tables.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MongoConfiguration.class)
@ComponentScan(basePackages = { "org.eluder.score.tables.service" })
public class ServiceConfiguration {

}
