package cl.lewickidev.msusersapi.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "email", "password", "created", "modified", "last_login", "token", "isactive", "phones"})
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime modified;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;
    private String token;
    @JsonProperty("isactive")
    private Boolean isActive;

    private List<Phone> phones = new ArrayList<>();

}
