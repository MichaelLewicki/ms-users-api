package cl.lewickidev.msusersapi.application.port.input;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.domain.dto.TokenDTO;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public interface AuthInputPort {

    TokenDTO authenticateUser(AuthDTO authDTO) throws HandledException;

    String createToken(String username);

    Boolean validateToken(String token) throws HandledException;

    String getUsernameFromToken(String token);


}
