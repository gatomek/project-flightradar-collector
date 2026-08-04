package pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public class TimeElapsedFilter extends OncePerRequestFilter {
    private static final String ELAPSED = "X-ELAPSED-MILLIS";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, wrappedResponse);
        } finally {
            long elapsed = System.currentTimeMillis() - startTime;
            wrappedResponse.setHeader(ELAPSED, String.valueOf(elapsed));
            wrappedResponse.copyBodyToResponse();
        }
    }
}
