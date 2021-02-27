package com.sfl.cafemanager.mapper;

import com.sfl.cafemanager.entity.TableEntity;
import com.sfl.cafemanager.rest.model.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface TableMapper {

    @Mapping(source = "waiter.id", target = "waiterId")
    Table toDomain(TableEntity tableEntity);

    List<Table> toDomain(List<TableEntity> tableEntity);

    @Mapping(source = "waiterId", target = "waiter.id")
    TableEntity toEntity(Table table);
}
