package com.brunosc.backend.usecase;

import org.keycloak.representations.AccessTokenResponse;

public interface LoginUseCase {
    AccessTokenResponse login(LoginCommand login);

    class LoginCommand {
        private final String email;
        private final String password;

        public LoginCommand(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
