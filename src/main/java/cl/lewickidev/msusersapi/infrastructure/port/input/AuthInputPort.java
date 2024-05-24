package cl.lewickidev.msusersapi.infrastructure.port.input;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;

public interface AuthInputPort {

    TokenDTO authenticateUser(AuthDTO authDTO);


}
