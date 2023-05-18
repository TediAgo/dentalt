package ta.presentation.dentalt.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.auth.model.AuthenticationRequest;
import ta.presentation.dentalt.auth.model.AuthenticationResponse;
import ta.presentation.dentalt.auth.model.RegisterRequest;
import ta.presentation.dentalt.security.service.JwtService;
import ta.presentation.dentalt.user.UserRepository;
import ta.presentation.dentalt.user.model.Roles;
import ta.presentation.dentalt.user.model.UserEntity;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(String.valueOf(Roles.PATIENT))
                .joinedOn(LocalDateTime.now())
                .validity(Boolean.TRUE)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .roles(user.getRoles())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .roles(user.getRoles())
                .token(jwtToken)
                .build();
    }
}
