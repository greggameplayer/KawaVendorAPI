package com.kawa.service.impl;

import com.kawa.domain.bean.Product;
import com.kawa.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Product}.
 */


@Service
public class ProductServiceImpl implements ProductService {
    public static final String API_KEY = "IcpnqyikSl3vtUgwOMlodLyosYYkmQSrcOzZMpuyExEsQlZRJAsX2HSDXqou4EbK";
    @Override
    public Product getProduct() {

        return new Product();
    }

}
