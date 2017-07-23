package iserba.to;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ilnur on 23.07.17.
 */
public interface GenericConverter<E,D>{

    E createFromDto(D d);

    D createFromEntity(E e);

    E updateEntity(E entity, D dto);

    default List<D> createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }

    default List<E> createFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::createFromDto)
                .collect(Collectors.toList());
    }
}