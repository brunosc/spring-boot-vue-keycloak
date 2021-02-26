package com.brunosc.backend.service.keycloak;

import com.brunosc.backend.usecase.GetUserByEmailUseCase;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class GetUserByEmailKeycloakService extends AbstractKeycloakService implements GetUserByEmailUseCase {

    GetUserByEmailKeycloakService(KeycloakSpringBootProperties keycloakProperties) {
        super(keycloakProperties);
    }

    @Override
    public Optional<UserRepresentation> getUserByEmail(String email) {
        RealmResource realm = getRealm();
        UsersResource usersResource = realm.users();

        List<UserRepresentation> userRepresentations = usersResource.search(email);

        if (userRepresentations.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(userRepresentations.get(0));
    }

}
