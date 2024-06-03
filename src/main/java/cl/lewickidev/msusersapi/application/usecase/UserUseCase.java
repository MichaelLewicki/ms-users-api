package cl.lewickidev.msusersapi.application.usecase;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.port.input.UserInputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.UserOutputPort;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserUseCase implements UserInputPort {

    @Autowired
    private UserOutputPort userOutputPort;

    @Value("${app.password.regex}")
    private String regularExpression;

    @Override
    public User postUser(User user) throws HandledException {
        if (!isValidPassword(user.getPassword())) {
            throw new HandledException("400", "The password doesn't contain the proper format.");
        }
        return userOutputPort.postUser(user);
    }

    @Override
    public User updateUserById(User user, String idUser) throws HandledException {
        return userOutputPort.updateUserById(user, idUser);
    }

    @Override
    public User findUserById(String idUser) throws HandledException {
        return userOutputPort.findUserById(idUser);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userOutputPort.findAllUsers(pageable);
    }

    @Override
    public MessageDTO deleteUserById(String idUser) throws HandledException {
        return userOutputPort.deleteUserById(idUser);
    }

    private Boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(regularExpression);
        return pattern.matcher(password).matches();
    }

}
