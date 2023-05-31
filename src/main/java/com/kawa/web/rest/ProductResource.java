package com.kawa.web.rest;

import com.kawa.service.ProductService;
import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.response.ProductInsertResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer")
@RequestMapping("/api")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    /**
     * {@code GET  /products} : get all the products.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("/products")
    public ResponseEntity<ProductResponseDTO> getProducts() {
        log.info("REST request to get all Products");
        ProductResponseDTO result = productService.getProducts();
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /products/:id} : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id) {
        log.info("REST request to get Product : {}", id);
        ProductResponseDTO result = productService.getProduct(id);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code POST  /products} : Create a new product.
     *
     * @param productRequestDTO the productDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productDTO, or with status {@code 400 (Bad Request)} if the product has already an ID.
     */
    @PostMapping("/products")
    public ResponseEntity<ProductInsertResponseDTO> createProduct(@Valid @RequestBody ProductInsertRequestDTO productRequestDTO) {
        log.info("REST request to save Product : {}", productRequestDTO);
        ProductInsertResponseDTO result = productService.insertProduct(productRequestDTO);
        return ResponseEntity.ok().body(result);
    }
}
