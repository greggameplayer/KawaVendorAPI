package com.kawa.service.dto.response.mongo;

import com.kawa.domain.bean.Product;
import org.springframework.context.annotation.Bean;

public class ProductFindOneMongoResponseDTO {

    @Bean
    public Document document(){
        return new Document();
    }


    public static class Document extends Product {
    }
}
