package cl.lewickidev.msusersapi.infrastructure.port.output;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;

public interface AuthOutputPort {

    void getUserToAuth(AuthDTO authDTO, String jwt);

}
