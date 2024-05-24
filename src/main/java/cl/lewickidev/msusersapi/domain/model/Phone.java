package cl.lewickidev.msusersapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {

    private String id;
    private String number;
    @JsonProperty("citycode")
    private String cityCode;
    @JsonProperty("countrycode")
    private String countryCode;

}
