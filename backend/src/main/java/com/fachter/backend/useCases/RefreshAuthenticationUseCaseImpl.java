package com.fachter.backend.useCases;

import com.fachter.backend.controllers.RefreshAuthenticationUseCase;
import com.fachter.backend.entities.UserAccount;
import com.fachter.backend.services.AuthenticationService;
import com.fachter.backend.viewModels.AuthenticationResponseViewModel;

public class RefreshAuthenticationUseCaseImpl implements RefreshAuthenticationUseCase {
    private final AuthenticationService authenticationService;

    public RefreshAuthenticationUseCaseImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public AuthenticationResponseViewModel getRefreshedToken(UserAccount user) {
        return authenticationService.getAuthenticationResponseFromUser(user);
    }
}
