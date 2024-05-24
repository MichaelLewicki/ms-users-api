package cl.lewickidev.msusersapi.application.usecase;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import cl.lewickidev.msusersapi.infrastructure.port.input.AuthInputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.AuthOutputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.UserOutputPort;
import cl.lewickidev.msusersapi.infrastructure.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUseCase implements AuthInputPort {

    @Autowired
    private UserOutputPort userOutputPort;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthOutputPort authOutputPort;

    @Override
    public TokenDTO authenticateUser(AuthDTO authDTO) {
        String jwt = jwtTokenProvider.createToken(authDTO.getEmail());
        authOutputPort.getUserToAuth(authDTO, jwt);
        return new TokenDTO(jwt);
    }

    @Override
    public String createToken(String username) {
        return jwtTokenProvider.createToken(username);
    }

    @Override
    public Boolean validateToken(String token) throws HandledException {
        if (jwtTokenProvider.validateToken(token)) {
           return true;
        } else {
            throw new HandledException("401", "this token is not valid");
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtTokenProvider.getUsernameFromToken(token);
    }
}
