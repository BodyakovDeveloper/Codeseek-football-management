package com.codeseek.footballmanagement.mapper;

import com.codeseek.footballmanagement.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    void updateTeam(@MappingTarget Team teamFromDb, Team team);
}