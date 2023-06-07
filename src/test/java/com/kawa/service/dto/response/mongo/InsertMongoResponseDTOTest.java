package com.kawa.service.dto.response.mongo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class InsertMongoResponseDTOTest {

    private static final String insertedId = "5ff9f9b0e4b0b5b9b0b9b9b9";

    private static final String JSON = "{\"insertedId\":\"" + insertedId + "\"}";

    @Test
    void whenUsingJsonNode() throws Exception {
        InsertMongoResponseDTO insertMongoResponseDTO = new ObjectMapper().readerFor(InsertMongoResponseDTO.class).readValue(JSON);
        assertEquals(insertedId, insertMongoResponseDTO.getInsertedId());
    }
}
