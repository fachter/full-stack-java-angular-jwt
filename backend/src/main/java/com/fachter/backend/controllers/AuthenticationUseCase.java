package com.fachter.backend.controllers;

import com.fachter.backend.viewModels.AuthenticationRequestViewModel;
import com.fachter.backend.viewModels.AuthenticationResponseViewModel;

public interface AuthenticationUseCase {
    AuthenticationResponseViewModel authenticate(AuthenticationRequestViewModel authenticationRequestViewModel);
}
