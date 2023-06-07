package com.kawa.service.dto.response.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.util.Map;

public abstract class FindOneMongoResponseDTO {

    @JsonProperty("document")
    protected abstract void unpackNested(Map<String, Object> document) throws ParseException;
}
