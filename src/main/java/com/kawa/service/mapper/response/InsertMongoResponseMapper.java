package com.kawa.service.mapper.response;

import com.kawa.service.dto.response.InsertResponseDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the Response DTO {@link InsertResponseDTO} and its Mongo DTO {@link InsertMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsertMongoResponseMapper {
    InsertResponseDTO toEntity(InsertMongoResponseDTO dtoList);

    InsertMongoResponseDTO toDto(InsertResponseDTO entityList);
}
