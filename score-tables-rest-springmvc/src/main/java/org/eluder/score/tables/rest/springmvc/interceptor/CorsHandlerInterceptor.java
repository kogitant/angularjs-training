package org.eluder.score.tables.rest.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class CorsHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        response.addHeader(ALLOW_ORIGIN, "*");
        response.addHeader(ALLOW_CREDENTIALS, "true");
        return true;
    }
    
}
