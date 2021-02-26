package com.brunosc.backend.service.keycloak;

import com.brunosc.backend.usecase.LoginUseCase;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
class LoginKeycloakService implements LoginUseCase {

    private static final String CLIENT_SECRET = "e354b3e4-bb33-4ee5-8090-199fc99351bb";

    private final KeycloakSpringBootProperties keycloakProperties;

    LoginKeycloakService(KeycloakSpringBootProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    @Override
    public AccessTokenResponse login(LoginCommand login) {
        AuthzClient authzClient = AuthzClient.create(configuration());
        return authzClient.obtainAccessToken(login.getEmail(), login.getPassword());
    }

    private Configuration configuration() {
        Map<String, Object> clientCredentials = clientCredentials();
        return new Configuration(
                keycloakProperties.getAuthServerUrl(),
                keycloakProperties.getRealm(),
                keycloakProperties.getResource(),
                clientCredentials,
                null);
    }

    private Map<String, Object> clientCredentials() {
        return Map.of("secret", CLIENT_SECRET, "grant_type", "password");
    }

}
