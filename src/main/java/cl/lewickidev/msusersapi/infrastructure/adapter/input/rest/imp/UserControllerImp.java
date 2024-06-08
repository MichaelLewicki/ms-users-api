package cl.lewickidev.msusersapi.infrastructure.adapter.input.rest.imp;


import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.adapter.input.rest.UserController;
import cl.lewickidev.msusersapi.application.port.input.AuthInputPort;
import cl.lewickidev.msusersapi.application.port.input.UserInputPort;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserControllerImp implements UserController {

    @Autowired
    private UserInputPort userInputPort;

    @Autowired
    private AuthInputPort authInputPort;

    @Override
    public ResponseEntity<User> postUser(User user) throws HandledException {
        if (user.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postUser] Request payload: {}", user.toString());
        User result = userInputPort.postUser(user);
        log.info("[postUser] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<User> updateUserById(User user, String idUser, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        if (user.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updateUserById] Request resource: {} ", idUser.toString());
        log.info("[updateUserById] Request payload: {} ", user.toString());
        User result = userInputPort.updateUserById(user, idUser);
        log.info("[updateUserById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<User> findUserById(String idUser, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        log.info("[findUserById] Request resource: {}", idUser.toString());
        User result = userInputPort.findUserById(idUser);
        log.info("[findUserById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<User>> findAllUsers(Integer pageNo, Integer pageSize, String sortBy, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        log.info("[findAllUsers] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> result = userInputPort.findAllUsers(pageable);
        log.info("[findAllUsers] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<MessageDTO> deleteUserById(String idUser, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        log.info("[deleteUserById] Request resource: {}", idUser.toString());
        MessageDTO result = userInputPort.deleteUserById(idUser);
        log.info("[deleteUserById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
