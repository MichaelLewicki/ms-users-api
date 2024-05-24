package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2;

import cl.lewickidev.msusersapi.domain.dto.AuthDTO;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.UserEntity;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.repository.UserRepository;
import cl.lewickidev.msusersapi.infrastructure.port.output.AuthOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthH2Adapter implements AuthOutputPort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserToAuth(AuthDTO authDTO, String jwt) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(authDTO.getEmail());
        if (userEntity.isPresent()) {
            userEntity.get().setToken(jwt);
            userRepository.save(userEntity.get());
        }
    }

}
