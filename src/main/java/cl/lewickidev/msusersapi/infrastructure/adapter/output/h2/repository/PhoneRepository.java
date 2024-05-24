package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.repository;

import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {

}
