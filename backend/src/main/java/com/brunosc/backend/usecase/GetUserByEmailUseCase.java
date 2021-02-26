package com.brunosc.backend.usecase;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.Optional;

public interface GetUserByEmailUseCase {
    Optional<UserRepresentation> getUserByEmail(String email);
}
