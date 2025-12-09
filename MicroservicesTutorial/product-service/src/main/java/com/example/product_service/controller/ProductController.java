package com.example.product_service.controller;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
     @Autowired
     private ProductRepo repo;
     // Creating a product
     @PostMapping("/create")
     public Product addProduct(@RequestBody Product p) {
         return repo.save(p);
     }

     @GetMapping("/get")
     public List<Product> getProducts(){
         return repo.findAll();
     }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {

        Product product = repo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        return ResponseEntity.ok(product);
    }
}
