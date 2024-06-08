package cl.lewickidev.msusersapi.application.usecase;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.Phone;
import cl.lewickidev.msusersapi.application.port.input.PhoneInputPort;
import cl.lewickidev.msusersapi.application.port.output.PhoneOutputPort;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhoneUseCase implements PhoneInputPort {

    @Autowired
    private PhoneOutputPort phoneOutputPort;

    @Override
    public Phone postPhoneByUserId(String idUser, Phone phone) throws HandledException {
        return phoneOutputPort.postPhoneByUserId(idUser, phone);
    }

    @Override
    public Phone updatePhoneById(Phone phone, String idPhone) throws HandledException {
        return phoneOutputPort.updatePhoneById(phone, idPhone);
    }

    @Override
    public Phone findPhoneById(String idPhone) throws HandledException {
        return phoneOutputPort.findPhoneById(idPhone);
    }

    @Override
    public Page<Phone> findAllPhones(Pageable pageable) {
        return phoneOutputPort.findAllPhones(pageable);
    }

    @Override
    public MessageDTO deletePhoneById(String idPhone) throws HandledException {
        return phoneOutputPort.deletePhoneById(idPhone);
    }
}
