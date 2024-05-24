package cl.lewickidev.msusersapi.infrastructure.adapter.input.rest;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.Phone;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/ms-users-api")
public interface PhoneController {

    @PostMapping(value = "/user/{idUser}/phone")
    ResponseEntity<Phone> postPhoneByUserId(@Valid @NotNull @PathVariable("idUser") String idUser,
                                            @RequestBody Phone phone) throws HandledException;

    @PutMapping("/phone/{idPhone}")
    ResponseEntity<Phone> updatePhoneById(@RequestBody Phone phone, @Valid @NotNull @PathVariable("idPhone") String idPhone) throws HandledException;

    @GetMapping("/phone/{idPhone}")
    ResponseEntity<Phone> findPhoneById(@Valid @NotNull @PathVariable("idPhone") String idPhone) throws HandledException;

    @GetMapping("/phone")
    ResponseEntity<Page<Phone>> findAllPhones(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "id") String sortBy);


    @DeleteMapping("/phone/{idPhone}")
    ResponseEntity<MessageDTO> deletePhoneById(@Valid @NotNull @PathVariable("idPhone") String idPhone) throws HandledException;

}
