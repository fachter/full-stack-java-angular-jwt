package com.fachter.backend.controllers.auth;

import com.fachter.backend.exceptions.UsernameAlreadyExistsException;
import com.fachter.backend.viewModels.auth.RegisterUserViewModel;

public interface RegisterUserUseCase {
    void register(RegisterUserViewModel registerUserViewModel) throws UsernameAlreadyExistsException;
}
