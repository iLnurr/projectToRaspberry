package iserba.to;

import iserba.model.UserQuotations;
import iserba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserQuotationsConverterImpl implements UserQuotationsConverter {
    @Autowired
    private UserService userService;

    @Override
    public UserQuotations createFromDto(UserQuotationsTo dto) {
        return updateEntity(new UserQuotations(), dto);
    }

    @Override
    public UserQuotationsTo createFromEntity(UserQuotations entity) {
        UserQuotationsTo dto = new UserQuotationsTo();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUser().getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public UserQuotations updateEntity(UserQuotations entity, UserQuotationsTo dto) {
        entity.setDescription(dto.getDescription());
        entity.setDateTime(dto.getDateTime());
        entity.setUser(userService.get(userService.getUserToIdByUserName(dto.getUserName())));
        if (dto.getId() != null) entity.setId(dto.getId());
        return entity;
    }
}
