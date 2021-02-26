package com.brunosc.backend.service.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;

abstract class AbstractKeycloakService {

    private final KeycloakSpringBootProperties keycloakProperties;

    protected AbstractKeycloakService(KeycloakSpringBootProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    protected RealmResource getRealm() {
        Keycloak keycloak = buildKeycloak(keycloakProperties.getAuthServerUrl());
        keycloak.tokenManager().getAccessToken();
        return keycloak.realm(keycloakProperties.getRealm());
    }

    private Keycloak buildKeycloak(String authServerUrl) {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm("master")
                .clientId("admin-cli")
                .username("keycloak")
                .password("keycloak")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }
}
