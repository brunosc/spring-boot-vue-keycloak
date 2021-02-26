package com.brunosc.backend.service.keycloak;

import com.brunosc.backend.domain.User;
import com.brunosc.backend.usecase.GetLoggedInUserUseCase;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
class GetLoggedInUserKeycloakService extends AbstractKeycloakService implements GetLoggedInUserUseCase {

    GetLoggedInUserKeycloakService(KeycloakSpringBootProperties keycloakProperties) {
        super(keycloakProperties);
    }

    @Override
    public User getUser() {
        RealmResource realm = getRealm();
        UsersResource usersResource = realm.users();
        UserResource userResource = usersResource.get(getUserId());

        return ofNullable(userResource)
                .map(UserResource::toRepresentation)
                .map(this::mapUser)
                .orElse(null);
    }

    private User mapUser(UserRepresentation user) {
        return new User(user.getEmail(), user.getFirstName(), user.getLastName());
    }

    @SuppressWarnings("unchecked")
    private String getUserId() {
        KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal =
                (KeycloakPrincipal<RefreshableKeycloakSecurityContext>)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getName();
    }
}
