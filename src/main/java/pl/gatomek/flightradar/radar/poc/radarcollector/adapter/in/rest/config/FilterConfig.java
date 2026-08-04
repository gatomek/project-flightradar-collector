package pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.filter.TimeElapsedFilter;

import java.util.Collections;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TimeElapsedFilter> registerTimeElapsedFilter() {
        FilterRegistrationBean<TimeElapsedFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TimeElapsedFilter());
        registrationBean.setUrlPatterns(Collections.singleton("/*"));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
