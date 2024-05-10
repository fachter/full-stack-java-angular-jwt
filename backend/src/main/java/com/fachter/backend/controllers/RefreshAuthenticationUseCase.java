package com.fachter.backend.controllers;

import com.fachter.backend.entities.UserAccount;
import com.fachter.backend.viewModels.AuthenticationResponseViewModel;

public interface RefreshAuthenticationUseCase {
    AuthenticationResponseViewModel getRefreshedToken(UserAccount user);
}
