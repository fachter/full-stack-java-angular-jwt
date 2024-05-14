package com.fachter.backend.useCases.auth;

import com.fachter.backend.controllers.auth.RegisterUserUseCase;
import com.fachter.backend.entities.UserAccount;
import com.fachter.backend.exceptions.UsernameAlreadyExistsException;
import com.fachter.backend.repositories.UserRepository;
import com.fachter.backend.viewModels.auth.RegisterUserViewModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterUserViewModel registerUserViewModel) throws UsernameAlreadyExistsException {
        if (userRepository.findByUsername(registerUserViewModel.username).isPresent())
            throw new UsernameAlreadyExistsException();
        userRepository.save(new UserAccount()
                .setUsername(registerUserViewModel.username)
                .setPassword(
                        passwordEncoder.encode(registerUserViewModel.password)
                )
        );
    }
}
