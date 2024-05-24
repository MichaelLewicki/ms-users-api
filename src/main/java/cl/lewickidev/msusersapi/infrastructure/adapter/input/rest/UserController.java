package cl.lewickidev.msusersapi.infrastructure.adapter.input.rest;

import cl.lewickidev.msusersapi.domain.dto.MessageDTO;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/ms-users-api")
public interface UserController {

    @PostMapping(value = "/user")
    ResponseEntity<User> postUser(@RequestBody User user) throws HandledException;

    @PutMapping("/user/{idUser}")
    ResponseEntity<User> updateUserById(@RequestBody User user,
                                        @Valid @NotBlank @PathVariable("idUser") String idUser,
                                        @RequestHeader("Authorization") String bearerToken) throws HandledException;

    @GetMapping("/user/{idUser}")
    ResponseEntity<User> findUserById(@Valid @NotBlank @PathVariable("idUser") String idUser,
                                      @RequestHeader("Authorization") String bearerToken) throws HandledException;

    @GetMapping("/user")
    ResponseEntity<Page<User>> findAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy,
                                            @RequestHeader("Authorization") String bearerToken) throws HandledException;


    @DeleteMapping("/user/{idUser}")
    ResponseEntity<MessageDTO> deleteUserById(@Valid @NotBlank @PathVariable("idUser") String idUser,
                                              @RequestHeader("Authorization") String bearerToken) throws HandledException;

}
