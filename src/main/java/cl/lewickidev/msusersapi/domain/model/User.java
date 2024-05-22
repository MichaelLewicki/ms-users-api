package cl.lewickidev.msusersapi.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private String email;
    private String password;

}
