package cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.mapper;

import cl.lewickidev.msusersapi.domain.model.Phone;
import cl.lewickidev.msusersapi.domain.model.User;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.PhoneEntity;
import cl.lewickidev.msusersapi.infrastructure.adapter.output.h2.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DomainEntityMapper {

    User toDTO(UserEntity entity);

    UserEntity toEntity(User dto);

    default Page<User> toUserDTOs(Page<UserEntity> userEntities) {
        return userEntities.map(this::toDTO);
    }

    Phone toDTO(PhoneEntity entity);

    @Mapping(target = "user", ignore = true)
    PhoneEntity toEntity(Phone dto);

    default Page<Phone> toPhoneDTOs(Page<PhoneEntity> phoneEntities) {
        return phoneEntities.map(this::toDTO);
    }

}
