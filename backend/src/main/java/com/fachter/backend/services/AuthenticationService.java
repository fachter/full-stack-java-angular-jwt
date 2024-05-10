package com.fachter.backend.services;

import com.fachter.backend.entities.UserAccount;
import com.fachter.backend.viewModels.AuthenticationResponseViewModel;

public interface AuthenticationService {
    AuthenticationResponseViewModel getAuthenticationResponseFromUser(UserAccount userDetails);
}
