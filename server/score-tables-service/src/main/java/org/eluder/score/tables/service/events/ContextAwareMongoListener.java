package org.eluder.score.tables.service.events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

public class ContextAwareMongoListener<T> extends AbstractMongoEventListener<T> implements ApplicationContextAware {
    
    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected <B> B getBean(final Class<B> type) {
        return applicationContext.getBean(type);
    }
}
