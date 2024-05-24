package cl.lewickidev.msusersapi.infrastructure.port.input;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserInputPort {

    User postUser(User user) throws HandledException;

    User updateUserById(User user, String idUser) throws HandledException;

    User findUserById(String idUser) throws HandledException;

    Page<User> findAllUsers(Pageable pageable);

    MessageDTO deleteUserById(String idUser) throws HandledException;

}
