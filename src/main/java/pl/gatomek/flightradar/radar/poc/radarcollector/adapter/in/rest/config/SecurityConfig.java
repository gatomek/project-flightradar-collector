package pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.filter.RequestIdLogFilter;
import pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest.filter.UserLogFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Profile("auth")
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CorsConfigurationSource corsConfigurationSource) {
        return httpSecurity
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/actuator/**").permitAll()
                                .anyRequest()
                                .authenticated())
                .oauth2ResourceServer(
                        auth2ResourceServer -> auth2ResourceServer
                                .jwt(Customizer.withDefaults())
                )
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource)
                )
                .addFilterAfter(new UserLogFilter(), BearerTokenAuthenticationFilter.class)
                .addFilterAfter(new RequestIdLogFilter(), UserLogFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .build();
    }

    @Profile("!auth")
    @Bean
    public SecurityFilterChain noSecurityFilterChain(
            HttpSecurity httpSecurity,
            CorsConfigurationSource corsConfigurationSource) {
        return httpSecurity
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource)
                )
                .addFilterAfter(new UserLogFilter(), SecurityContextHolderFilter.class)
                .addFilterAfter(new RequestIdLogFilter(), UserLogFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${allowedOrigins}") List<String> allowedOrigins) {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }
}
