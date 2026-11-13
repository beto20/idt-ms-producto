package org.example.controller;

import org.example.model.ProductRequest;
import org.example.model.ProductResponse;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products/")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500", "https://d26bciom6vbxaq.cloudfront.net"})
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return service.getProducts();
    }

    @PostMapping("register")
    public boolean saveProduct(@RequestBody ProductRequest request) {
        return service.saveProduct(request);
    }

}
