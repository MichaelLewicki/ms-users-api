package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USER_INFO", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL")})
public class UserEntity {

    @Id
    @Column(name = "ID_USER")
    private String id;

    @Column(name = "NAME")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "email cannot be blank")
    //@Email
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "The email format isn't valid")
    private String email;

    @Column(name = "PASSWORD")
    @NotBlank(message = "password cannot be blank")
    private String password;

    @Column(name = "CREATED")
    @NotNull(message = "created cannot be null")
    private LocalDateTime created;

    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    @Column(name = "LAST_LOGIN")
    @NotNull(message = "last_login cannot be null")
    private LocalDateTime lastLogin;

    @Column(name = "TOKEN")
    //@NotBlank(message = "token cannot be blank")
    private String token;

    @Column(name = "IS_ACTIVE")
    //@NotNull(message = "isactive cannot be null")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<PhoneEntity> phones = new ArrayList<>();

}
