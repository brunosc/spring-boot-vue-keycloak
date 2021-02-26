package com.brunosc.backend.usecase;

public interface CreateUserUseCase {
    void create(NewUserCommand user);

    class NewUserCommand {
        private final String email;
        private final String password;
        private final String firstname;
        private final String lastname;

        public NewUserCommand(String email, String password, String firstname, String lastname) {
            this.email = email;
            this.password = password;
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }
    }
}
