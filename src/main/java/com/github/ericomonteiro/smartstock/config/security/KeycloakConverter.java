package com.github.ericomonteiro.smartstock.config.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import java.util.Collection;

public class KeycloakConverter implements GrantedAuthoritiesMapper {
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        System.out.println(authorities);
        return null;
    }
}