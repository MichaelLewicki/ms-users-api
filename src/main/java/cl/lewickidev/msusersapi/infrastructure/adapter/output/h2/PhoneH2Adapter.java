package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.Phone;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.PhoneEntity;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.UserEntity;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.mapper.DomainEntityMapper;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.repository.PhoneRepository;
import cl.lewickidev.msusersapi.infrastructure.port.output.PhoneOutputPort;
import cl.lewickidev.msusersapi.infrastructure.port.output.UserOutputPort;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhoneH2Adapter implements PhoneOutputPort {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Autowired
    private UserOutputPort userOutputPort;

    @Override
    @Transactional
    public Phone postPhoneByUserId(String idUser, Phone phone) throws HandledException {
        User userFound = userOutputPort.findUserById(idUser);
        UserEntity userEntity = domainEntityMapper.toEntity(userFound);
        PhoneEntity phoneEntity = domainEntityMapper.toEntity(phone);
        phoneEntity.setId(UUID.randomUUID().toString());
        phoneEntity.setUser(userEntity);
        return domainEntityMapper.toDTO(phoneRepository.save(phoneEntity));
    }

    @Override
    @Transactional
    public Phone updatePhoneById(Phone phone, String idPhone) throws HandledException {
        PhoneEntity phoneFound = phoneRepository.findById(idPhone)
                .orElseThrow(() -> new HandledException("404", "Phone not found"));
        phoneFound.setNumber(phone.getNumber());
        phoneFound.setCityCode(phone.getCityCode());
        phoneFound.setCountryCode(phone.getCountryCode());
        return domainEntityMapper.toDTO(phoneRepository.save(phoneFound));
    }

    @Override
    @Transactional
    public Phone findPhoneById(String idPhone) throws HandledException {
        PhoneEntity phoneFound = phoneRepository.findById(idPhone)
                .orElseThrow(() -> new HandledException("404", "Phone not found"));
        return domainEntityMapper.toDTO(phoneRepository.save(phoneFound));
    }

    @Override
    @Transactional
    public Page<Phone> findAllPhones(Pageable pageable) {
        Page<PhoneEntity> phoneListFound = phoneRepository.findAll(pageable);
        return domainEntityMapper.toPhoneDTOs(phoneListFound);
    }

    @Override
    @Transactional
    public MessageDTO deletePhoneById(String idPhone) throws HandledException {
        PhoneEntity phoneFound = phoneRepository.findById(idPhone)
                .orElseThrow(() -> new HandledException("404", "Phone not found"));
        UserEntity user = phoneFound.getUser();
        if (user != null) {
            user.getPhones().remove(phoneFound);
        }
        phoneRepository.delete(phoneFound);
        return new MessageDTO("record deleted");
    }

}
