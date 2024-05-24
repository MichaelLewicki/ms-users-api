package cl.lewickidev.msusersapi.application.usecase;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;
import cl.lewickidev.msusersapi.infrastructure.port.input.AuthInputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.AuthOutputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.UserOutputPort;
import cl.lewickidev.msusersapi.infrastructure.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUseCase implements AuthInputPort {

    @Autowired
    private UserOutputPort userOutputPort;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthOutputPort authOutputPort;

    @Override
    public TokenDTO authenticateUser(AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getEmail(),
                        authDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        authOutputPort.getUserToAuth(authDTO, jwt);
        return new TokenDTO(jwt);
    }
}
