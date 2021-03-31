package com.github.ericomonteiro.smartstock.config.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.core.convert.converter.Converter;

@Configuration
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/product/*", "/product")
                            .hasRole("user")
                        .antMatchers(HttpMethod.POST, "/product")
                            .hasRole("administrator")
                        .antMatchers("/", "/swagger-ui/**", "/v3/api-docs/**")
                            .permitAll()
                        .anyRequest()
                            .authenticated()
                .and()
                    .oauth2ResourceServer().jwt(
                                    jwt  -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
        );
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakConverter());
        return jwtConverter;
    }
}
