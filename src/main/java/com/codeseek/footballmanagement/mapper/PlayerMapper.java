package com.codeseek.footballmanagement.mapper;

import com.codeseek.footballmanagement.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    void updatePlayer(@MappingTarget Player playerFromDb, Player player);
}