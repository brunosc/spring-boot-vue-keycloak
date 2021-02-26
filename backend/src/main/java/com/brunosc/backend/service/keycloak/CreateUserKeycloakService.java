package com.brunosc.backend.service.keycloak;

import com.brunosc.backend.exception.UserAlreadyExistsException;
import com.brunosc.backend.usecase.CreateUserUseCase;
import com.brunosc.backend.usecase.GetUserByEmailUseCase;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

import static java.util.Collections.singletonList;

@Service
class CreateUserKeycloakService extends AbstractKeycloakService implements CreateUserUseCase {

    private static final String ROLE = "user";

    private final GetUserByEmailUseCase getUserByEmailUseCase;

    CreateUserKeycloakService(KeycloakSpringBootProperties keycloakProperties, GetUserByEmailUseCase getUserByEmailUseCase) {
        super(keycloakProperties);
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    @Override
    public void create(NewUserCommand user) {
        validateUserAlreadyExists(user.getEmail());

        // Get realm
        RealmResource realmResource = getRealm();
        UsersResource usersResource = realmResource.users();

        // Create
        Response response = usersResource.create(createUserRepresentation(user));

        if (response.getStatus() == 201) {
            String userId = CreatedResponseUtil.getCreatedId(response);

            // create password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(user.getPassword());

            UserResource userResource = usersResource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // Get realm role student
            RoleRepresentation realmRoleUser = realmResource.roles().get(ROLE).toRepresentation();

            // Assign realm role student to user
            userResource.roles().realmLevel().add(singletonList(realmRoleUser));
        }
    }

    private void validateUserAlreadyExists(String email) {
        getUserByEmailUseCase.getUserByEmail(email)
                .ifPresent(user -> {
                    final var message = String.format("User '%s' already exists.", user.getEmail());
                    throw new UserAlreadyExistsException(message);
                });
    }

    private UserRepresentation createUserRepresentation(NewUserCommand user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(user.getEmail());
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        return userRepresentation;
    }

}
