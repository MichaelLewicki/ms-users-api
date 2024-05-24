package cl.lewickidev.msusersapi.infrastructure.adapter.input.rest.imp;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.Phone;
import cl.lewickidev.msusersapi.infrastructure.adapter.input.rest.PhoneController;
import cl.lewickidev.msusersapi.infrastructure.port.input.AuthInputPort;
import cl.lewickidev.msusersapi.infrastructure.port.input.PhoneInputPort;
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
public class PhoneControllerImp implements PhoneController {

    @Autowired
    private PhoneInputPort phoneInputPort;

    @Autowired
    private AuthInputPort authInputPort;

    @Override
    public ResponseEntity<Phone> postPhoneByUserId(String idUser, Phone phone, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        if (phone.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postPhone] Request payload: {}", phone.toString());
        Phone result = phoneInputPort.postPhoneByUserId(idUser, phone);
        log.info("[postPhone] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<Phone> updatePhoneById(Phone phone, String idPhone, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        if (phone.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updatePhoneById] Request resource: {} ", idPhone.toString());
        log.info("[updatePhoneById] Request payload: {} ", phone.toString());
        Phone result = phoneInputPort.updatePhoneById(phone, idPhone);
        log.info("[updatePhoneById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Phone> findPhoneById(String idPhone, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        log.info("[findPhoneById] Request resource: {}", idPhone.toString());
        Phone result = phoneInputPort.findPhoneById(idPhone);
        log.info("[findPhoneById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<Phone>> findAllPhones(Integer pageNo, Integer pageSize, String sortBy, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        log.info("[findAllPhones] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Phone> result = phoneInputPort.findAllPhones(pageable);
        log.info("[findAllPhones] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<MessageDTO> deletePhoneById(String idPhone, String bearerToken) throws HandledException {
        authInputPort.validateToken(bearerToken);
        log.info("[deletePhoneById] Request resource: {}", idPhone.toString());
        MessageDTO result = phoneInputPort.deletePhoneById(idPhone);
        log.info("[deletePhoneById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
