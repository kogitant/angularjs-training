package org.eluder.score.tables.rest.springmvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

    private static final String REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String MAX_AGE = "Access-Control-Max-Age";
    private static final String CONTENT_TYPE = "Content-Type";
    
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        response.addHeader(ALLOW_ORIGIN, "*");
        response.addHeader(ALLOW_CREDENTIALS, "true");
        response.addHeader(ALLOW_HEADERS, "Accept, Accept-Language, Content-Language, Last-Event-ID, Content-Type");
        if (isPreflightRequest(request)) {
            response.addIntHeader(MAX_AGE, 300);
            response.setContentType(getContentType(request));
            response.setStatus(HttpServletResponse.SC_OK);
            response.flushBuffer();
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isPreflightRequest(final HttpServletRequest request) {
        return (request.getHeader(REQUEST_METHOD) != null &&
                RequestMethod.OPTIONS.toString().equals(request.getMethod()));
    }
    
    private String getContentType(final HttpServletRequest request) {
        String contentType = request.getHeader(CONTENT_TYPE);
        if (contentType != null) {
            MediaType media = MediaType.valueOf(contentType);
            return media.getType() + "/" + media.getSubtype();
        } else {
            return MediaType.TEXT_HTML_VALUE;
        }
    }
}
