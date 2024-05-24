package cl.lewickidev.msusersapi.infrastructure.adapter.input.rest;


import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/ms-users-api/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<TokenDTO> login(@RequestBody AuthDTO authDTO) throws HandledException;


}
