package org.eluder.score.tables.rest.springmvc.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.eluder.score.tables.rest.springmvc.filter.CorsFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringMvcWebAppInitializer implements WebApplicationInitializer {

    private static final String CORS_FILTER_NAME = "cors";
    private static final String CORS_FILTER_MAPPING = "/*";
    
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";
    
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMvcConfiguration.class);

        FilterRegistration.Dynamic cors = servletContext.addFilter(CORS_FILTER_NAME, CorsFilter.class);
        cors.addMappingForUrlPatterns(null, false, CORS_FILTER_MAPPING);
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);

        servletContext.addListener(new ContextLoaderListener(context));
    }

}
