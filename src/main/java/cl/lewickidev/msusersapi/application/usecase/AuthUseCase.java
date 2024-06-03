package cl.lewickidev.msusersapi.application.usecase;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import cl.lewickidev.msusersapi.infrastructure.port.input.AuthInputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.UserOutputPort;
import cl.lewickidev.msusersapi.infrastructure.util.JwtTokenProvider;
import cl.lewickidev.msusersapi.infrastructure.util.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDTO authenticateUser(AuthDTO authDTO) throws HandledException {
        User user = userOutputPort.findUserByEmail(authDTO.getEmail());
        Boolean validation = passwordEncoder.checkPassword(authDTO.getPassword(), user.getPassword());
        if (validation) {
            String jwt = jwtTokenProvider.createToken(user.getEmail());
            userOutputPort.authenticateUserByEmail(user.getEmail(), jwt);
            return new TokenDTO(jwt);
        } else {
            throw new HandledException("409", "The Password is incorrect");
        }
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
