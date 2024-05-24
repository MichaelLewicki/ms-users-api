package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "PHONE")
public class PhoneEntity {

    @Id
    @Column(name = "ID_PHONE")
    private String id;

    @Column(name = "NUMBER")
    @NotBlank(message = "number cannot be blank")
    private String number;

    @Column(name = "CITY_CODE")
    @NotBlank(message = "citycode cannot be blank")
    private String cityCode;

    @Column(name = "COUNTRY_CODE")
    @NotBlank(message = "countrycode cannot be blank")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    @JsonBackReference
    private UserEntity user;

}
