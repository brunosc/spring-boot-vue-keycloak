package com.brunosc.backend.controller;

import com.brunosc.backend.domain.User;
import com.brunosc.backend.usecase.CreateUserUseCase;
import com.brunosc.backend.usecase.CreateUserUseCase.NewUserCommand;
import com.brunosc.backend.usecase.GetLoggedInUserUseCase;
import com.brunosc.backend.usecase.LoginUseCase;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;
    private final GetLoggedInUserUseCase getLoggedInUserUseCase;

    UserController(CreateUserUseCase createUserUseCase, LoginUseCase loginUseCase, GetLoggedInUserUseCase getLoggedInUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.loginUseCase = loginUseCase;
        this.getLoggedInUserUseCase = getLoggedInUserUseCase;
    }

    @PostMapping
    ResponseEntity<Void> postUser(@RequestBody NewUserCommand user) {
        createUserUseCase.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/token")
    ResponseEntity<AccessTokenResponse> postToken(@RequestBody LoginUseCase.LoginCommand login) {
        return ResponseEntity.ok(loginUseCase.login(login));
    }

    @GetMapping("/unprotected-data")
    public String getName() {
        return "Hello, this endpoint is not protected.";
    }


    @GetMapping("/protected-data")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(getLoggedInUserUseCase.getUser());
    }
}
