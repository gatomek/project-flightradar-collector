package pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class UserLogFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final String ANONYMOUS = "anonymous";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            MDC.put(USER_ID, getUserName());
            chain.doFilter(request, response);
        } finally {
            MDC.remove(USER_ID);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private String getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }

        return ANONYMOUS;
    }
}
