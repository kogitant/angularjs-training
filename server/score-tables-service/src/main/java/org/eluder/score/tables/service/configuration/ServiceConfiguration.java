package org.eluder.score.tables.service.configuration;

import static org.eluder.score.tables.service.validation.ValidationMappingResourceFactory.ValidationMapping.create;

import org.eluder.score.tables.api.validation.AnyNotNull;
import org.eluder.score.tables.api.validation.ExistingReference;
import org.eluder.score.tables.api.validation.VersionedDocument;
import org.eluder.score.tables.service.validation.AnyNotNullValidator;
import org.eluder.score.tables.service.validation.ExistingReferenceValidator;
import org.eluder.score.tables.service.validation.ValidationMappingResourceFactory;
import org.eluder.score.tables.service.validation.VersionedDocumentValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@Import(MongoConfiguration.class)
@ComponentScan(basePackages = { "org.eluder.score.tables.service" })
public class ServiceConfiguration {

    @Bean
    public LocalValidatorFactoryBean validatorFactory() {
        ValidationMappingResourceFactory mappingFactory = new ValidationMappingResourceFactory();
        mappingFactory.add(create(ExistingReference.class, ExistingReferenceValidator.class));
        mappingFactory.add(create(AnyNotNull.class, AnyNotNullValidator.class));
        mappingFactory.add(create(VersionedDocument.class, VersionedDocumentValidator.class));
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setMappingLocations(new Resource[] { mappingFactory.create() });
        return factory;
    }
    
    @Bean
    public MethodValidationPostProcessor methodValidation() {
        MethodValidationPostProcessor methodValidation = new MethodValidationPostProcessor();
        methodValidation.setValidatorFactory(validatorFactory());
        return methodValidation;
    }
    
}
