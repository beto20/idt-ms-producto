package org.example.service;

import org.example.model.ProductRequest;
import org.example.model.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getProducts();
    boolean saveProduct(ProductRequest request);
}
