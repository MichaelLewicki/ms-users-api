package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.repository;

import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}
