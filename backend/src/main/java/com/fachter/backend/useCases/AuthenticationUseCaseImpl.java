package com.fachter.backend.useCases;

import com.fachter.backend.controllers.AuthenticationUseCase;
import com.fachter.backend.entities.UserAccount;
import com.fachter.backend.entities.UserRole;
import com.fachter.backend.utils.JsonWebTokenUtil;
import com.fachter.backend.viewModels.AuthenticationRequestViewModel;
import com.fachter.backend.viewModels.AuthenticationResponseViewModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JsonWebTokenUtil jsonWebTokenUtil;

    public AuthenticationUseCaseImpl(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JsonWebTokenUtil jsonWebTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jsonWebTokenUtil = jsonWebTokenUtil;
    }

    @Override
    public AuthenticationResponseViewModel authenticate(AuthenticationRequestViewModel authenticationRequestViewModel) {
        final UserAccount userDetails = (UserAccount) userDetailsService.loadUserByUsername(authenticationRequestViewModel.username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequestViewModel.username,
                authenticationRequestViewModel.password
        ));
        final String jwt = jsonWebTokenUtil.generateToken(userDetails);
        return new AuthenticationResponseViewModel()
                .setAuthorities(getUserAuthorities(userDetails))
                .setExpiresAt(jsonWebTokenUtil.extractExpiration(jwt).getTime())
                .setToken(jwt);
    }

    private List<String> getUserAuthorities(UserAccount userDetails) {
        return userDetails.getUserRoles().stream().map(UserRole::getName).toList();
    }
}
