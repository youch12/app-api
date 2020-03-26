package com.octo.ecom.security;

import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        KeycloakAuthenticationToken keycloakAuthenticationToken =
                (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        RefreshableKeycloakSecurityContext refreshableKeycloakSecurityContext =
                (RefreshableKeycloakSecurityContext) keycloakAuthenticationToken.getCredentials();
        return Optional.ofNullable(refreshableKeycloakSecurityContext.getToken().getName());
    }
}