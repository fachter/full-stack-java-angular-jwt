package com.fachter.backend.controllers;

import com.fachter.backend.viewModels.AuthenticationRequestViewModel;
import com.fachter.backend.viewModels.AuthenticationResponseViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    public AuthenticationController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseViewModel> createAuthToken(@RequestBody AuthenticationRequestViewModel authenticationRequestViewModel) {
        try {
            AuthenticationResponseViewModel response = authenticationUseCase.authenticate(authenticationRequestViewModel);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
