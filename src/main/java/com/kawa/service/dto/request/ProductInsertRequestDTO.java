package com.kawa.service.dto.request;

import com.kawa.domain.bean.ProductWithoutId;

public class ProductInsertRequestDTO extends ProductWithoutId {

    @Override
    public String toString() {
        String superString = super.toString();
        superString = superString.substring(superString.indexOf('{') + 1, superString.lastIndexOf('}'));
        return "ProductInsertRequestDTO{" + superString + "}";
    }
}
