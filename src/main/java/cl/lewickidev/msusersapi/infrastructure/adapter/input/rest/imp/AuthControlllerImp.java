package cl.lewickidev.msusersapi.infrastructure.adapter.input.rest.imp;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;
import cl.lewickidev.msusersapi.infrastructure.adapter.input.rest.AuthController;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import cl.lewickidev.msusersapi.application.port.input.AuthInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthControlllerImp implements AuthController {

    @Autowired
    AuthInputPort authInputPort;

    @Override
    public ResponseEntity<TokenDTO> login(AuthDTO authDTO) throws HandledException {
        log.info("[login] Request resource: {}", authDTO.toString());
        TokenDTO token = authInputPort.authenticateUser(authDTO);
        log.info("[login] Response: {}", token);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
