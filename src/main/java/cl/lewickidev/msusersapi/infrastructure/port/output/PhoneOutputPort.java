package cl.lewickidev.msusersapi.infrastructure.port.output;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.Phone;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneOutputPort {

    Phone postPhoneByUserId(String idUser, Phone phone) throws HandledException;

    Phone updatePhoneById(Phone phone, String idPhone) throws HandledException;

    Phone findPhoneById(String idPhone) throws HandledException;

    Page<Phone> findAllPhones(Pageable pageable);

    MessageDTO deletePhoneById(String idPhone) throws HandledException;

}
