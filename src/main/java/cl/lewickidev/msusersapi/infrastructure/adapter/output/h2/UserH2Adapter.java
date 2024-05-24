package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.UserEntity;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.mapper.DomainEntityMapper;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.repository.UserRepository;
import cl.lewickidev.msusersapi.infrastructure.port.output.UserOutputPort;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import cl.lewickidev.msusersapi.infrastructure.util.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserH2Adapter implements UserOutputPort {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public User postUser(User user) throws HandledException {
        if (user.getEmail() != null) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new HandledException("409", "El correo ya está registrado");
            }
        }
        UserEntity userEntity = domainEntityMapper.toEntity(user);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setToken(jwtTokenProvider.createToken(user.getEmail()));
        if (!userEntity.getPhones().isEmpty()) {
            userEntity.getPhones().forEach( phoneEntity -> {
                if (phoneEntity.getId() == null) {
                    phoneEntity.setId(UUID.randomUUID().toString());
                }
                phoneEntity.setUser(userEntity);
            });
        }
        userEntity.setIsActive(true);
        return domainEntityMapper.toDTO(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public User updateUserById(User user, String idUser) throws HandledException {
        UserEntity userFound = userRepository.findById(idUser)
                .orElseThrow(() -> new HandledException("404", "User not found"));
        if (user.getName() != null) {
            userFound.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userFound.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userFound.setPassword(user.getPassword());
        }
        if (user.getLastLogin() != null) {
            userFound.setLastLogin(user.getLastLogin());
        }
        if (user.getToken() != null) {
            userFound.setToken(user.getToken());
        }
        if (user.getIsActive() != null) {
            userFound.setIsActive(userFound.getIsActive());
        }
        userFound.setModified(LocalDateTime.now());
        return domainEntityMapper.toDTO(userRepository.save(userFound));
    }

    @Override
    @Transactional
    public User findUserById(String idUser) throws HandledException {
        UserEntity userFound = userRepository.findById(idUser)
                .orElseThrow(() -> new HandledException("404", "User not found"));
        return domainEntityMapper.toDTO(userRepository.save(userFound));
    }

    @Override
    @Transactional
    public Page<User> findAllUsers(Pageable pageable) {
        Page<UserEntity> userList = userRepository.findAll(pageable);
        return domainEntityMapper.toUserDTOs(userList);
    }

    @Override
    @Transactional
    public MessageDTO deleteUserById(String idUser) throws HandledException {
        UserEntity userFound = userRepository.findById(idUser)
                .orElseThrow(() -> new HandledException("404", "User not found"));
        userRepository.delete(userFound);
        return new MessageDTO("record deleted");
    }


    @Override
    @Transactional
    public User findUserByEmail(String email) throws HandledException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new HandledException("404", "User not found searching for this email"));
        return domainEntityMapper.toDTO(userEntity);
    }


}
