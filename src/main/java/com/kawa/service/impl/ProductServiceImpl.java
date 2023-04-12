package com.kawa.service.impl;

import com.kawa.domain.bean.Product;
import com.kawa.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service Implementation for managing {@link Product}.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private static final String API_KEY = "IcpnqyikSl3vtUgwOMlodLyosYYkmQSrcOzZMpuyExEsQlZRJAsX2HSDXqou4EbK";

    private final RestTemplate restTemplate;

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct() {
        return new Product();
    }
}
