package iserba.to;

import iserba.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    @Override
    public User createFromDto(UserTo userTo) {
        return updateEntity(new User(), userTo);
    }

    @Override
    public UserTo createFromEntity(User entity) {
        UserTo userTo = new UserTo();
        userTo.setName(entity.getName());
        userTo.setEmail(entity.getEmail());
        userTo.setPassword(entity.getPassword());
        userTo.setId(entity.getId());
        return userTo;
    }

    @Override
    public User updateEntity(User entity, UserTo dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        if (dto.getId() != null) entity.setId(dto.getId());
        return entity;
    }
}
