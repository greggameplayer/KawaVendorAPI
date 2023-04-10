package com.kawa.service.dto.response.mongo;

import com.kawa.domain.bean.Product;
import org.springframework.context.annotation.Bean;

public class ProductInsertMongoResponseDTO {
    private String insertedId;

    public String getInsertedId() {
        return insertedId;
    }

    public void setInsertedId(String insertedId) {
        this.insertedId = insertedId;
    }
}
