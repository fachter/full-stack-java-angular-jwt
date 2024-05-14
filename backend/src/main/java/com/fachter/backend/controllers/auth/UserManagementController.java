package com.fachter.backend.controllers.auth;

import com.fachter.backend.exceptions.UsernameAlreadyExistsException;
import com.fachter.backend.viewModels.auth.RegisterUserViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserManagementController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserManagementController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserViewModel registerUserViewModel) {
        try {
            registerUserUseCase.register(registerUserViewModel);
            return ResponseEntity.ok().build();
        } catch (UsernameAlreadyExistsException e) {
            return new ResponseEntity<>("Username already exists", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
